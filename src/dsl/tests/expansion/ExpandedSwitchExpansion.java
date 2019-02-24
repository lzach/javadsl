package dsl.tests.expansion;
/**
 * Manually expanded expansion based on examples/expansion/expansion.dsl
 */

import dsl.ast.AST;
import dsl.ast.ASTBuilder;

import java.util.*;

public class ExpandedSwitchExpansion {
  private AST expansion;
  private final Map<String, List<String>> functionMap = new HashMap<>();
  private final Map<String, List<String>> opParams = new HashMap<>();
  private final Map<String, AST> opMap = new HashMap<>();
  private final Deque<Deque<Map<String, AST>>> symbols = new ArrayDeque<>();

  public ExpandedSwitchExpansion(AST expansion) {
    this.expansion = expansion;
    update();
    pushStack();
  }

  private void update() {
    assert Objects.equals(expansion.getTypeName(), "Expansions");

    for (AST child : expansion.get("functions").getMemberList()) {
      assert (Objects.equals(child.getTypeName(), "Function"));
      String type = dereference(child.get("name"));

      setParams(child, type, functionMap);
    }

    for (AST child : expansion.get("operations").getMemberList()) {
      assert (Objects.equals(child.getTypeName(), "Operation"));
      String type = dereference(child.get("name"));
      opMap.put(type, child.get("expansion"));
      setParams(child, type, opParams);
    }

  }

  private void setParams(AST child, String type, Map<String, List<String>> opMap) {
    List<String> pList = new ArrayList<>();
    opMap.put(type, pList);
    for (AST param : child.get("params").getMemberList()) {
      pList.add(dereference(param.get("name")));
    }
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



  public static String dereference(AST nameLit) {
    assert nameLit != null;
    //AST refAST = nameLit.get("reference");
    AST refAST = nameLit;
    assert refAST != null;
    Object o = refAST.getValue();
    assert o != null;
    assert o instanceof String;
    return (String) o;
  }


  public AST expand() {
    return expand(expansion);
  }

  private AST expand(AST ast) {
    ASTBuilder builder = new ASTBuilder();
    switch (ast.getTypeName()) {
      case "Expansions":
        return expandExpansions(ast)
            ;
      case "Expansion":
        return expandExpansion(ast)
            ;
      case "Function":
        return expandFunction(ast)
            ;
      case "expFunName":
        return expFunName(ast)
            ;
      case "expExpansion":
        return expExpansion(ast);
      case "getFunArgs":
        return getFunArgs(ast)
            ;
      case "callExpansion":
        return (ast)
            ;
      case "callFunction":

            ;
      case "callOperation":

        ;
      case "isMember":

        ;
      case "isList":

        ;
      case "isValue":

        ;
      case "isFunction":

        ;
      case "isOperation":

        ;
      case "member":

        ;
      case "members":

        ;
      case "concat":

        ;
      case "itemKey":

        ;
      case "typeName":

        ;
      case "literalItem":

        ;
      case "expandItem":
    ;
      default:
        if ( functionMap.containsKey(ast.getTypeName())) {
          builder.setName("Call");
          builder.add("function", AST.create("IDLit", ast.getTypeName()));
          builder.add("args", getFunArgs(ast));
          return builder.create();
        } else if ( opParams.containsKey(ast.getTypeName())) {
          builder.setName("List");
          builder.add(
             new ASTBuilder("Call")
                 .add("function", AST.IDLit("pushLocal"))
                 .add("args", new AST("ArgList", (AST)null))
            .create());
          builder.add(
             new ASTBuilder("For")
                .add("var",
                    new ASTBuilder("Define")
                          .add("type", AST.IDLit("String"))
                          .add("name", AST.IDLit("varName"))
                    .create())
                .add("expr",
                     new ASTBuilder("Call")
                          .add("function",
                              new ASTBuilder("Member")
                                  .add("lhs", AST.IDLit("ast"))
                                  .add("rhs", AST.IDLit("getMembers"))
                              .create())
                          .add("args", new AST("ArgList", (AST)null))
                     .create())
                .add("block",
                    new ASTBuilder("Block")
                        .add(
                            new ASTBuilder("Call")
                                .add("function", AST.IDLit("set"))
                                .add("args",
                                    new ASTBuilder("ArgList")
                                        .add(new ASTBuilder("Arg").add("name", AST.IDLit("name")).add("value", AST.IDLit("varName")).create())
                                        .add(new ASTBuilder("Arg").add("name", AST.IDLit("value")).add("value",
                                            new ASTBuilder("Call")
                                              .add("function",
                                                  new ASTBuilder("Member")
                                                      .add("lhs", AST.IDLit("ast"))
                                                      .add("rhs", AST.IDLit("get"))
                                                  .create())
                                              .add("args", new AST("ArgList",
                                                  new ASTBuilder("Arg").add("name", AST.IDLit("name")).add("value", AST.IDLit("varName")).create()))
                                            .create()
                                        ).create())
                                    .create())
                            .create())
                    .create())
              .create());
          builder.add(expand(opMap.get(ast.getTypeName())));
          builder.add(
              new ASTBuilder("Call")
                  .add("function", AST.IDLit("popLocal"))
                  .add("args", new AST("ArgList", (AST)null))
              .create());
        } else {
          builder.setName(ast.getTypeName());
          if (ast.isMembers()) {
            for (String member : ast.getMembers()) {
              AST child = expand(ast.get(member));
              if (child != null) {
                builder.add(member, child);
              }
            }
          } else if (ast.isList()) {
            assert ast.getMemberList() != null;
            for (AST child : ast.getMemberList()) {
              child = expand(child);
              if (child != null) {
                builder.add(child);
              }
            }
          } else {
            assert ast.getValue() != null;
            AST tmp = lookup(ast.getValue().toString());
            if ( tmp != null ) {
              return tmp;
            } else {
              builder.set(ast.getValue());
            }
          }
        }

    }
    return builder.create();
  }

  private AST getFunArgs(AST ast) {
    ASTBuilder builder = new ASTBuilder("ArgList");
    for ( String name : ast.getMembers()) {
      builder.add(new ASTBuilder("Arg").add("name", AST.IDLit("name")).add("value", expand(ast.get(name))).create());
    }
    return builder.create();
  }

  private AST expExpansion(AST ast) {
    return null;
  }

  private AST expFunName(AST ast) {
    return AST.IDLit("expand" + ast.getTypeName());
  }

  private AST expandFunction(AST ast) {
    ASTBuilder builder = new ASTBuilder("Method");
    builder.add("name", AST.IDLit(ast.getTypeName()));
    ASTBuilder params = new ASTBuilder("ParamList");
    params.add(new ASTBuilder("Arg").add("name", AST.IDLit("ast")).add("type", AST.IDLit("AST")).create());
    for ( AST child : ast.get("params").getMemberList()) {
      params.add(new ASTBuilder("Arg").add("name", child.get("name")).add("type", child.get("type")).create());
    }
    builder.add("params", params.create());
    builder.add(expand(ast.get("expansion")));
    return builder.create();
  }

  private AST expandExpansion(AST ast) {
    return null;
  }

  private AST expandExpansions(AST ast) {
    return null;
  }


}
