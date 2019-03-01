package dsl.tests.expansion;
/**
 * Manually expanded expansion based on examples/expansion/expansion.dsl
 */

import dsl.ast.AST;
import dsl.ast.ASTBuilder;
import dsl.expansion.Expansion;

import java.util.*;

public class ExpandedSwitchExpansion extends Expansion {

  public ExpandedSwitchExpansion(AST expansion) {
    super(expansion);
    update();
    pushStack();
  }

  public AST expand() {
    return expand(expansion);
  }

  public AST expand(AST ast) {
    ASTBuilder builder = new ASTBuilder();
    ASTBuilder call;
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
//      case "expFunName":
//        return expFunName(ast)
//            ;
//      case "expExpansion":
//        return expExpansion(ast);
//      case "getFunArgs":
//        return getFunArgs(ast)
//            ;
//      case "callExpansion":
//        return callExpansion(ast)
//            ;
//      case "callFunction":
//
//            ;
//      case "callOperation":
//
//        ;
//      case "isMember":
//
//        ;
//      case "isList":
//
//        ;
//      case "isValue":
//
//        ;
//      case "isFunction":
//
//        ;
//      case "isOperation":
//
//        ;
//      case "member":
//
//        ;
//      case "members":
//
//        ;
//      case "concat":
//
//        ;
//      case "itemKey":
//
//        ;
//      case "typeName":
//
//        ;
//      case "literalItem":
//
//        ;
//      case "expandItem":
//    ;
//      case "ref":
//        break;
      default:
        if ( functionMap.containsKey(ast.getTypeName())) {

          call = new ASTBuilder();
          builder.setName("List");
          List<String> names = new ArrayList<>();
          List<AST> expandedArgs = new ArrayList<>();
          AST args = getFunArgs(ast);
          for (AST member : args.getMemberList()) {
            AST child = expand(member.get("value"));
            if (child != null) {
              names.add(member.get("name").getValue().toString());
              expandedArgs.add(child);
            }
          }
          Collections.reverse(expandedArgs);
          for ( AST child : expandedArgs) {
            builder.add(child);
          }
          ASTBuilder argBuilder = new ASTBuilder("ArgList");
          for ( String name : names) {
            argBuilder.add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit(name))
                        .add("value",
                            new ASTBuilder("Call")
                                .add("function",
                                    new ASTBuilder("Member")
                                        .add("lhs", AST.IDLit("builder"))
                                        .add("rhs", AST.IDLit("create"))
                                    .create())
                                .add("args", AST.emptyList("ArgList"))
                            .create() )
                    .create());
          }
          builder.add(
              new ASTBuilder("Assign")
                  .add("lhs", AST.IDLit("builder"))
                  .add("rhs",
                      new ASTBuilder("New")
                          .add("type", AST.IDLit("ASTBuilder"))
                          .add("args",
                              new ASTBuilder("Call")
                                  .add("function", AST.IDLit(ast.getTypeName()))
                                  .add("args", argBuilder.create())
                              .create())
                      .create())
              .create());
          builder.add(
              new ASTBuilder("Call")
                  .add("function",
                      new ASTBuilder("Member")
                          .add("lhs", AST.IDLit("bQue"))
                          .add("rhs", AST.IDLit("push"))
                      .create())
                  .add("args",
                      new ASTBuilder("ArgList")
                          .add(
                              new ASTBuilder("Arg")
                                  .add("name", AST.IDLit("name"))
                                  .add("value", AST.IDLit("builder"))
                              .create())
                      .create())
              .create());
          return builder.create();
        } else if ( opParams.containsKey(ast.getTypeName())) {
          builder.setName("List");
          builder.add(
             new ASTBuilder("Call")
                 .add("function", AST.IDLit("pushLocal"))
                 .add("args", AST.emptyList("ArgList"))
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
                          .add("args", AST.emptyList("ArgList"))
                     .create())
                .add("code",
                    new ASTBuilder("Block")
                        .add(
                            new ASTBuilder("Call")
                                .add("function", AST.IDLit("set"))
                                .add("args",
                                    new ASTBuilder("ArgList")
                                        .add(
                                                new ASTBuilder("Arg")
                                                        .add("name", AST.IDLit("name"))
                                                        .add("value", AST.IDLit("varName"))
                                                .create())
                                        .add(
                                            new ASTBuilder("Arg")
                                              .add("name", AST.IDLit("value"))
                                              .add("value",
                                                  new ASTBuilder("Call")
                                                    .add("function",
                                                      new ASTBuilder("Member")
                                                        .add("lhs", AST.IDLit("ast"))
                                                        .add("rhs", AST.IDLit("get"))
                                                      .create())
                                                    .add("args", new AST("ArgList",
                                                        new ASTBuilder("Arg")
                                                                .add("name", AST.IDLit("name"))
                                                                .add("value", AST.IDLit("varName"))
                                                        .create()))
                                                  .create())
                                        .create())
                                    .create())
                            .create())
                    .create())
              .create());
          builder.add(expand(opMap.get(ast.getTypeName())));
          builder.add(
              new ASTBuilder("Call")
                  .add("function", AST.IDLit("popLocal"))
                  .add("args", AST.emptyList("ArgList"))
              .create());
        } else {
          builder.setName(ast.getTypeName());
          if (ast.isMembers()) {
            call = new ASTBuilder();
            builder.setName("List");
            List<String> names = new ArrayList<>();
            for (String member : ast.getMembers()) {
              AST child = expand(ast.get(member));
              if (child != null) {
                names.add(member);
                builder.add(child);
              }
            }
            Collections.reverse(names);
            builder.add(
                new ASTBuilder("Assign")
                    .add("lhs", AST.IDLit("builder"))
                    .add("rhs",
                        new ASTBuilder("New")
                            .add("type", AST.IDLit("ASTBuilder"))
                            .add("args", AST.emptyList("ArgList"))
                            .create())
                    .create());
            call.setName("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("builder"))
                        .add("rhs", AST.IDLit("setName"))
                        .create())
                .add("args",
                    new ASTBuilder("ArgList")
                        .add(
                            new ASTBuilder("Arg")
                                .add("name", AST.IDLit("name"))
                                .add("value",
                                    new ASTBuilder("String")
                                        .add("value", AST.IDLit(ast.getTypeName()))
                                    .create())
                                .create())
                        .create());
            builder.add(call.create());
            for ( String name : names) {
              builder.add(
                  new ASTBuilder("Call")
                      .add("function",
                          new ASTBuilder("Member")
                              .add("lhs", AST.IDLit("builder"))
                              .add("rhs", AST.IDLit("add"))
                              .create())
                      .add("args",
                          new ASTBuilder("ArgList")
                              .add(
                                  new ASTBuilder("Arg")
                                      .add("name", AST.IDLit("name"))
                                      .add("value",
                                          new ASTBuilder("String")
                                              .add("value", AST.IDLit(name))
                                          .create())
                                  .create())
                              .add(
                                  new ASTBuilder("Arg")
                                      .add("name", AST.IDLit("ast"))
                                      .add("value",
                                          new ASTBuilder("Convert")
                                              .add("type", AST.IDLit("AST"))
                                              .add("value",
                                                  new ASTBuilder("Call")
                                                      .add("function",
                                                          new ASTBuilder("Member")
                                                              .add("lhs",
                                                                  new ASTBuilder("Convert")
                                                                      .add("type", AST.IDLit("ASTBuilder"))
                                                                      .add("value",
                                                                          new ASTBuilder("Call")
                                                                              .add("function",
                                                                                  new ASTBuilder("Member")
                                                                                      .add("lhs", AST.create("IDLit", "bQue"))
                                                                                      .add("rhs", AST.create("IDLit", "pop"))
                                                                                  .create())
                                                                              .add("args", AST.emptyList("ArgList"))
                                                                          .create())
                                                                  .create())
                                                              .add("rhs", AST.IDLit("create"))
                                                          .create())
                                                      .add("args", AST.emptyList("ArgList"))
                                                  .create())
                                          .create())
                                  .create())
                          .create())
                  .create());
            }
            builder.add(
                new ASTBuilder("Call")
                    .add("function",
                        new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("bQue"))
                            .add("rhs", AST.IDLit("push"))
                            .create())
                    .add("args",
                        new ASTBuilder("ArgList")
                            .add(
                                new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("name"))
                                    .add("value", AST.IDLit("builder"))
                                    .create())
                            .create())
                    .create());

          } else if (ast.isList()) {
            assert ast.getMemberList() != null;
            call = new ASTBuilder();
            builder.setName("List");
            List<AST> asts = new ArrayList<>();
            for (AST child : ast.getMemberList()) {
              child = expand(child);
              if (child != null) {
                asts.add(child);
              }
            }
            Collections.reverse(asts);
            for ( AST child : asts) {
              builder.add(child);
            }

            builder.add(
                new ASTBuilder("Assign")
                    .add("lhs", AST.IDLit("builder"))
                    .add("rhs",
                        new ASTBuilder("New")
                            .add("type", AST.IDLit("ASTBuilder"))
                            .add("args", AST.emptyList("ArgList"))
                            .create())
                    .create());
            call.setName("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("builder"))
                        .add("rhs", AST.IDLit("setName"))
                    .create())
                .add("args",
                    new ASTBuilder("ArgList")
                        .add(
                            new ASTBuilder("Arg")
                                .add("name", AST.IDLit("name"))
                                .add("value",
                                    new ASTBuilder("String")
                                        .add("value", AST.IDLit(ast.getTypeName()))
                                        .create())
                            .create())
                    .create());
            builder.add(call.create());
            for ( AST ignored : asts) {
              builder.add(
                  new ASTBuilder("Call")
                      .add("function",
                          new ASTBuilder("Member")
                              .add("lhs", AST.IDLit("builder"))
                              .add("rhs", AST.IDLit("add"))
                          .create())
                      .add("args",
                          new ASTBuilder("ArgList")
                              .add(
                                  new ASTBuilder("Arg")
                                      .add("name", AST.IDLit("ast"))
                                      .add("value",
                                          new ASTBuilder("Convert")
                                                  .add("type", AST.IDLit("AST"))
                                                  .add("value",
                                                      new ASTBuilder("Call")
                                                          .add("function",
                                                              new ASTBuilder("Member")
                                                                  .add("lhs",
                                                                      new ASTBuilder("Convert")
                                                                          .add("type", AST.IDLit("ASTBuilder"))
                                                                          .add("value",
                                                                              new ASTBuilder("Call")
                                                                                  .add("function",
                                                                                      new ASTBuilder("Member")
                                                                                          .add("lhs", AST.create("IDLit", "bQue"))
                                                                                          .add("rhs", AST.create("IDLit", "pop"))
                                                                                      .create())
                                                                                  .add("args", AST.emptyList("ArgList"))
                                                                              .create())
                                                                      .create())
                                                                  .add("rhs", AST.IDLit("create"))
                                                              .create())
                                                          .add("args", AST.emptyList("ArgList"))
                                                      .create())
                                          .create())
                                  .create())
                          .create())
                  .create());
            }
            builder.add(
                new ASTBuilder("Call")
                    .add("function",
                        new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("bQue"))
                            .add("rhs", AST.IDLit("push"))
                            .create())
                    .add("args",
                        new ASTBuilder("ArgList")
                            .add(
                                new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("name"))
                                    .add("value", AST.IDLit("builder"))
                                    .create())
                            .create())
                    .create());
          } else {
            assert ast.getValue() != null;
            builder = new ASTBuilder("List");
            builder.add(
                    new ASTBuilder("Assign")
                        .add("lhs", AST.IDLit("builder"))
                        .add("rhs",
                            new ASTBuilder("New")
                                .add("type", AST.IDLit("ASTBuilder"))
                                .add("args",
                                    new ASTBuilder("ArgList")
                                        .add(
                                            new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("name"))
                                                .add("value",
                                                    new ASTBuilder("String")
                                                            .add("value", AST.IDLit(ast.getTypeName()))
                                                  .create())
                                          .create())
                                    .create())
                            .create())
                    .create());
            builder.add(
                    new ASTBuilder("Call")
                        .add("function",
                            new ASTBuilder("Member")
                              .add("lhs", AST.IDLit("builder"))
                              .add("rhs", AST.IDLit("set"))
                            .create())
                        .add("args",
                            new ASTBuilder("ArgList")
                                .add(
                                    new ASTBuilder("Arg")
                                        .add("name", AST.IDLit("value"))
                                        .add("value",
                                                new ASTBuilder("String")
                                                        .add("value", AST.IDLit(ast.getValue().toString()))
                                                .create())
                                    .create())
                            .create())
                    .create());
            builder.add(
                    new ASTBuilder("Call")
                        .add("function",
                                new ASTBuilder("Member")
                                    .add("lhs", AST.IDLit("bQue"))
                                    .add("rhs", AST.IDLit("push"))
                                .create())
                        .add("args",
                            new ASTBuilder("ArgList")
                                .add(
                                    new ASTBuilder("Arg")
                                        .add("name", AST.IDLit("name"))
                                        .add("value", AST.IDLit("builder"))
                                    .create())
                            .create())
                    .create());
          }
        }

    }
    return builder.create();
  }

  private AST getFunArgs(AST ast) {
    ASTBuilder builder = new ASTBuilder("ArgList");
    if ( ! ast.hasMember("ast")) {
      builder.add(
              new ASTBuilder("Arg")
                  .add("name", AST.IDLit("ast"))
                  .add("value", AST.IDLit("ast"))
              .create());
    }
    for ( String name : ast.getMembers()) {
      builder.add(
            new ASTBuilder("Arg")
                  .add("name", AST.IDLit(name))
                  .add("value",
                      new ASTBuilder("Call")
                          .add("function", AST.IDLit("expand"))
                          .add("args",
                              new ASTBuilder("ArgList")
                                  .add(
                                      new ASTBuilder("Arg")
                                          .add("name", AST.IDLit("ast"))
                                          .add("value", ast.get(name))
                                      .create())
                              .create())
                      .create())
            .create());
    }
    return builder.create();
  }

  private AST expExpansion(AST ast) {
    ASTBuilder astList = new ASTBuilder("List");
    astList.add(new ASTBuilder("Assign")
            .add("lhs", AST.create("IDLit", "builder"))
            .add("rhs", new ASTBuilder("New")
                    .add("type", AST.create("IDLit", "ASTBuilder"))
                    .add("args", new AST("ArgList",
                            new ASTBuilder("Arg")
                                    .add("name", AST.create("IDLit", "name"))
                                    .add("value", new ASTBuilder("String")
                                            .add("value", /*typename*/ AST.create("IDLit", ast.getTypeName()))
                                            .create())
                                    .create()))
                    .create())
            .create());
    astList.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                    .add("lhs", AST.create("IDLit", "bQue"))
                    .add("rhs", AST.create("IDLit", "push"))
                    .create())
            .add("args", new AST("ArgList", new ASTBuilder("Arg")
                    .add("name", AST.create("IDLit", "value"))
                    .add("value", AST.create("IDLit", "builder"))
                    .create()))
            .create());
    /* isMember */
    if ( ast.isMembers() ) {
      /* members */
      for ( String key : ast.getMembers() ) {
        ASTBuilder list = new ASTBuilder("List");
        list.add(expand(/*literalItem*/ast.get(key)));
        list.add(new ASTBuilder("Call")
                .add("function",
                        new ASTBuilder("Member")
                                .add("lhs", new ASTBuilder("Convert")
                                        .add("type", AST.create("IDLit", "ASTBuilder"))
                                        .add("value", new ASTBuilder("Call")
                                                .add("function", new ASTBuilder("Member")
                                                        .add("lhs", AST.create("IDLit", "bQue"))
                                                        .add("rhs", AST.create("IDLit", "peek"))
                                                        .create())
                                                .add("args", AST.emptyList("ArgList"))
                                                .create())
                                        .create())
                                .add("rhs", AST.create("IDLit", "add"))
                                .create())
                .add("args", new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                                .add("name", AST.create("IDLit", "name"))
                                .add("value", new ASTBuilder("String")
                                        .add("value", AST.create("IDLit",/*itemKey*/key))
                                        .create())
                                .create())
                        .add(new ASTBuilder("Arg")
                                .add("name", AST.create("IDLit", "value"))
                                .add("value", new ASTBuilder("Call")
                                        .add("function", new ASTBuilder("Member")
                                                .add("lhs", AST.create("IDLit", "builder"))
                                                .add("rhs", AST.create("IDLit", "create")).create())
                                        .add("args", new AST("ArgList", new AST[0]))
                                        .create())
                                .create())
                        .create())
                .create());
        astList.add(list.create());
      }
    }
    /* isList */
    if ( ast.isList() ) {
      for ( AST child : ast.getMemberList() ) {
        ASTBuilder list = new ASTBuilder("List");
        list.add(expand(/*literalItem*/child));
        list.add(new ASTBuilder("Call")
                .add("function",
                        new ASTBuilder("Member")
                                .add("lhs", new ASTBuilder("Convert")
                                        .add("type", AST.create("IDLit", "ASTBuilder"))
                                        .add("value", new ASTBuilder("Call")
                                                .add("function", new ASTBuilder("Member")
                                                        .add("lhs", AST.create("IDLit", "bQue"))
                                                        .add("rhs", AST.create("IDLit", "peek")).create())
                                                .add("args", AST.emptyList("ArgList")).create()).create())
                                .add("rhs", AST.create("IDLit", "add")).create())
                .add("args", new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                                .add("name", AST.create("IDLit", "value"))
                                .add("value", new ASTBuilder("Call")
                                        .add("function", new ASTBuilder("Member")
                                                .add("lhs", AST.create("IDLit", "builder"))
                                                .add("rhs", AST.create("IDLit", "create")).create())
                                        .add("args", AST.emptyList("ArgList")).create()).create()).create()).create());
        astList.add(list.create());
      }
    }
    /* isValue */
    if ( ast.isValue() ) {
      ASTBuilder list = new ASTBuilder("List");
      list.add(new ASTBuilder("Call")
              .add("function",
                      new ASTBuilder("Member")
                              .add("lhs", new ASTBuilder("Convert")
                                      .add("type", AST.create("IDLit", "ASTBuilder"))
                                      .add("value", new ASTBuilder("Call")
                                              .add("function", new ASTBuilder("Member")
                                                      .add("lhs", AST.create("IDLit", "bQue"))
                                                      .add("rhs", AST.create("IDLit", "peek")).create())
                                              .add("args", new AST("ArgList",new AST[0])).create()).create())
                              .add("rhs", AST.create("IDLit", "set")).create())
              .add("args", new ASTBuilder("ArgList")
                      .add(new ASTBuilder("Arg")
                              .add("name", AST.create("IDLit", "value"))
                              .add("value", new ASTBuilder("Call")
                                      .add("function", new ASTBuilder("Member")
                                              .add("lhs", AST.create("IDLit", "builder"))
                                              .add("rhs", AST.create("IDLit", "create")).create())
                                      .add("args", new AST("ArgList", new AST[0])).create()).create()).create()).create());
      astList.add(list.create());
    }
    astList.add(new ASTBuilder("Assign")
            .add("lhs", AST.create("IDLit", "builder"))
            .add("rhs",
                    new ASTBuilder("Convert")
                        .add("type", AST.create("IDLit", "ASTBuilder"))
                        .add("value",
                            new ASTBuilder("Convert")
                                .add("type", AST.IDLit("AST"))
                                .add("value",
                                    new ASTBuilder("Call")
                                        .add("function",
                                            new ASTBuilder("Member")
                                                .add("lhs",
                                                    new ASTBuilder("Convert")
                                                        .add("type", AST.IDLit("ASTBuilder"))
                                                        .add("value",
                                                            new ASTBuilder("Call")
                                                                .add("function",
                                                                    new ASTBuilder("Member")
                                                                        .add("lhs", AST.create("IDLit", "bQue"))
                                                                        .add("rhs", AST.create("IDLit", "pop"))
                                                                    .create())
                                                                .add("args", AST.emptyList("ArgList"))
                                                            .create())
                                                    .create())
                                                .add("rhs", AST.IDLit("create"))
                                            .create()
                                              )
                                        .add("args", AST.emptyList("ArgList"))
                                    .create())
                            .create())
                    .create())
            .create());
    return astList.create();
  }

  private AST expFunName(AST ast) {
    return AST.IDLit("expand" + ast.get("type").getValue());
  }

  private AST expandFunction(AST ast) {
    ASTBuilder builder = new ASTBuilder("Method");
    builder.add("name", /*member*/ast.get("name"));
    ASTBuilder params = new ASTBuilder("ParamList");
    params.add(
            new ASTBuilder("Param")
                .add("name", AST.IDLit("ast"))
                .add("type", AST.IDLit("AST"))
            .create());
    for ( AST child : ast.get("params").getMemberList()) {
      params.add(
              new ASTBuilder("Param")
                  .add("name", child.get("name"))
                  .add("type", child.get("type"))
              .create());
    }
    builder.add("params", params.create());
    builder.add("code",
        new ASTBuilder("Block")
            .add(

                new ASTBuilder("Assign")
                    .add("lhs",
                        new ASTBuilder("Define")
                            .add("type", AST.IDLit("Deque"))
                            .add("name", AST.IDLit("bQue"))
                            .create())
                    .add("rhs",
                        new ASTBuilder("New")
                            .add("type", AST.IDLit("ArrayDeque"))
                            .add("args", AST.emptyList("ArgList"))
                            .create())
                    .create())
            .add(new ASTBuilder("Assign")
                .add("lhs",
                    new ASTBuilder("Define")
                        .add("type", AST.IDLit("ASTBuilder"))
                        .add("name", AST.IDLit("builder"))
                        .create())
                .add("rhs",
                    new ASTBuilder("New")
                        .add("type", AST.IDLit("ASTBuilder"))
                        .add("args", AST.emptyList("ArgList"))
                        .create())
                .create())
            .add(expand(ast.get("expansion")))
            .add(
                new ASTBuilder("Return")
                    .add("value",
                        new ASTBuilder("Call")
                            .add("function",
                                new ASTBuilder("Member")
                                    .add("lhs", AST.IDLit("builder"))
                                    .add("rhs", AST.IDLit("create"))
                                .create())
                            .add("args", AST.emptyList("ArgList"))
                        .create())
                .create())
        .create());
    builder.add("returnType", AST.IDLit("AST"));
    return builder.create();
  }

  private AST expandExpansion(AST ast) {
    ASTBuilder blockAST = new ASTBuilder("Block");
    blockAST.add(
        new ASTBuilder("Assign")
            .add("lhs",
                new ASTBuilder("Define")
                    .add("type", AST.IDLit("ASTBuilder"))
                    .add("name", AST.IDLit("builder"))
                .create())
            .add("rhs",
                new ASTBuilder("New")
                    .add("type", AST.IDLit("ASTBuilder"))
                    .add("args", AST.emptyList("ArgList"))
                .create())
        .create());
    blockAST.add(
        new ASTBuilder("Assign")
            .add("lhs",
                new ASTBuilder("Define")
                     .add("type", AST.IDLit("Deque"))
                     .add("name", AST.IDLit("bQue"))
                .create())
            .add("rhs",
                new ASTBuilder("New")
                    .add("type", AST.IDLit("ArrayDeque"))
                    .add("args", AST.emptyList("ArgList"))
                .create())
        .create());
    /* member */
    blockAST.add(expand(ast.get("expansion")));
//    blockAST.add(
//            new ASTBuilder("Call")
//                 .add("function",
//                         new ASTBuilder("Member")
//                                 .add("lhs", AST.IDLit("builder"))
//                                 .add("rhs", AST.IDLit("add"))
//                         .create())
//                 .add("args",
//                     new ASTBuilder("ArgList")
//                         .add(
//                             new ASTBuilder("Arg")
//                                 .add("name", AST.IDLit("ast"))
//                                 .add("value",
//                                      new ASTBuilder("Call")
//                                          .add("function", AST.IDLit("expand"))
//                                          .add("args",
//                                              new ASTBuilder("Call")
//                                                  .add("function",
//                                                      new ASTBuilder("Member")
//                                                          .add("lhs", AST.IDLit("ast"))
//                                                          .add("rhs", AST.IDLit("get"))
//                                                      .create())
//                                                  .add("args",
//                                                      new ASTBuilder("ArgList")
//                                                          .add(
//                                                              new ASTBuilder("Arg")
//                                                                  .add("name", AST.IDLit("name"))
//                                                                  .add("value",
//                                                                      new ASTBuilder("String")
//                                                                          .add("value", AST.IDLit("expansion"))
//                                                                      .create())
//                                                              .create())
//                                                      .create())
//                                              .create())
//                                      .create())
//                             .create())
//                     .create())
//            .create());
    blockAST.add(
            new ASTBuilder("Return")
              .add("value",
                  new ASTBuilder("Call")
                      .add("function",
                          new ASTBuilder("Member")
                              .add("lhs", AST.IDLit("builder"))
                              .add("rhs", AST.IDLit("create"))
                          .create())
                      .add("args", AST.emptyList("ArgList"))
                  .create())
            .create());
    return blockAST.create();
  }

  private AST expandExpansions(AST ast) {
    ASTBuilder listAST = new ASTBuilder("List");
    listAST.add(
            new ASTBuilder("Import")
               .add("name",
                  new ASTBuilder("Name")
                    .add(AST.IDLit("java"))
                    .add(AST.IDLit("util"))
                    .add(AST.IDLit("Arrays"))
                  .create())
            .create());
    listAST.add(
        new ASTBuilder("Import")
            .add("name",
                new ASTBuilder("Name")
                    .add(AST.IDLit("java"))
                    .add(AST.IDLit("util"))
                    .add(AST.IDLit("Map"))
                    .create())
            .create());
    listAST.add(
        new ASTBuilder("Import")
            .add("name",
                new ASTBuilder("Name")
                    .add(AST.IDLit("java"))
                    .add(AST.IDLit("util"))
                    .add(AST.IDLit("HashMap"))
                    .create())
            .create());
    listAST.add(
            new ASTBuilder("Import")
                .add("name",
                    new ASTBuilder("Name")
                        .add(AST.IDLit("java"))
                        .add(AST.IDLit("util"))
                        .add(AST.IDLit("Deque"))
                    .create())
            .create());
    listAST.add(
            new ASTBuilder("Import")
                .add("name",
                    new ASTBuilder("Name")
                        .add(AST.IDLit("java"))
                        .add(AST.IDLit("util"))
                        .add(AST.IDLit("ArrayDeque"))
                    .create())
            .create());
    listAST.add(
            new ASTBuilder("Import")
                .add("name",
                    new ASTBuilder("Name")
                        .add(AST.IDLit("dsl"))
                        .add(AST.IDLit("ast"))
                        .add(AST.IDLit("AST"))
                    .create())
            .create());;
    listAST.add(
        new ASTBuilder("Import")
            .add("name",
                new ASTBuilder("Name")
                    .add(AST.IDLit("dsl"))
                    .add(AST.IDLit("ast"))
                    .add(AST.IDLit("ASTBuilder"))
                .create())
        .create());

    ASTBuilder clsAST = new ASTBuilder("Class");
    clsAST.add("modifier", AST.IDLit("public"));
    clsAST.add("name", /*concat*/AST.IDLit("Expansion" + /* member */ast.get("name").getValue()));
    clsAST.add("attrs",
            new ASTBuilder("AttrList")
              .add(
                  new ASTBuilder("Attr")
                    .add("name", AST.IDLit("funcMap"))
                    .add("type", AST.IDLit("Map"))
                  .create())
            .create());
    clsAST.add("base",
            new ASTBuilder("Name")
                    .add(AST.IDLit("dsl")).add(AST.IDLit("expansion")).add(AST.IDLit("Expansion"))
            .create());

    /* members name:functions */
    ASTBuilder functions = new ASTBuilder("List");
    for ( AST fun : ast.get("functions").getMemberList() ) {
      /* members name:params */
      ASTBuilder funParams = new ASTBuilder("ArgList");
      for ( AST funParam : fun.get("params").getMemberList() ) {
        funParams.add(new ASTBuilder("String").add("value", /* member */funParam.get("name")).create());
      }
      functions.add(
              new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                      .add("lhs", AST.IDLit("funcMap"))
                      .add("rhs", AST.IDLit("put"))
                    .create())
                .add("args",
                    new ASTBuilder("ArgList")
                        .add(
                            new ASTBuilder("Arg")
                                .add("name", AST.IDLit("key"))
                                .add("value",
                                    new ASTBuilder("String")
                                      .add("value", /*member*/fun.get("name"))
                                    .create())
                            .create())
                        .add(
                            new ASTBuilder("Arg")
                                .add("name", AST.IDLit("value"))
                                .add("value",
                                    new ASTBuilder("Call")
                                        .add("function",
                                            new ASTBuilder("Member")
                                                .add("lhs", AST.IDLit("Arrays"))
                                                .add("rhs", AST.IDLit("asList"))
                                            .create())
                                        .add("args", funParams.create())
                                    .create())
                            .create())
                    .create())
              .create());
    }
    clsAST.add("cons",
            new ASTBuilder("ConsList")
                .add(
                    new ASTBuilder("Constructor")
                        .add("params",
                            new ASTBuilder("ParamList")
                                .add(
                                    new ASTBuilder("Param")
                                        .add("name", AST.IDLit("ast"))
                                        .add("type", AST.IDLit("AST"))
                                    .create())
                            .create())

                        .add("code",
                            new ASTBuilder("Block")
                                .add(
                                    new ASTBuilder("Call")
                                        .add("function", AST.IDLit("super"))
                                        .add("args",
                                            new ASTBuilder("ArgList")
                                                .add(
                                                    new ASTBuilder("Arg")
                                                        .add("name", AST.IDLit("expansion"))
                                                        .add("value", AST.IDLit("ast"))
                                                    .create())
                                            .create())
                                    .create())
                              .add(
                                  new ASTBuilder("Assign")
                                    .add("lhs", AST.IDLit("funcMap"))
                                    .add("rhs",
                                        new ASTBuilder("New")
                                          .add("type", AST.IDLit("HashMap"))
                                          .add("args", AST.emptyList("ArgList"))
                                        .create())
                                  .create())
                              .add(functions.create())
                            .create())
                    .create())
            .create());

    /* concat */
    ASTBuilder methodList = new ASTBuilder("MethodList");
    /* members name:expansions */
    ASTBuilder methodListExpansions = new ASTBuilder("List");
    for (AST mLExp : ast.get("expansions").getMemberList()) {
      methodListExpansions.add(
              new ASTBuilder("Case")
                 .add("value",
                         new ASTBuilder("String")
                                 .add("value", /* member */mLExp.get("type"))
                         .create())
                 .add("block", callExpansion(/*literalItem*/mLExp))
              .create());
    }
    for (AST mLExp : ast.get("functions").getMemberList()) {
      methodListExpansions.add(
          new ASTBuilder("Case")
              .add("value",
                  new ASTBuilder("String")
                      .add("value", /* member */mLExp.get("name"))
                      .create())
              .add("block", callFunction(/*literalItem*/mLExp))
              .create());
    }
    for (AST mLExp : ast.get("operations").getMemberList()) {
      methodListExpansions.add(
          new ASTBuilder("Case")
              .add("value",
                  new ASTBuilder("String")
                      .add("value", /* member */mLExp.get("name"))
                      .create())
              .add("block", expand(/*literalItem*/mLExp))
              .create());
    }

    methodList.add(
            new ASTBuilder("Method")
                    .add("returnType", AST.IDLit("AST"))
                    .add("name", AST.IDLit("expand"))
                    .add("params",
                        new ASTBuilder("ParamList")
                          .add(
                              new ASTBuilder("Param")
                                 .add("name", AST.IDLit("ast"))
                                 .add("type", AST.IDLit("AST"))
                              .create())
                        .create())
                    .add("code",
                        new ASTBuilder("Block")
                            .add(
                                new ASTBuilder("Assign")
                                  .add("lhs",
                                      new ASTBuilder("Define")
                                          .add("type", AST.IDLit("ASTBuilder"))
                                          .add("name", AST.IDLit("builder"))
                                          .create())
                                  .add("rhs",
                                      new ASTBuilder("New")
                                          .add("type", AST.IDLit("ASTBuilder"))
                                          .add("args", AST.emptyList("ArgList"))
                                          .create())
                                .create())
                            .add(
                                new ASTBuilder("Assign")
                                    .add("lhs",
                                        new ASTBuilder("Define")
                                            .add("type", AST.IDLit("Deque"))
                                            .add("name", AST.IDLit("bQue"))
                                            .create())
                                    .add("rhs",
                                        new ASTBuilder("New")
                                            .add("type", AST.IDLit("ArrayDeque"))
                                            .add("args", AST.emptyList("ArgList"))
                                            .create())
                                .create())
                            .add(
                                new ASTBuilder("Select")
                                    .add("value",
                                        new ASTBuilder("Call")
                                            .add("function",
                                                new ASTBuilder("Member")
                                                  .add("lhs", AST.IDLit("ast"))
                                                  .add("rhs", AST.IDLit("getTypeName"))
                                                .create())
                                            .add("args", AST.emptyList("ArgList"))
                                        .create())
                                    .add("block",
                                        new ASTBuilder("List")
                                          .add(methodListExpansions.create())
                                        .create())
                                .create())
                            .add(
                                new ASTBuilder("Return")
                                    .add("value",
                                        new ASTBuilder("Call")
                                            .add("function",
                                                new ASTBuilder("Member")
                                                    .add("lhs", AST.IDLit("builder"))
                                                    .add("rhs", AST.IDLit("create"))
                                                .create())
                                            .add("args", AST.emptyList("ArgList"))
                                        .create())
                                .create())
                        .create())
            .create());
    /* members name:expansions */
    for ( AST fMeth : ast.get("expansions").getMemberList() ) {
      methodList.add(
              new ASTBuilder("Method")
                  .add("returnType", AST.IDLit("AST"))
                  .add("name", expFunName(fMeth))
                  .add("params",
                      new ASTBuilder("ParamList")
                          .add(
                              new ASTBuilder("Param")
                                .add("name", AST.IDLit("ast"))
                                .add("type", AST.IDLit("AST"))
                              .create())
                      .create())
                  .add("code", expandExpansion(fMeth))
              .create());
    }
    /* members name:functions */
    for ( AST fMeth : ast.get("functions").getMemberList() ) {
      methodList.add(expandFunction(fMeth));
    }

    clsAST.add("methods", methodList.create());
    listAST.add(clsAST.create());
    return listAST.create();
  }

  private AST callExpansion(AST ast) {
    return
        new ASTBuilder("Return")
            .add("value",
                new ASTBuilder("Call")
                    .add("function", expFunName(ast))
                    .add("args",
                        new ASTBuilder("ArgList")
                            .add(
                                new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("ast"))
                                    .add("value", AST.IDLit("ast"))
                                    .create())
                            .create())
                    .create())
            .create();
  }

  private AST callFunction(AST ast) {
    return
        new ASTBuilder("Return")
            .add("value",
                new ASTBuilder("Call")
                    .add("function", ast.get("name"))
                    .add("args",
                        new ASTBuilder("ArgList")
                            .add(
                                new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("ast"))
                                    .add("value", AST.IDLit("ast"))
                                    .create())
                            .create())
                    .create())
            .create();
  }


}
