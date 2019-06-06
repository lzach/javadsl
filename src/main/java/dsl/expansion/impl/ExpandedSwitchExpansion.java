package dsl.expansion.impl;
/*
 * Manually expanded expansion based on expansion/expansion.dsl
 */

import dsl.ast.AST;
import dsl.ast.ASTBuilder;
import dsl.expansion.Expansion;

import java.sql.RowIdLifetime;
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
    switch (ast.getTypeName()) {
      case "Expansions":
        return expandExpansions(ast);
      case "Expansion":
        return expandExpansion(ast);
      case "member": {
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        builder.add(expand(ast.get("name")));
        if ( ast.hasMember("template") ) {
          builder.add(createPushAST().create());
          builder.add(
              new ASTBuilder("Assign")
                  .add("lhs", AST.IDLit("ast"))
                  .add("rhs",
                      new ASTBuilder("Call")
                          .add("function",
                              new ASTBuilder("Member")
                                  .add("lhs", AST.IDLit("ast"))
                                  .add("rhs", AST.IDLit("get"))
                                  .create())
                          .add("args",
                              new ASTBuilder("ArgList")
                                  .add(
                                      new ASTBuilder("Arg")
                                          .add("name", AST.IDLit("name"))
                                          .add("value",
                                              new ASTBuilder("Call")
                                                  .add("function",
                                                      new ASTBuilder("Member")
                                                          .add("lhs", createCreate().create())
                                                          .add("rhs", AST.IDLit("toString"))
                                                          .create())
                                                  .add("args", AST.emptyList("ArgList"))
                                                  .create())
                                          .create())
                                  .create())
                          .create())
                  .create());
          builder.add(expand(ast.get("template")));
          builder.add(createPopAST().create());
        } else {
          builder.add(
              new ASTBuilder("Call")
                  .add("function",
                      new ASTBuilder("Member")
                          .add("lhs", AST.IDLit("bQue"))
                          .add("rhs", AST.IDLit("push"))
                          .create())
                  .add("args",
                      new ASTBuilder("ArgList")
                          .add(new ASTBuilder("Arg")
                              .add("name", AST.IDLit("value"))
                              .add("value",
                                  new ASTBuilder("Call")
                                      .add("function",
                                          new ASTBuilder("Member")
                                              .add("lhs", AST.IDLit("ast"))
                                              .add("rhs", AST.IDLit("get"))
                                              .create())
                                      .add("args",
                                          new ASTBuilder("ArgList")
                                              .add(
                                                  new ASTBuilder("Arg")
                                                      .add("name", AST.IDLit("name"))
                                                      .add("value",
                                                          new ASTBuilder("Call")
                                                              .add("function",
                                                                  new ASTBuilder("Member")
                                                                      .add("lhs", createCreate().create())
                                                                      .add("rhs", AST.IDLit("toString"))
                                                                      .create())
                                                              .add("args", AST.emptyList("ArgList"))
                                                              .create())
                                                      .create())
                                              .create())
                                      .create())
                                  .create())
                          .create())
                  .create());
          }
          break;
      }
      case "members": {
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        builder.add(createPushAST().create());
        if ( ast.hasMember("name")) {
          builder.add(expand(ast.get("name")));
          builder.add(
              new ASTBuilder("Assign")
                  .add("lhs", AST.IDLit("ast"))
                  .add("rhs",
                      new ASTBuilder("Call")
                          .add("function",
                              new ASTBuilder("Member")
                                  .add("lhs", AST.IDLit("ast"))
                                  .add("rhs", AST.IDLit("get"))
                                  .create())
                          .add("args",
                              new ASTBuilder("ArgList")
                                  .add(
                                      new ASTBuilder("Arg")
                                          .add("name", AST.IDLit("name"))
                                          .add("value",
                                              new ASTBuilder("Call")
                                                  .add("function",
                                                      new ASTBuilder("Member")
                                                          .add("lhs", createCreate().create())
                                                          .add("rhs", AST.IDLit("toString"))
                                                          .create())
                                                  .add("args", AST.emptyList("ArgList"))
                                                  .create())
                                          .create())
                                  .create())
                          .create())
                  .create());
        }
        AST templateAST = expand(ast.get("template"));

        ASTBuilder tmpBuilder = new ASTBuilder("List");
        tmpBuilder.add(templateAST);
        tmpBuilder.add(
            new ASTBuilder("Assign")
                .add("lhs", AST.IDLit("builder"))
                .add("rhs",
                    new ASTBuilder("New")
                        .add("type", AST.IDLit("ASTBuilder"))
                        .add("args",
                            new ASTBuilder("ArgList")
                                .add(new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("ast"))
                                    .add("value",
                                        new ASTBuilder("Convert")
                                            .add("type", AST.IDLit("AST"))
                                            .add("value",
                                                new ASTBuilder("Call")
                                                    .add("function",
                                                        new ASTBuilder("Member")
                                                            .add("lhs", AST.IDLit("bQue"))
                                                            .add("rhs", AST.IDLit("pop"))
                                                            .create())
                                                    .add("args", AST.emptyList("ArgList"))
                                                    .create())
                                            .create())
                                    .create())
                                .create())
                        .create())
                .create());
        tmpBuilder.add(
            new ASTBuilder("Assign")
                .add("lhs", AST.IDLit("builder"))
                .add("rhs",
                    new ASTBuilder("Convert")
                        .add("type", AST.IDLit("ASTBuilder"))
                        .add("value", new ASTBuilder("Call")
                            .add("function",
                                new ASTBuilder("Member")
                                    .add("lhs",
                                        new ASTBuilder("New")
                                            .add("type", AST.IDLit("ASTBuilder"))
                                            .add("args",
                                                new ASTBuilder("ArgList")
                                                    .add(new ASTBuilder("Arg")
                                                        .add("name", AST.IDLit("ast"))
                                                        .add("value", new ASTBuilder("Convert")
                                                            .add("type", AST.IDLit("AST"))
                                                            .add("value",
                                                                new ASTBuilder("Call")
                                                                    .add("function",
                                                                        new ASTBuilder("Member")
                                                                            .add("lhs", AST.IDLit("bQue"))
                                                                            .add("rhs", AST.IDLit("pop"))
                                                                            .create())
                                                                    .add("args", AST.emptyList("ArgList"))
                                                                    .create())
                                                            .create())
                                                        .create())
                                                    .create())
                                            .create())
                                    .add("rhs", AST.IDLit("add"))
                                    .create())
                            .add("args",
                                new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("name", AST.IDLit("ast"))
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
                            .create())
                        .create())
                .create());
        tmpBuilder.add(createPush().create());
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
                                        .add("name", AST.IDLit("typeName"))
                                        .add("value", AST.STRLit("List"))
                                        .create())
                                .create())
                        .create())
                .create()
        );
        builder.add(createPush().create());
        builder.add(
            new ASTBuilder("If")
                .add("cond",
                    new ASTBuilder("Call")
                        .add("function",
                            new ASTBuilder("Member")
                                .add("lhs", AST.IDLit("ast"))
                                .add("rhs", AST.IDLit("isMembers"))
                                .create())
                        .add("args", AST.emptyList("ArgList"))
                        .create())
                .add("code",
                    new ASTBuilder("Block")
                        .add(
                            new ASTBuilder("For")
                                .add("var",
                                    new ASTBuilder("Define")
                                        .add("name", AST.IDLit("member"))
                                        .add("type", AST.IDLit("String"))
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
                                        .add(createPollAST().create())
                                        .add(
                                            new ASTBuilder("Assign")
                                                .add("lhs", AST.IDLit("ast"))
                                                .add("rhs",
                                                    new ASTBuilder("Call")
                                                        .add("function",
                                                            new ASTBuilder("Member")
                                                                .add("lhs", AST.IDLit("ast"))
                                                                .add("rhs", AST.IDLit("get"))
                                                                .create())
                                                        .add("args",
                                                            new ASTBuilder("ArgList")
                                                                .add(
                                                                    new ASTBuilder("Arg")
                                                                        .add("name", AST.IDLit("name"))
                                                                        .add("value", AST.IDLit("member"))
                                                                        .create())
                                                                .create())
                                                        .create())
                                                .create())
                                        .add(tmpBuilder.create())
                                        .create())
                                .create())
                        .create())
                .add("otherwise",
                    new ASTBuilder("If")
                        .add("cond", new ASTBuilder("Call")
                            .add("function",
                                new ASTBuilder("Member")
                                    .add("lhs", AST.IDLit("ast"))
                                    .add("rhs", AST.IDLit("isList"))
                                    .create())
                            .add("args", AST.emptyList("ArgList"))
                            .create())
                        .add("code",
                            new ASTBuilder("Block")
                                .add(
                                    new ASTBuilder("For")
                                        .add("var",
                                            new ASTBuilder("Define")
                                                .add("name", AST.IDLit("member"))
                                                .add("type", AST.IDLit("AST"))
                                                .create())
                                        .add("expr",
                                            new ASTBuilder("Call")
                                                .add("function",
                                                    new ASTBuilder("Member")
                                                        .add("lhs", AST.IDLit("ast"))
                                                        .add("rhs", AST.IDLit("getMemberList"))
                                                        .create())
                                                .add("args", AST.emptyList("ArgList"))
                                                .create())
                                        .add("code",
                                            new ASTBuilder("Block")
                                                .add(
                                                    new ASTBuilder("Assign")
                                                        .add("lhs", AST.IDLit("ast"))
                                                        .add("rhs", AST.IDLit("member"))
                                                        .create())
                                                .add(tmpBuilder.create())
                                                .create())
                                        .create())
                                .create())
                        .add("otherwise", new ASTBuilder("Block").add(tmpBuilder.create()).create())
                        .create())
                .create());
        builder.add(createPopAST().create());
        //createPush(builder);
        break;
      }
      case "concat":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        List<AST> parts = new ArrayList<>();
        if ( ast.getMemberList()[0].isList() ) {

          for (AST member : ast.getMemberList()) {
            AST expansion = expand(member);
//            Collections.addAll(parts, expansion.getMemberList());
            parts.add(expansion);
          }
        } else {
          for (AST member : ast.getMemberList()) {
            parts.add(expand(member));
          }
        }
        Collections.reverse(parts);

        builder.addAll(parts);
        builder.add(createDefineBuilder().create());
        builder.add(
            new ASTBuilder("Call")
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
                                        .add("value", AST.IDLit(ast.getMemberList()[0].getTypeName()))
                                        .create())
                                .create())
                        .create())
                .create());

        if ( ast.getMemberList()[0].isList() ) {
          for ( int i = 0; i < parts.size(); ++i ) {
            builder.add(createPopAllToBuilder().create());
          }
        } else {
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
                                              new ASTBuilder("Call")
                                                  .add("function",
                                                      new ASTBuilder("Member")
                                                          .add("lhs", createCreate().create())
                                                          .add("rhs", AST.IDLit("toString"))
                                                          .create())
                                                  .add("args", AST.emptyList("ArgList"))
                                                  .create())
                                          .create())
                                  .create())
                          .create());
          for ( int i = 1; i < parts.size(); ++i ) {
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
                                        new ASTBuilder("Add")
                                            .add("lhs",
                                                new ASTBuilder("Call")
                                                    .add("function",
                                                        new ASTBuilder("Member")
                                                            .add("lhs",
                                                                new ASTBuilder("Call")
                                                                    .add("function",
                                                                        new ASTBuilder("Member")
                                                                            .add("lhs", AST.IDLit("builder"))
                                                                            .add("rhs", AST.IDLit("getValue"))
                                                                            .create())
                                                                    .add("args", AST.emptyList("ArgList"))
                                                                    .create())
                                                            .add("rhs", AST.IDLit("toString"))
                                                            .create())
                                                    .add("args", AST.emptyList("ArgList"))
                                                    .create())
                                            .add("rhs",
                                                new ASTBuilder("Call")
                                                    .add("function",
                                                        new ASTBuilder("Member")
                                                            .add("lhs", createCreate().create())
                                                            .add("rhs", AST.IDLit("toString"))
                                                            .create())
                                                    .add("args", AST.emptyList("ArgList"))
                                                    .create())
                                            .create())
                                    .create())
                            .create())
                    .create());
          }
        }
        createPush(builder);
        break;
      case "ast":
        builder.setName("List");
        List<String> members = new ArrayList<>();
        List<AST> children = new ArrayList<>();
        if ( ast.hasMember("members") ) {
          for (AST child : ast.get("members").getMemberList()) {
            builder.add(expand(child.get("value")));
            members.add(child.get("name").toString());
          }
          Collections.reverse(members);
        } else {
          for (AST child : ast.get("list").getMemberList()) {
            children.add(expand(child.get("value")));
          }
          Collections.reverse(children);
          builder.addAll(children);
        }
        builder.add(expand(ast.get("name")));
//        builder.add(createDefineBuilder().create());
        builder.add(
            createPushWithCreate(
                createAssign(
                    createASTCreate(AST.IDLit("IDLit"), AST.IDLit("builder")).create(),
                    createNew(
                        createASTCreate(AST.IDLit("IDLit"), AST.IDLit("ASTBuilder")).create(),
                        createArg("name",
                            new ASTBuilder("Convert")
                                .add("type", AST.IDLit("AST"))
                                .add("value",
                                    new ASTBuilder("Call")
                                        .add("function",
                                            new ASTBuilder("Member")
                                                .add("lhs", AST.IDLit("bQue"))
                                                .add("rhs", AST.IDLit("pop"))
                                                .create())
                                        .add("args", AST.emptyList("ArgList"))
                                        .create()
                                ).create()
                        ).create()
                    ).create()
                ).create()
            ).create());
        builder.add(createDefineBuilder().create());

        if ( ast.hasMember("members") ) {
          for (String member : members) {
            builder.add(
                createPushWithCreate(
                    createCall(
                        createMember(
                            createASTCreate(AST.IDLit("IDLit"), AST.IDLit("builder")).create(),
                            createASTCreate(AST.IDLit("IDLit"), AST.IDLit("add")).create()
                        ).create(),
                        createArg("name", createSTRLit(member).create()).create(),
                        createArg("value", new ASTBuilder("Convert")
                            .add("type", AST.IDLit("AST"))
                            .add("value",
                                new ASTBuilder("Call")
                                    .add("function",
                                        new ASTBuilder("Member")
                                            .add("lhs", AST.IDLit("bQue"))
                                            .add("rhs", AST.IDLit("pop"))
                                            .create())
                                    .add("args", AST.emptyList("ArgList"))
                                    .create()
                            ).create()
                        ).create()
                    ).create()
                ).create());
          }
        } else {
          for (int ignored = 0; ignored < children.size(); ++ignored ) {
            builder.add(
                createPushWithCreate(
                    createCall(
                        createMember(
                            createASTCreate(AST.IDLit("IDLit"), AST.IDLit("builder")).create(),
                            createASTCreate(AST.IDLit("IDLit"), AST.IDLit("add")).create()
                        ).create(),
                        createArg("value", new ASTBuilder("Convert")
                            .add("type", AST.IDLit("AST"))
                            .add("value",
                                new ASTBuilder("Call")
                                    .add("function",
                                        new ASTBuilder("Member")
                                            .add("lhs", AST.IDLit("bQue"))
                                            .add("rhs", AST.IDLit("pop"))
                                            .create())
                                    .add("args", AST.emptyList("ArgList"))
                                    .create()
                            ).create()
                        ).create()
                    ).create()
                ).create());
          }
        }
        builder.add(createPush().create());
        break;
      case "callExpansion":
        return createPushWithCreate(createCall(
            new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("ast"))
                        .add("rhs", AST.IDLit("get"))
                        .create())
                .add("args",
                    new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                            .add("name", AST.IDLit("name"))
                            .add("value", new ASTBuilder("String")
                                .add("value", AST.IDLit("type"))
                                .create())
                            .create())
                        .create())
                .create(),
            createArg("name",
                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("ast")).create())
                .create())
        .create()).create();
      case "isMember":
       return createPushWithCreate(createCall(
           new ASTBuilder("Call")
               .add("function",
                   new ASTBuilder("Member")
                       .add("lhs",
                           createMember(
                               createASTCreate(AST.IDLit("IDLit"), AST.IDLit("ast")).create(),
                               createASTCreate(AST.IDLit("IDLit"), AST.IDLit("isMember")).create()
                           ).create())
                       .add("rhs", AST.IDLit("create"))
                       .create())
               .add("args", AST.emptyList("ArgList"))
               .create()

       ).create()).create();
      case "isList":
        return createPushWithCreate(createCall(
            new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs",
                            createMember(
                                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("ast")).create(),
                                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("isList")).create()
                            ).create())
                        .add("rhs", AST.IDLit("create"))
                        .create())
                .add("args", AST.emptyList("ArgList"))
                .create()

        ).create()).create();
      case "isValue":
        return createPushWithCreate(createCall(
            new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs",
                            createMember(
                                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("ast")).create(),
                                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("isValue")).create()
                            ).create())
                        .add("rhs", AST.IDLit("create"))
                        .create())
                .add("args", AST.emptyList("ArgList"))
                .create()
        ).create()).create();
      case "setValue":
        return createPushWithCreate(createCall(
            new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs",
                            createMember(
                                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("builder")).create(),
                                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("set")).create()
                            ).create())
                        .add("rhs", AST.IDLit("create"))
                        .create())
                .add("args", AST.emptyList("ArgList"))
                .create(),
            createArg("name",
                createASTCreate(AST.IDLit("IDLit"), AST.IDLit("ast")).create())
                .create()
        ).create()).create();
      case "push":
        builder = (ASTBuilder) new ASTBuilder("List")
            .add(createBuilder("List").create())
            .add(expand(ast.get("value")))
            .add(createPushWithCreate(createCall(
                createCreate(
                  createMember(
                      createASTCreate(AST.IDLit("IDLit"), AST.IDLit("bQue")).create(),
                      createASTCreate(AST.IDLit("IDLit"), AST.IDLit("push")).create())
                ).create(),
                createArg("value", createCreate().create()).create())
                .create()).create());
        break;
      case "pop":
        builder = (ASTBuilder) new ASTBuilder("List")
            .add(createBuilder("List").create())
            .add(createPushWithCreate(createCall(
                createCreate(
                    createMember(
                      createASTCreate(AST.IDLit("IDLit"), AST.IDLit("bQue")).create(),
                      createASTCreate(AST.IDLit("IDLit"), AST.IDLit("pop")).create())
                    ).create())
                .create()).create());
        break;
      case "expFunName":
        return new ASTBuilder("List")
            .add(expand(ast.get("type")))
            .add(new ASTBuilder("Call")
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
                                        .add("value", AST.IDLit("IDLit"))
                                        .create())
                                .create())
                        .create())
              .create())
            .add(new ASTBuilder("Call")
                    .add("function",
                        new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("builder"))
                            .add("rhs", AST.IDLit("set"))
                            .create())
                    .add("args",
                        new ASTBuilder("ArgList")
                            .add(
                                new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("name"))
                                    .add("value",
                                        new ASTBuilder("Add")
                                           .add("lhs",
                                               new ASTBuilder("String")
                                                   .add("value", AST.IDLit("expand")).
                                                   create())
                                           .add("rhs", new ASTBuilder("Call")
                                               .add("function",
                                                   new ASTBuilder("Member")
                                                       .add("lhs", createCreate().create())
                                                       .add("rhs", AST.IDLit("toString"))
                                                       .create())
                                               .add("args", AST.emptyList("ArgList"))
                                               .create())
                                           .create())
                                    .create())
                            .create())
                    .create())
            .add(createPush().create())
            .create();

      default:
          builder.setName(ast.getTypeName());
          if (ast.isMembers()) {
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
            createDefineAssignBuilder(ast, builder);
            for (String name : names) {
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
                                                              .add("value", createCreate().create())
                                                              .create())
                                              .create())
                              .create());
            }
            createPush(builder);

          } else if (ast.isList()) {
            assert ast.getMemberList() != null;
            builder.setName("List");
            List<AST> asts = new ArrayList<>();
            for (AST child : ast.getMemberList()) {
              child = expand(child);
              if (child != null) {
                asts.add(child);
              }
            }
            Collections.reverse(asts);
            for (AST child : asts) {
              builder.add(child);
            }

            createDefineAssignBuilder(ast, builder);
            for (AST ignored : asts) {
              builder.add(createPopToBuilder().create());
            }
            createPush(builder);
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
            createPush(builder);
          }
    }

    return builder.create();
  }

  private ASTBuilder createSTRLit(String str) {
    return (ASTBuilder)new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", AST.IDLit("AST"))
                .add("rhs", AST.IDLit("STRLit"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("value"))
                    .add("value", new ASTBuilder("String")
                        .add("value", AST.IDLit(str))
                        .create())
                    .create())
                .create());
  }

  private ASTBuilder createAssign(AST lhs, AST rhs) {
        ASTBuilder builder = (ASTBuilder) new ASTBuilder("New")
            .add("type", AST.IDLit("ASTBuilder"))
            .add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", AST.STRLit("Assign"))
                        .create())
                    .create());
        builder = (ASTBuilder)new ASTBuilder("Call")
            .add("function",
                new ASTBuilder("Member")
                    .add("lhs", builder.create())
                    .add("rhs", AST.IDLit("add"))
                    .create())
            .add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", new ASTBuilder("String")
                            .add("value", AST.IDLit("lhs"))
                            .create())
                        .create())
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", lhs)
                        .create())
                    .create());
        builder = (ASTBuilder)new ASTBuilder("Call")
            .add("function",
                new ASTBuilder("Member")
                    .add("lhs", builder.create())
                    .add("rhs", AST.IDLit("add"))
                    .create())
            .add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", new ASTBuilder("String")
                            .add("value", AST.IDLit("rhs"))
                            .create())
                        .create())
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", rhs)
                        .create())
                    .create());
        return builder;
    }

    private ASTBuilder createNew(AST type, AST ... args) {
        ASTBuilder builder = (ASTBuilder) new ASTBuilder("New")
                             .add("type", AST.IDLit("ASTBuilder"))
                             .add("args",
                                 new ASTBuilder("ArgList")
                                     .add(new ASTBuilder("Arg")
                                         .add("name", AST.IDLit("name"))
                                         .add("value", AST.STRLit("New"))
                                         .create())
                                     .create());
        builder = (ASTBuilder)new ASTBuilder("Call")
          .add("function",
              new ASTBuilder("Member")
                  .add("lhs", builder.create())
                  .add("rhs", AST.IDLit("add"))
                  .create())
          .add("args",
              new ASTBuilder("ArgList")
                  .add(new ASTBuilder("Arg")
                      .add("name", AST.IDLit("name"))
                      .add("value", new ASTBuilder("String")
                          .add("value", AST.IDLit("type"))
                          .create())
                      .create())
                  .add(new ASTBuilder("Arg")
                      .add("name", AST.IDLit("name"))
                      .add("value", type)
                          .create())
                      .create());
        builder = (ASTBuilder)new ASTBuilder("Call")
            .add("function",
                new ASTBuilder("Member")
                    .add("lhs", builder.create())
                    .add("rhs", AST.IDLit("add"))
                    .create())
            .add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", new ASTBuilder("String")
                            .add("value", AST.IDLit("args"))
                            .create())
                        .create())
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", new ASTBuilder("Call")
                            .add("function",
                                new ASTBuilder("Member")
                                    .add("lhs", createArgList(args).create())
                                    .add("rhs", AST.IDLit("create"))
                                    .create())
                            .add("args", AST.emptyList("ArgList"))
                            .create())
                        .create())
                    .create());
        return builder;
  }

  private ASTBuilder createCreateCall(AST ast) {
    return (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", ast)
                .add("rhs", AST.IDLit("create"))
                .create())
        .add("args", AST.emptyList("ArgList"));
  }

  private ASTBuilder createConvert(String type, AST ... args) {
    return createConvert(
        new ASTBuilder("Call")
            .add("function",
                new ASTBuilder("Member")
                    .add("lhs", AST.IDLit("AST"))
                    .add("rhs", AST.IDLit("IDLit"))
                    .create()
            ).add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("value"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit(type))
                                .create())
                        .create())
                    .create()
            ).create(),
        args);

  }

  private ASTBuilder createASTCreate(AST lhs, AST rhs) {
    return (ASTBuilder)new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", AST.IDLit("AST"))
                .add("rhs", AST.IDLit("create"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("value"))
                    .add("value", new ASTBuilder("String")
                        .add("value", lhs)
                        .create())
                    .create())
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("value"))
                    .add("value", new ASTBuilder("String")
                        .add("value", rhs)
                        .create())
                    .create())
                .create());
  }


  private AST expFunName(AST ast) {
    return AST.IDLit("expand" + ast.get("type").getValue());
  }

  private AST expandExpansion(AST ast) {
    ASTBuilder blockAST = new ASTBuilder("Block");
    blockAST.add(createDefineAssignEmptyBuilder().create());
    blockAST.add(createAssignQue().create());
    /* member */
    blockAST.add(expand(ast.get("expansion")));
    blockAST.add(createReturnBuild().create());
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
                    .add(AST.IDLit("java"))
                    .add(AST.IDLit("util"))
                    .add(AST.IDLit("Collections"))
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
            .create());
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
    clsAST.add("attrs", AST.emptyList("AttrList"));
    clsAST.add("base",
            new ASTBuilder("Name")
                    .add(AST.IDLit("dsl")).add(AST.IDLit("expansion")).add(AST.IDLit("Expansion"))
            .create());

    /* members name:functions */
    ASTBuilder functions = new ASTBuilder("List");
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
                                .add( new ASTBuilder("Call")
                                        .add("function", AST.IDLit("update"))
                                        .add("args", AST.emptyList("ArgList"))
                                        .create())
                                .add( new ASTBuilder("Call")
                                        .add("function", AST.IDLit("pushLocal"))
                                        .add("args", AST.emptyList("ArgList"))
                                        .create())
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
                            .add(createDefineAssignEmptyBuilder().create())
                            .add(createAssignQue().create())
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
                                    .add("block", methodListExpansions.create())
                                .create())
                            .add(createReturnBuild().create())
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
                  .add("code", expand(fMeth))
              .create());
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
  private ASTBuilder createBuilder(String name) {
    return createBuilder(
            new ASTBuilder("String")
                .add("value", AST.IDLit(name))
                .create());
  }

  private ASTBuilder createBuilder(AST name) {
    return (ASTBuilder) new ASTBuilder("New")
        .add("type", AST.IDLit("ASTBuilder"))
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("name", AST.IDLit("name"))
                .add("value", name)
                .create())
            .create());
  }

  private ASTBuilder createConvert(AST type, AST ... args) {
    ASTBuilder builder = (ASTBuilder) new ASTBuilder("New")
        .add("type", AST.IDLit("ASTBuilder"))
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit("Convert"))
                                .create())
                        .create())
                .create());
    builder = (ASTBuilder)new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit("type"))
                                .create())
                        .create())
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("value"))
                        .add("value", type)
                        .create())
                .create());
    builder = (ASTBuilder)new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("name"))
                    .add("value", new ASTBuilder("String")
                        .add("value", AST.IDLit("args"))
                        .create())
                    .create())
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("name"))
                    .add("value", new ASTBuilder("Call")
                        .add("function",
                            new ASTBuilder("Member")
                                .add("lhs", createArgList(args).create())
                                .add("rhs", AST.IDLit("create"))
                                .create())
                        .add("args", AST.emptyList("ArgList"))
                        .create())
                    .create())
                .create());
    return builder;
  }

  private ASTBuilder createArg(String name, AST  arg) {
    ASTBuilder builder = createBuilder("Arg");
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit("name"))
                                .create())
                        .create())
                .add(
                    new ASTBuilder("Call")
                        .add("function",
                            new ASTBuilder("Member")
                                .add("lhs", AST.IDLit("AST"))
                                .add("rhs", AST.IDLit("create"))
                                .create())
                        .add("args",
                            new ASTBuilder("ArgList")
                                .add(new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("name"))
                                    .add("value", new ASTBuilder("String")
                                        .add("value", AST.IDLit("IDLit"))
                                        .create())
                                    .create())
                                .add(new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("value"))
                                    .add("value", new ASTBuilder("String")
                                        .add("value", AST.IDLit(name))
                                        .create())
                                    .create())
                                .create())
                        .create())
                .create());

    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit("value"))
                                .create())
                        .create())
                .add(arg)
                .create());
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("create"))
                .create())
        .add("args", AST.emptyList("ArgList"));
    return builder;
  }

  private ASTBuilder createArg(AST arg) {
    ASTBuilder builder = createBuilder("Arg");
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("value"))
                        .add("value", arg)
                        .create())
                .create());
    return builder;
  }

  private ASTBuilder createArgList(AST ... args) {
    ASTBuilder builder = (ASTBuilder) new ASTBuilder("New")
        .add("type", AST.IDLit("ASTBuilder"))
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("name", AST.IDLit("name"))
                .add("value",
                    new ASTBuilder("String")
                        .add("value", AST.IDLit("ArgList"))
                        .create())
                .create())
            .create());
    for (AST ast : args ) {
      builder = (ASTBuilder) new ASTBuilder("Call")
          .add("function",
              new ASTBuilder("Member")
                  .add("lhs", builder.create())
                  .add("rhs", AST.IDLit("add"))
                  .create())
          .add("args",
              new ASTBuilder("ArgList")
                  .add(
                      new ASTBuilder("Arg")
                          .add("name", AST.IDLit("value"))
                          .add("value", ast)
                          .create())
                  .create());
    }
    return builder;
  }

  private ASTBuilder createCall(AST function, AST ... args) {
    ASTBuilder builder =  (ASTBuilder) new ASTBuilder("New")
        .add("type", AST.IDLit("ASTBuilder"))
        .add("args", new ASTBuilder("ArgList")
            .add(
                new ASTBuilder("Arg")
                    .add("name", AST.IDLit("name"))
                    .add("value",
                        new ASTBuilder("String")
                            .add("value", AST.IDLit("Call"))
                            .create())
                    .create())
            .create());
    builder = (ASTBuilder)new ASTBuilder("Call")
                  .add("function",
                      new ASTBuilder("Member")
                          .add("lhs", builder.create())
                          .add("rhs", AST.IDLit("add"))
                          .create())
                  .add("args",
                      new ASTBuilder("ArgList")
                          .add(
                              new ASTBuilder("Arg")
                                  .add("name", AST.IDLit("name"))
                                  .add("value",
                                      new ASTBuilder("String")
                                          .add("value", AST.IDLit("function"))
                                          .create())
                                  .create())
                          .add(
                              new ASTBuilder("Arg")
                                  .add("name", AST.IDLit("value"))
                                  .add("value", function)
                                  .create())
                          .create());

    builder = (ASTBuilder)new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
            .add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", new ASTBuilder("String")
                            .add("value", AST.IDLit("args"))
                            .create())
                        .create())
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value", new ASTBuilder("Call")
                                .add("function",
                                    new ASTBuilder("Member")
                                        .add("lhs", createArgList(args).create())
                                        .add("rhs", AST.IDLit("create"))
                                        .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create())
                        .create())
                    .create());
    return builder;
  }

  private ASTBuilder createMember(AST lhs, AST rhs) {
    ASTBuilder builder = createBuilder("Member");
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit("lhs"))
                                .create())
                        .create())
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("value"))
                        .add("value", lhs)
                        .create())
                .create());
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", builder.create())
                .add("rhs", AST.IDLit("add"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("name"))
                        .add("value",
                            new ASTBuilder("String")
                                .add("value", AST.IDLit("rhs"))
                                .create())
                        .create())
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("value"))
                        .add("value", rhs)
                        .create())
                .create());
     return builder;
  }

  private void createPush(ASTBuilder builder) {
     builder.add(createPush().create());
  }

  private ASTBuilder createPush() {
     return (ASTBuilder) new ASTBuilder("Call")
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
                          .add("value",
                              new ASTBuilder("Call")
                                  .add("function",
                                      new ASTBuilder("Member")
                                          .add("lhs", AST.IDLit("builder"))
                                          .add("rhs", AST.IDLit("create"))
                                          .create())
                                  .add("args", AST.emptyList("ArgList"))
                                  .create()
                              )
                          .create())
                  .create());
  }

  private ASTBuilder createPushWithCreate(AST ast) {
    return (ASTBuilder) new ASTBuilder("Call")
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
                        .add("value",
                            new ASTBuilder("Call")
                                .add("function",
                                    new ASTBuilder("Member")
                                        .add("lhs", ast)
                                        .add("rhs", AST.IDLit("create"))
                                        .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create()
                        ).create())
                .create());
  }

  private void createDefineBuilder(ASTBuilder builder) {
    builder.add(createDefineBuilder().create());
  }
  private ASTBuilder createDefineBuilder() {
    return (ASTBuilder)new ASTBuilder("Assign")
                    .add("lhs", AST.IDLit("builder"))
                    .add("rhs",
                            new ASTBuilder("New")
                                    .add("type", AST.IDLit("ASTBuilder"))
                                    .add("args", AST.emptyList("ArgList"))
                                    .create());
  }

  private void createAssignBuilder(AST ast, ASTBuilder builder) {
    builder.add(createAssignBuilder(ast).create());
  }

  private ASTBuilder createAssignBuilder(AST ast) {
    return (ASTBuilder) new ASTBuilder("Call")
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
  }



  private void createDefineAssignBuilder(AST ast, ASTBuilder builder) {
     createDefineBuilder(builder);
     createAssignBuilder(ast, builder);
  }

  private ASTBuilder createCreate(ASTBuilder builder) {
    return (ASTBuilder) new ASTBuilder("Convert")
        .add("type", AST.IDLit("AST"))
        .add("value",
            new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs", builder.create())
                        .add("rhs", AST.create("IDLit", "create"))
                        .create())
                .add("args", AST.emptyList("ArgList"))
                .create());
  }

  private ASTBuilder createCreate() {
    return (ASTBuilder) new ASTBuilder("Convert")
                .add("type", AST.IDLit("AST"))
                .add("value",
                    new ASTBuilder("Call")
                        .add("function",
                            new ASTBuilder("Member")
                                .add("lhs", AST.create("IDLit", "bQue"))
                                .add("rhs", AST.create("IDLit", "pop"))
                            .create())
                        .add("args", AST.emptyList("ArgList"))
                    .create());
  }


  private ASTBuilder createAssignQue() {

    return (ASTBuilder)new ASTBuilder("Assign")
            .add("lhs",
                    new ASTBuilder("Define")
                            .add("type", AST.IDLit("Deque"))
                            .add("name", AST.IDLit("bQue"))
                            .create())
            .add("rhs",
                    new ASTBuilder("New")
                            .add("type", AST.IDLit("ArrayDeque"))
                            .add("args", AST.emptyList("ArgList"))
                            .create());
  }

  private ASTBuilder createDefineAssignEmptyBuilder() {
    return (ASTBuilder)new ASTBuilder("Assign")
            .add("lhs",
                    new ASTBuilder("Define")
                            .add("type", AST.IDLit("ASTBuilder"))
                            .add("name", AST.IDLit("builder"))
                            .create())
            .add("rhs",
                    new ASTBuilder("New")
                            .add("type", AST.IDLit("ASTBuilder"))
                            .add("args", AST.emptyList("ArgList"))
                            .create());
  }

  private ASTBuilder createDefineAssignQueBuilder() {
    ASTBuilder builder = new ASTBuilder("List");
    builder.add(createAssignQue().create());
    builder.add(createDefineAssignEmptyBuilder().create());
    return builder;
  }

  private ASTBuilder createReturnBuild() {

    return (ASTBuilder)new ASTBuilder("Return")
            .add("value",
                    new ASTBuilder("Call")
                            .add("function",
                                    new ASTBuilder("Member")
                                            .add("lhs", AST.IDLit("builder"))
                                            .add("rhs", AST.IDLit("create"))
                                            .create())
                            .add("args", AST.emptyList("ArgList"))
                            .create());
  }

  private void createPopToBuilder(ASTBuilder builder) {
    builder.add(createPopToBuilder().create());
  }

  private ASTBuilder createPopToBuilder() {
    return (ASTBuilder)new ASTBuilder("Call")
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
                          .add("value", createCreate().create())
                          .create())
                  .create());
  }

  private ASTBuilder createPopAllToBuilder() {
    return (ASTBuilder)new ASTBuilder("Call")
        .add("function",
            new ASTBuilder("Member")
                .add("lhs", AST.IDLit("builder"))
                .add("rhs", AST.IDLit("addAll"))
                .create())
        .add("args",
            new ASTBuilder("ArgList")
                .add(
                    new ASTBuilder("Arg")
                        .add("name", AST.IDLit("ast"))
                        .add("value",
                            new ASTBuilder("Call")
                                .add("function",
                                    new ASTBuilder("Member")
                                        .add("lhs", createCreate().create())
                                        .add("rhs", AST.IDLit("getMemberList"))
                                        .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create())
                        .create())
                .create());
  }


  private ASTBuilder createPushAST() {
      return (ASTBuilder)new ASTBuilder("Call")
            .add("function", AST.IDLit("pushAST"))
            .add("args",
                new ASTBuilder("ArgList")
                    .add(
                        new ASTBuilder("Arg")
                            .add("name", AST.IDLit("ast"))
                            .add("value", AST.IDLit("ast"))
                            .create())
                    .create());
  }


  private ASTBuilder createPopAST() {
    return (ASTBuilder)new ASTBuilder("Assign")
        .add("lhs", AST.IDLit("ast"))
        .add("rhs",
            new ASTBuilder("Call")
                .add("function", AST.IDLit("popAST"))
                .add("args", AST.emptyList("ArgList"))
                .create());
  }

  private ASTBuilder createPop() {
    return (ASTBuilder)
            new ASTBuilder("Call")
                .add("function", AST.IDLit("popAST"))
                .add("args", AST.emptyList("ArgList"));
  }

  private ASTBuilder createPollAST() {
    return (ASTBuilder)new ASTBuilder("Assign")
        .add("lhs", AST.IDLit("ast"))
        .add("rhs",
            new ASTBuilder("Call")
                .add("function", AST.IDLit("peekAST"))
                .add("args", AST.emptyList("ArgList"))
                .create());
  }
}
