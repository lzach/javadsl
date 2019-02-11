package dsl.expansion.expansion;
/**
 * Manually expanded expansion based on examples/expansion/expansion.dsl
 */

import dsl.ast.AST;
import dsl.ast.ASTBuilder;

import java.util.*;

public class ExpandedExpansion {
  private AST expansion;
  private final Map<String, AST> functionMap = new HashMap<>();
  private final Map<String, List<String>> params = new HashMap<>();

  public ExpandedExpansion(AST expansion) {
    this.expansion = expansion;
    update();
  }

  private void update() {
    assert Objects.equals(expansion.getTypeName(), "Expansions");

    for (AST child : expansion.get("functions").getMemberList()) {
      assert (Objects.equals(child.getTypeName(), "Function"));
      String type = dereference(child.get("name"));
      AST ast = child.get("expansion");
      functionMap.put(type, ast);

      List<String> pList = new ArrayList<>();
      params.put(type, pList);
      for (AST param : child.get("params").getMemberList()) {
        pList.add(dereference(param.get("name")));
      }
    }
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

  public AST expandParams(AST param) {
    switch (param.getMemberType()) {
      case "literalItem":
        return AST.create("IDLit", "ast");

      default:
        return new AST("Lambda", new Object[]{"params", new AST("ParamList",
            new AST("Arg", new Object[]{"name", AST.create("IDLit", "ast")},
                new Object[]{"type", AST.create("IDLit", "AST")}))},
            new Object[]{"code", param});
    }
  }

  public AST expandExpansions(AST ast) {
    ASTBuilder listAST = new ASTBuilder("List");
    listAST.add(new AST("Import", new Object[]{"name", new AST("Name", AST.create("IDLit", "java"), AST.create("IDLit", "util"), AST.create("IDLit", "Arrays"))}));
    listAST.add(new AST("Import", new Object[]{"name", new AST("Name", AST.create("IDLit", "java"), AST.create("IDLit", "util"), AST.create("IDLit", "Map"))}));
    listAST.add(new AST("Import", new Object[]{"name", new AST("Name", AST.create("IDLit", "java"), AST.create("IDLit", "util"), AST.create("IDLit", "HashMap"))}));
    listAST.add(new AST("Import", new Object[]{"name", new AST("Name", AST.create("IDLit", "java"), AST.create("IDLit", "util"), AST.create("IDLit", "ArrayDeque"))}));
    listAST.add(new AST("Import", new Object[]{"name", new AST("Name", AST.create("IDLit", "domain2"), AST.create("IDLit", "ast"), AST.create("IDLit", "AST"))}));
    listAST.add(new AST("Import", new Object[]{"name", new AST("Name", AST.create("IDLit", "domain2"), AST.create("IDLit", "ast"), AST.create("IDLit", "ASTBuilder"))}));

    ASTBuilder clsAST = new ASTBuilder("Class");
    clsAST.add("modifier", AST.create("IDLit", "public"));
    clsAST.add("name", /*concat*/AST.create("IDLit", "Expansion" + /* member */ast.get("name").getValue()));
    clsAST.add("attrs", AST.create("AttrList", new AST("Attr", new Object[]{"name", AST.create("IDLit", "funcMap")},  new Object[]{"type", AST.create("IDLit", "Map")})));
    clsAST.add("base", new AST("Name", AST.create("IDLit", "domain2"), AST.create("IDLit", "expansion"), AST.create("IDLit", "Expansion")));

    /* members name:functions */
    ASTBuilder functions = new ASTBuilder("List");
    for ( AST fun : ast.get("functions").getMemberList() ) {
      /* members name:params */
      ASTBuilder funParams = new ASTBuilder("ArgList");
      for ( AST funParam : fun.get("params").getMemberList() ) {
        funParams.add(new AST("String", new Object[]{"value", /* member */funParam.get("name")}));
      }
      functions.add(new AST("Call", new Object[]{"function", new AST("Member", new Object[]{"lhs", AST.create("IDLit", "funcMap")}, new Object[]{"rhs", AST.create("IDLit", "put")})},
                                          new Object[]{"args", new AST("ArgList", new AST("Arg", new Object[]{"name", AST.create("IDLit", "key")}, new Object[]{"value", new AST("String", new Object[]{"value", /*member*/ast.get("name")})}),
                                                                                        new AST("Arg", new Object[]{"name", AST.create("IDLit", "value")}, new Object[]{"value",
                                                                                                                                  new AST("Call",
                                                                                                                                      new Object[]{"function",
                                                                                                                                       new AST("Member", new Object[]{"lhs", AST.create("IDLit", "Arrays")},
                                                                                                                                                               new Object[]{"rhs", AST.create("IDLit", "asList")})
                                                                                                                                      }, new Object[]{"args", funParams.create()})}))}));
    }
    clsAST.add("cons", new ASTBuilder("ConsList").add(new AST("Constructor",
                    new Object[]{"params", new AST("ParamList", (AST)null)},
                    new Object[]{"code", new ASTBuilder("Block").add(
                        new AST("Assign", new Object[]{"lhs", AST.create("IDLit", "funcMap")}, new Object[]{"rhs", new AST("New", new Object[]{"type", AST.create("IDLit", "HashMap")}, new Object[]{"args", new AST("ArgList", (AST)null)})})
                    ).add(
                        functions.create()
                    ).create()})).create());

    /* concat */
    ASTBuilder methodList = new ASTBuilder("MethodList");
    /* members name:expansions */
    ASTBuilder methodListExpansions = new ASTBuilder("List");
    for (AST mLExp : ast.get("expansions").getMemberList()) {
      methodListExpansions.add(new AST("Case", new Object[]{"value", new AST("String", new Object[]{"value", /* member */mLExp.get("type")}, new Object[]{"code", callExpansion(/*literalItem*/mLExp)})}));
    }

    methodList.add(new AST("Method", new Object[]{"returnType", AST.create("IDLit", "AST")}, new Object[]{"name", AST.create("IDLit", "expand")}, new Object[]{"params", new AST("ParamList",
                             new AST("Param", new Object[]{"name", AST.create("IDLit", "ast")}, new Object[]{"type", AST.create("IDLit", "AST")}))},
                             new Object[]{"code", new ASTBuilder("Block").add(new AST("Select", new Object[]{"value", new AST("Call",
                                    new Object[]{"function", new AST("Member", new Object[]{"lhs", AST.create("IDLit", ast)},
                                                           new Object[]{"rhs", AST.create("IDLit", "getTypeName")}),
                                    new Object[]{"args", new AST("ArgList", (AST)null)}})}, new Object[]{"code", new AST("Block",
                                    methodListExpansions.create(),
                                 new AST("Return", AST.create("IDLit", "null"))
                             )})).create()}));
    /* members name:expansions */
    for ( AST fMeth : ast.get("expansions").getMemberList() ) {
      methodList.add(new AST("Method", new Object[]{"returnType", AST.create("IDLit", "AST")}, new Object[]{"name", expFunName(fMeth)}, new Object[]{"params", new AST("ParamList",
          new AST("Param", new Object[]{"name", AST.create("IDLit", "ast")}, new Object[]{"type", AST.create("IDLit", "AST")}))},
          new Object[]{"code", expandExpansion(fMeth)}));
    }
    /* members name:functions */
    for ( AST fMeth : ast.get("functions").getMemberList() ) {
      methodList.add(expandFunction(fMeth));
    }

    clsAST.add("methods", methodList.create());

    return listAST.create();
  }

  public AST expandExpansion(AST ast) {
    ASTBuilder blockAST = new ASTBuilder("Block");
    blockAST.add(new AST("Define", new Object[]{"type", AST.create("IDLit", "ASTBuilder")}, new Object[]{"name", AST.create("IDLit", "builder")}));
    blockAST.add(new AST("Define", new Object[]{"type", AST.create("IDLit", "Deque")}, new Object[]{"name", AST.create("IDLit", "bQue")}));
    blockAST.add(new AST("Assign", new Object[]{"lhs", AST.create("IDLit", "bQue")}, new Object[]{"rhs", new AST("New", new Object[]{"type", AST.create("IDLit", "ArrayDeque")}, new Object[]{"args", new AST("ArgsList", (AST)null)})}));
    /* member */
    blockAST.add(dispatch(ast.get("expansion")));
    blockAST.add(new AST("Return", new Object[]{"value", new AST("Call", new Object[]{"function", new AST("Member", new Object[]{"lhs", AST.create("IDLit", "builder")}, new Object[]{"rhs", AST.create("IDLit", "create")})}, new Object[]{"args", new AST("ArgList", (AST)null)})}));
    return blockAST.create();
  }


  private AST expandFunction(AST ast) {
    ASTBuilder methodAST = new ASTBuilder("Method");
    methodAST.add("returnType", AST.create("IDLit", "AST"));
    /* concat */
    ASTBuilder paramsAST = new ASTBuilder("Params");
    paramsAST.add(new AST("Param", new Object[]{"name", AST.create("IDLit", "ast")}, new Object[]{"type", AST.create("IDLit", "AST")}));
    for ( AST child : /*member*/ast.get("params").getMemberList()) {
      paramsAST.add(child);
    }
    methodAST.add("params", paramsAST.create());

    ASTBuilder blockAST = new ASTBuilder("Block");
    blockAST.add(new AST("Define", new Object[]{"type", AST.create("IDLit", "ASTBuilder")}, new Object[]{"name", AST.create("IDLit", "builder")}));
    blockAST.add(new AST("Define", new Object[]{"type", AST.create("IDLit", "Deque")}, new Object[]{"name", AST.create("IDLit", "bQue")}));
    blockAST.add(new AST("Assign", new Object[]{"lhs", AST.create("IDLit", "bQue")}, new Object[]{"rhs", new AST("New", new Object[]{"type", AST.create("IDLit", "ArrayDeque")}, new Object[]{"args", new AST("ArgsList", (AST)null)})}));
    /* member */
    blockAST.add(dispatch(ast.get("expansion")));
    blockAST.add(new AST("Return", new Object[]{"value", new AST("Call", new Object[]{"function", new AST("Member", new Object[]{"lhs", AST.create("IDLit", "builder")}, new Object[]{"rhs", AST.create("IDLit", "create")})}, new Object[]{"args", new AST("ArgList", (AST)null)})}));
    methodAST.add("code", blockAST.create());

    return methodAST.create();
  }

  private AST expFunName(AST method) {
    return method;
  }

  private AST callExpansion(AST ast) {

    return ast;
  }

  private AST dispatch(AST ast) {
    return ast;
  }

  public AST getArgs(AST ast) {
    //TODO: return args for given function
    ASTBuilder builder = new ASTBuilder("ArgList");
    List<String> pList = params.get(ast.getTypeName());
    AST argAST = null;
    if (ast.hasMember("ast")) {
      argAST = ast.get("ast");
    }
    if (argAST == null) {
//      argAST = lookup("ast");
    }
    if (argAST == null) {
      argAST = AST.create("IDLit", "ast");
    }
    builder.add(new AST("Arg", new Object[]{"name", AST.create("IDLit", "ast")}, new Object[]{"value", argAST}));
    for (String name : pList) {
      builder.add(new AST("Arg", new Object[]{"name", AST.create("IDLit", name)}, new Object[]{"value", ast.get(name)}));
    }
    return builder.create();
  }

  public AST isMember(AST expansion, AST ast) {
    if (ast.isMembers()) {
//      return replace(expansion, ast);
    }
    return null;
  }

  public AST isList(AST expansion, AST ast) {
    if (ast.isList()) {
//      return replace(expansion, ast);
    }
    return null;
  }

  public AST isValue(AST expansion, AST ast) {
    if (ast.isValue()) {
//      return replace(expansion, ast);
    }
    return null;
  }

  public AST isFunction(AST expansion, AST otherwise, AST ast) {
    if (functionMap.keySet().contains(ast.getTypeName())) {
//      return replace(expansion, ast);
    } else {
//      return replace(otherwise, ast);
    }
    return null;
  }

  public AST itemKey() {
    return null;// lookup("itemKey");
  }

  public AST expandItem(AST ast) {
    return null;//expand(ast);
  }

  public AST literalItem(AST ast) {
    return ast;
  }
  public AST typeName(AST ast) {
    return AST.create("IDLit", ast.getTypeName());
  }

  public AST hasArg(String argName, AST arg, AST ast) {
    if (ast.hasMember(argName)) {
//      return replace(arg, ast);
    }
    return null; // sk
  }
}
