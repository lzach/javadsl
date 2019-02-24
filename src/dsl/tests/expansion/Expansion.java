package dsl.tests.expansion;

import dsl.ast.AST;
import dsl.ast.ASTBuilder;
import dsl.parser.Token;

import java.util.*;

public class Expansion {
  private AST expansion;
  private final Map<String, AST> expansionMap = new HashMap<>();
  private final Map<String, AST> functionMap = new HashMap<>();
  private final Map<String, List<String>> funParams = new HashMap<>();
  private final Map<String, AST> opMap = new HashMap<>();
  private final Map<String, List<String>> opParams = new HashMap<>();
  private final Deque<Deque<Map<String, AST>>> symbols = new ArrayDeque<>();

  public Expansion(AST expansion) {
    this.expansion = expansion;
    update();
    pushStack();
  }

  private void pushStack() {
    symbols.push(new ArrayDeque<>());
    pushLocal();
  }

  private void popStack() {
    symbols.pop();
  }

  private void pushLocal() {
    symbols.peek().push(new HashMap<>());
  }

  private void popLocal() {
    symbols.peek().pop();
  }

  private AST lookup(String name) {
    for ( Map<String, AST> map : symbols.peek() ) {
       if ( map.containsKey(name)) {
         return map.get(name);
       }
    }
    return null;
  }

  private void declare(String name, AST ast) {
    symbols.peek().peek().put(name, ast);
  }

  private void set(String name, AST ast) {
    for ( Map<String, AST> map : symbols.peek()) {
      if (map.containsKey(name)) {
        map.put(name, ast);
        return;
      }
    }
    symbols.peek().peek().put(name, ast);
  }

  private void update() {
    assert Objects.equals(expansion.getTypeName(), "Expansions");
    for (AST child : expansion.get("expansions").getMemberList()) {
      assert ( Objects.equals(child.getTypeName(), "Expansion"));
      String type = dereference(child.get("type"));
      AST ast = child.get("expansion");
      expansionMap.put(type, ast);
    }
    for (AST child : expansion.get("functions").getMemberList()) {
      assert ( Objects.equals(child.getTypeName(), "Function"));
      String type = dereference(child.get("name"));
      fillMap(child, type, functionMap, funParams);
    }

    for (AST child : expansion.get("operations").getMemberList()) {
      assert ( Objects.equals(child.getTypeName(), "Operation"));
      String type = dereference(child.get("name"));
      fillMap(child, type, opMap, opParams);
    }
  }

  private void fillMap(AST child, String type, Map<String, AST> functionMap, Map<String, List<String>> funParams) {
    AST ast = child.get("expansion");
    functionMap.put(type, ast);

    List<String> pList = new ArrayList<>();
    funParams.put(type, pList);
    for ( AST param : child.get("params").getMemberList() ) {
      pList.add(dereference(param.get("name")));
    }
  }

  public AST expand(AST ast) {
    if (!expansionMap.containsKey(ast.getTypeName())) {
      throw new ExpansionException("Missing: " + ast.getTypeName());
    }
    return replace(expansionMap.get(ast.getTypeName()), ast);
  }

  public static String dereference(AST nameLit) {
    assert nameLit != null;
    //AST refAST = nameLit.get("reference");
    AST refAST = nameLit;
    assert refAST != null;
    Object o = refAST.getValue();
    assert o != null;
    if ( o instanceof Token ) {
      o = ((Token)o).getValue();
    }
    assert o instanceof String;
    return (String) o;
  }


  public AST replace(AST template, AST ast) {
    assert template != null;
    assert ast != null;
    String argName = "";
    ASTBuilder builder = new ASTBuilder();
    AST newTemplate = null;

    switch (template.getTypeName()) {
      case "member": {
        pushLocal();
        assert template.isMembers();
        assert template.hasMember("name");
        AST child = ast.get(dereference(template.get("name")));
        if (template.hasMember("template")) {
           newTemplate = template.get("template");
        }
        if (newTemplate != null) {
          child = replace(newTemplate, child);
        }
        popLocal();
        return child; 
      }
      case "members": {
        pushLocal();
        builder.setName("List");
        if (template.isMembers()) {
          newTemplate = template.get("template");
          if (template.hasMember("name")) {
            ast = ast.get(dereference(template.get("name")));
          }
        }
        if (newTemplate == null) {
          newTemplate =template;
        }
        if ( ast.isList() ) {
          declare("itemIndex", AST.create("IntLit", 0));
          int itemIndex = 0;
          for (AST child : ast.getMemberList()) {
            set("itemIndex", AST.create("IntLit", itemIndex));
            child = replace(newTemplate, child);
            if (child != null) {
              builder.add(child);
            }
            ++itemIndex;
          }
        } else if ( ast.isMembers() ) {
          declare("itemKey", null);
          for (String childName : ast.getMembers()) {
            set("itemKey", AST.create("IDLit", childName));
            AST child = replace(newTemplate, ast.get(childName));
            if (child != null) {
              builder.add(child);
            }
          }
        }
        popLocal();
        break;
      }
      case "concat":
        assert template.isList();
        assert template.getMemberList().length > 0;
        builder.setName(template.getMemberList()[0].getTypeName());
        if ( template.getMemberList()[0].isList() ) {
          for ( AST listAST : template.getMemberList() ) {
            //assert listAST.isList();
            for ( AST child : replace(listAST, ast).getMemberList() ) {
              if ( child != null ) {
                builder.add(child);
              }
            }
          }
        } else {
          String str = "";
          for ( AST child : template.getMemberList() ) {
            child = replace(child, ast);
            str += child.getValue();
          }
          builder.set(str);
        }
        break;
      case "expand":
        return replace(replace(ast.get("expansion"), ast), replace(ast.get("ast"), ast));
      case "getFunArgxs":
        //TODO: return args for given function
        builder.setName("ArgList");
        List<String> pList = funParams.get(template.getTypeName());
        AST argAST = null;
        if ( ast.hasMember("ast") ) {
          argAST = expand(ast.get("ast"));
        }
        if ( argAST == null ) {
          argAST = lookup("ast");
        }
        if (argAST == null ) {
                argAST = AST.create("IDLit", "ast");
        }
        builder.add(new AST("Arg", new Object[]{"name", AST.create("IDLit", "ast")}, new Object[]{"value", argAST}));
        System.out.println(argAST);
        for ( String name : pList ) {
          builder.add(new AST("Arg", new Object[]{"name", AST.create("IDLit", name)}, new Object[]{"value", ast.get(name)}));
        }
        return builder.create();
      case "isMember":
        if ( ast.isMembers() ) {
          return replace(template.get("expansion"), ast);
        }
        return null;
      case "isList":
        if ( ast.isList() ) {
          return replace(template.get("expansion"), ast);
        }
        return null;
      case "isValue":
        if ( ast.isValue() ) {
          return replace(template.get("expansion"), ast);
        }
        return null;
      case "isFunction":
        if ( functionMap.keySet().contains(ast.getTypeName()) ) {
           return replace(template.get("expansion"), ast);
        } else {
          return replace(template.get("otherwise"), ast);
        }
      case "itemKey":
        return lookup("itemKey");
      case "expandItem":
        return expand(ast);
      case "literalItem":
        return ast;
      case "typeName":
        return AST.create("IDLit", ast.getTypeName());
      case "hasArg":
        assert template.isMembers();
        assert template.hasMember("arg");
        assert template.hasMember("name");
        argName = dereference(template.get("name"));
        if (ast.hasMember(argName)) {
          return replace(template.get("arg"), ast);
        }
        return null; // skip this one
      default:
        if ( functionMap.containsKey(template.getTypeName())) {
          pushStack();
          // TODO: This should not try to access "args", but rather get all the different members and pass that through somehow.
          for ( String name: template.getMembers() ) {
            set(name, template.get(name));
          }
          AST arg = ast;
          if ( template.hasMember("ast") ) {
            arg = replace(template.get("ast"), ast);
          }
          AST retval = replace(functionMap.get(template.getTypeName()), arg);
          popStack();
          return retval;
        } else if ( opMap.containsKey(template.getTypeName())) {
          pushStack();
          // TODO: This should not try to access "args", but rather get all the different members and pass that through somehow.
          for ( String name: template.getMembers() ) {
            set(name, template.get(name));
          }
          AST arg = ast;
          if ( template.hasMember("ast") ) {
            arg = replace(template.get("ast"), ast);
          }
          AST retval = replace(opMap.get(template.getTypeName()), arg);
          popStack();
          return retval;
        } else {
          builder.setName(template.getTypeName());
          if (template.isMembers()) {
            for (String member : template.getMembers()) {
              AST child = replace(template.get(member), ast);
              if (child != null) {
                builder.add(member, child);
              }
            }
          } else if (template.isList()) {
            assert template.getMemberList() != null;
            for (AST child : template.getMemberList()) {
              child = replace(child, ast);
              if (child != null) {
                builder.add(child);
              }
            }
          } else {
            assert template.getValue() != null;
            builder.set(template.getValue());
          }
        }

    }
    return builder.create();
  }
}
