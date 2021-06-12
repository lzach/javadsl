package dsl.expansion.impl;
/*
 * Manually expanded expansion based on expansion/expansion.dsl
 */

import dsl.ast.AST;
import dsl.ast.ASTBuilder;
import dsl.ast.ASTListBuilder;
import dsl.ast.ASTMemberBuilder;
import dsl.expansion.Expansion;

import java.sql.RowIdLifetime;

public class ExpandedSwitchFixExpansion extends Expansion {


  public ExpandedSwitchFixExpansion(AST expansion) {
    super(expansion);
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
                                      .add("function", AST.IDLit("expand"))
                                      .add("args",
                                          new ASTBuilder("ArgList")
                                              .add(new ASTBuilder("Arg")
                                                  .add("name", AST.STRLit("ast"))
                                                  .add("value", new ASTBuilder("Call")
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
                                      .create()
                                  ).create())
                          .create())
                  .create());
          }
          break;
      }
      case "static_member": {
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        builder.add(expand(ast.get("name")));
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
        AST templateAST = null ;
        if ( ast.hasMember("template") ) {
          templateAST = expand(ast.get("template"));
        } else {
          templateAST = new ASTBuilder("Call")
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
                                .add("function", AST.IDLit("expand"))
                                .add("args",
                                    new ASTBuilder("ArgList")
                                        .add(new ASTBuilder("Arg")
                                            .add("name", AST.STRLit("ast"))
                                            .add("value", AST.IDLit("ast"))
                                            .create())
                                        .create())
                                .create()
                        ).create())
                    .create())
            .create();
        }
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
                                        .add(createPollAST().create())
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
                                                .add(createPollAST().create())
                                                .create())
                                        .create())
                                .create())
                        .add("otherwise", new ASTBuilder("Block").add(templateAST).add(
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
                .create()).add(
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
                                    .add("rhs", AST.IDLit("set"))
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
                .create()).add(createPush().create()))
                        .create())
                .create());
        builder.add(createPopAST().create());
        //createPush(builder);
        break;
      }
      case "concat":
        builder.setName("List");
        builder.add(new AST("Comment", ast));

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
                                .add("name", AST.IDLit("value"))
                                .add("value",new ASTBuilder("Call")
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

        if ( ast.getMemberList()[0].isList() ) {
          for ( AST member : ast.getMemberList() ) {
            builder.add(expand(member));
            builder.add(createPopAllToBuilder().create());
          }
        } else {
            builder.add(new ASTBuilder("Call")
                .add("function",
                    new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("bQue"))
                        .add("rhs", AST.IDLit("push"))
                        .create())
                .add("args",
                    new ASTBuilder("ArgList")
                        .add(
                            new ASTBuilder("Arg")
                                .add("name", AST.IDLit("value"))
                                .add("value", new ASTBuilder("Call")
                                    .add("function", new ASTBuilder("Member")
                                        .add("lhs", new ASTBuilder("Call")
                                            .add("function", new ASTBuilder("Member")
                                                .add("lhs", new ASTBuilder("New")
                                                    .add("type", AST.IDLit("ASTBuilder"))
                                                    .add("args",
                                                        new ASTBuilder("ArgList")
                                                            .add(new ASTBuilder("Arg")
                                                                .add("name", AST.IDLit("ast"))
                                                                .add("value", new ASTBuilder("Call")
                                                                    .add("function", new ASTBuilder("Member")
                                                                        .add("lhs", AST.IDLit("bQue"))
                                                                        .add("rhs", AST.IDLit("pop"))
                                                                        .create())
                                                                    .add("args", AST.emptyList("ArgList"))
                                                                    .create())
                                                                .create())
                                                            .create())
                                                    .create())
                                                .add("rhs", AST.IDLit("set"))
                                                .create())
                                            .add("args", new ASTBuilder("ArgList")
                                                .add(new ASTBuilder("Arg")
                                                    .add("name", AST.IDLit("value"))
                                                    .add("value", AST.STRLit(""))
                                                    .create())
                                                .create())
                                            .create())
                                        .add("rhs", AST.IDLit("create"))
                                        .create())
                                    .add("args", AST.emptyList("ArgList"))
                                    .create())
                                .create())
                        .create())
                .create());
          for ( AST member : ast.getMemberList() ) {
            builder.add(expand(member));
            builder.add(new ASTBuilder("Assign")
                .add("lhs", AST.IDLit("builder"))
                .add("rhs", new ASTBuilder("New")
                    .add("type", AST.IDLit("ASTBuilder"))
                    .add("args", new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                            .add("name", AST.IDLit("ast"))
                            .add("value", new ASTBuilder("Convert")
                                .add("type", AST.IDLit("AST"))
                                .add("value", new ASTBuilder("Call")
                                    .add("function",
                                        new ASTBuilder("Member")
                                            .add("lhs", AST.create("IDLit", "bQue"))
                                            .add("rhs", AST.create("IDLit", "pop"))
                                            .create())
                                    .add("args", AST.emptyList("ArgList"))
                                    .create()))
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
                                    .add("name", AST.IDLit("value"))
                                    .add("value", new ASTBuilder("Call")
                                        .add("function", new ASTBuilder("Member")
                                            .add("lhs", new ASTBuilder("Call")
                                                .add("function", new ASTBuilder("Member")
                                                    .add("lhs", new ASTBuilder("New")
                                                        .add("type", AST.IDLit("ASTBuilder"))
                                                        .add("args",
                                                            new ASTBuilder("ArgList")
                                                                .add(new ASTBuilder("Arg")
                                                                    .add("name", AST.IDLit("ast"))
                                                                    .add("value", new ASTBuilder("Call")
                                                                        .add("function", new ASTBuilder("Member")
                                                                            .add("lhs", AST.IDLit("bQue"))
                                                                            .add("rhs", AST.IDLit("pop"))
                                                                            .create())
                                                                        .add("args", AST.emptyList("ArgList"))
                                                                        .create())
                                                                    .create())
                                                                .create())
                                                        .create())
                                                    .add("rhs", AST.IDLit("setAdd"))
                                                    .create())
                                                .add("args", new ASTBuilder("ArgList")
                                                    .add(new ASTBuilder("Arg")
                                                        .add("name", AST.IDLit("value"))
                                                        .add("value", new ASTBuilder("Call")
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
                                                        .create())
                                                    .create())
                                                .create())
                                            .add("rhs", AST.IDLit("create"))
                                            .create())
                                        .add("args", AST.emptyList("ArgList"))
                                        .create())
                                    .create())
                            .create())
                    .create());
          }
        }
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

      case "expFunName":
        return new ASTBuilder("List")
            .add(expand(ast.get("type")))
            .add(createDefineBuilder().create())
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

      case "id":
          return new ASTBuilder("Call")
                  .add("function",
                      new ASTBuilder("Member")
                          .add("lhs", AST.IDLit("bQue"))
                          .add("rhs", AST.IDLit("push"))
                          .create())
                  .add("args",
                      new ASTBuilder("ArgList")
                          .add(
                              new ASTBuilder("Arg")
                                  .add("name", AST.IDLit("value"))
                                  .add("value", AST.IDLit("ast"))
                                  .create())
                          .create())
                  .create();
      case "expand":;
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        ASTBuilder get_ast;
        if ( ast.hasMember("ast") ) {
          builder.add(expand(ast.get("ast")));
          get_ast = (ASTBuilder) new ASTBuilder("Call")
                .add("function", new ASTBuilder("Member")
                  .add("lhs", AST.IDLit("ast"))
                  .add("rhs", AST.IDLit("get"))
                )
                .add("args", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("value", new ASTBuilder("Call")
                            .add("function", new ASTBuilder("Member")
                                .add("lhs", new ASTBuilder("Call")
                                    .add("function", new ASTBuilder("Member")
                                        .add("lhs", AST.IDLit("bQue"))
                                        .add("rhs", AST.IDLit("pop"))
                                    )
                                    .add("args", new ASTBuilder("ArgList"))
                                )
                                .add("rhs", AST.IDLit("toString"))
                            )
                            .add("args", new ASTBuilder("ArgList"))
                        )
                    )
                );
        } else {
          get_ast = new ASTBuilder(AST.IDLit("ast"));
        }

        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
              .add("lhs", AST.IDLit("bQue"))
              .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                  .add("name", AST.IDLit("ast"))
                  .add("value", new ASTBuilder("Call")
                      .add("function", AST.IDLit("expand"))
                      .add("args", new ASTBuilder("ArgList")
                          .add(new ASTBuilder("Arg")
                              .add("value", get_ast)
                          )
                      )
                  )
                )
            )
        );
//      (Expansion type:expand expansion:(List
//              (if_member member:ast code:(block
//              (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:name value:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:ast value:($ ast)))))))))
//            )) otherwise:(block
//              (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:name value:(Call function:expand args:(ArgList (Arg name:ast value:(Call function:(Member lhs:ast rhs:get) args:(ArgList (Arg name:ast value:ast))))))))
//            )))
//        ))
        break;
      case "insert":
          ASTBuilder get_i_ast;
          if ( ast.hasMember("ast") ) {
            get_i_ast = (ASTBuilder) new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("ast"))
                            .add("lhs", AST.IDLit("get"))
                    )
                    .add("args", new ASTBuilder("ArgList")
                            .add(new ASTBuilder("Arg")
                                    .add("value", AST.STRLit(ast.get("ast").toString()))
                            )
                    );
          } else {
            get_i_ast = new ASTBuilder(AST.IDLit("ast"));
          }
          builder.add(new ASTBuilder("Call")
                .add("function", new ASTBuilder("Member")
                    .add("lhs", AST.IDLit("bQue"))
                    .add("rhs", AST.IDLit("push"))
                )
                .add("args", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("ast"))
                        .add("value", get_i_ast)
                    )
                )
        );
        break;
//      (Expansion type:insert expansion:(List
//              (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:name value:($ ast))))
//        ))
      case "list_type":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        if ( ast.hasMember("ast") ) {
            builder.add(new ASTBuilder("Call")
                .add("function", AST.IDLit("getListType"))
                .add("code", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("ast"))
                        .add("value", new ASTBuilder("Call")
                            .add("function", new ASTBuilder("Member")
                                    .add("lhs", AST.IDLit("ast"))
                                    .add("rhs", AST.IDLit("get"))
                            )
                            .add("args", new ASTBuilder("ArgList")
                                .add(new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("ast"))
                                    .add("value", AST.STRLit(ast.get("ast").toString()))
                                )
                            )
                        )
                    )
                )
            );
        } else {
            builder.add(new ASTBuilder("Call")
                .add("function", AST.IDLit("getListType"))
                .add("code", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("ast"))
                        .add("value", AST.IDLit("ast"))
                    )
                )
          );
        }
        break;
//        (Expansion type:list_type expansion:(if_member
//        member:ast
//        code:(Call function:getListType args:(ArgList (Arg name:ast value:($ ast))))
//        otherwise:(Call function:getListType args:(ArgList (Arg name:ast value:ast)))
//       ))
      case "if_member":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
//        ASTBuilder member_ast;
//        if ( ast.hasMember("member") ) {
//          member_ast = (ASTBuilder) new ASTBuilder("Call")
//              .add("function", new ASTBuilder("Member")
//                  .add("lhs", AST.IDLit("ast"))
//                  .add("rhs", AST.IDLit("get"))
//              )
//              .add("args", new ASTBuilder("ArgList")
//                  .add(new ASTBuilder("Arg")
//                      .add("name", AST.IDLit("member"))
//                      .add("value", AST.STRLit(ast.get("member").toString()))
//                  )
//              );
//        } else {
//          member_ast = new ASTBuilder(AST.IDLit("ast"));
//        }
        ASTMemberBuilder if_ast = new ASTBuilder("If")
            .add("cond",
                new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("ast"))
                        .add("rhs", AST.IDLit("hasMember"))
                    )
                    .add("args", new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                            .add("name", AST.IDLit("ast"))
                            .add("value",  AST.STRLit(ast.get("member").toString()))
                        )
                    )
            )
            .add("code", expand(ast.get("code")));
        if ( ast.hasMember("otherwise") ) {
          if_ast.add("otherwise", expand(ast.get("otherwise")));
        } else {
            if_ast.add("otherwise", new ASTBuilder("Block")
                .add(new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("bQue"))
                        .add("rhs", AST.IDLit("push"))
                    )
                    .add("args", new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                            .add("name", AST.IDLit("ast"))
                            .add("value", new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", AST.STRLit("List"))
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("create"))
                                )
                                .add("args", new ASTBuilder("ArgList"))
                            )
                        )
                    )
                )
            );
        }
        builder.add(if_ast);
        break;
//        (Expansion type:if_member expansion:(If
//        cond:(Call function:(Member lhs:ast rhs:hasMember) args:(ArgList (Arg name:name value:($ member))))
//        code:(member name:code)
//        otherwise:(if_member member:otherwise code:(member name:otherwise) otherwise:(List))
//       ))
      case "if_labelled":
          builder.setName("List");
          builder.add(new AST("Comment", ast));
//        ASTBuilder member_l_ast;
//        if ( ast.hasMember("member") ) {
//          member_l_ast = (ASTBuilder) new ASTBuilder("Call")
//                  .add("function", new ASTBuilder("Member")
//                          .add("lhs", AST.IDLit("ast"))
//                          .add("rhs", AST.IDLit("get"))
//                  )
//                  .add("args", new ASTBuilder("ArgList")
//                          .add(new ASTBuilder("Arg")
//                                  .add("name", AST.IDLit("member"))
//                                  .add("value", AST.STRLit(ast.get("member").toString()))
//                          )
//                  );
//        } else {
//          member_l_ast = new ASTBuilder(AST.IDLit("ast"));
//        }
          ASTMemberBuilder if_l_ast = new ASTBuilder("If")
              .add("cond",
                  new ASTBuilder("Call")
                      .add("function", new ASTBuilder("Member")
                          .add("lhs", AST.IDLit("ast"))
                          .add("rhs", AST.IDLit("isMembers"))
                      )
                      .add("args", new ASTBuilder("ArgList"))
              )
              .add("code", expand(ast.get("code")));
          if ( ast.hasMember("otherwise") ) {
            if_l_ast.add("otherwise", expand(ast.get("otherwise")));
          } else {
            if_l_ast.add("otherwise", new ASTBuilder("Block")
                .add(new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", AST.IDLit("bQue"))
                        .add("rhs", AST.IDLit("push"))
                    )
                    .add("args", new ASTBuilder("ArgList")
                        .add(new ASTBuilder("Arg")
                            .add("name", AST.IDLit("ast"))
                            .add("value", new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", AST.STRLit("List"))
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("create"))
                                )
                                .add("args", new ASTBuilder("ArgList"))
                            )
                        )
                    )
                ));
          }
          builder.add(if_l_ast);
//        (Expansion type:if_labelled expansion:(If
//        cond:(Call function:(Member lhs:ast rhs:isMembers) args:(ArgList ))
//        code:(member name:code)
//        otherwise:(if_member member:otherwise code:(member name:otherwise) otherwise:(List))
//       ))
        break;
      case "if_list":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        ASTMemberBuilder if_ll_ast = new ASTBuilder("If")
                .add("cond",
                        new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                        .add("lhs", AST.IDLit("ast"))
                                        .add("rhs", AST.IDLit("isList"))
                                )
                                .add("args", new ASTBuilder("ArgList"))
                )
                .add("code", expand(ast.get("code")));
        if ( ast.hasMember("otherwise") ) {
          if_ll_ast.add("otherwise", expand(ast.get("otherwise")));
        } else {
          if_ll_ast.add("otherwise", new ASTBuilder("Block")
              .add(new ASTBuilder("Call")
                  .add("function", new ASTBuilder("Member")
                      .add("lhs", AST.IDLit("bQue"))
                      .add("rhs", AST.IDLit("push"))
                  )
                  .add("args", new ASTBuilder("ArgList")
                      .add(new ASTBuilder("Arg")
                          .add("name", AST.IDLit("ast"))
                          .add("value", new ASTBuilder("Call")
                              .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", AST.STRLit("List"))
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("create"))
                              )
                              .add("args", new ASTBuilder("ArgList"))
                          )
                      )
                  )
              ));
        }
        builder.add(if_ll_ast);
//        (Expansion type:if_list expansion:(If
//        cond:(Call function:(Member lhs:ast rhs:isList) args:(ArgList ))
//        code:(member name:code)
//        otherwise:(if_member member:otherwise code:(member name:otherwise) otherwise:(List))
//       ))
        break;
      case "if_atom":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        ASTMemberBuilder if_a_ast = new ASTBuilder("If")
                .add("cond",
                        new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                        .add("lhs", AST.IDLit("ast"))
                                        .add("rhs", AST.IDLit("isValue"))
                                )
                                .add("args", new ASTBuilder("ArgList"))
                )
                .add("code", expand(ast.get("code")));
        if ( ast.hasMember("otherwise") ) {
          if_a_ast.add("otherwise", expand(ast.get("otherwise")));
        } else {
          if_a_ast.add("otherwise", new ASTBuilder("Block")
              .add(new ASTBuilder("Call")
                  .add("function", new ASTBuilder("Member")
                      .add("lhs", AST.IDLit("bQue"))
                      .add("rhs", AST.IDLit("push"))
                  )
                  .add("args", new ASTBuilder("ArgList")
                      .add(new ASTBuilder("Arg")
                          .add("name", AST.IDLit("ast"))
                          .add("value", new ASTBuilder("Call")
                              .add("function", new ASTBuilder("Member")
                                  .add("lhs", new ASTBuilder("New")
                                      .add("type", AST.IDLit("ASTBuilder"))
                                      .add("args", new ASTBuilder("ArgList")
                                          .add(new ASTBuilder("Arg")
                                              .add("value", AST.STRLit("List"))
                                          )
                                      )
                                  )
                                  .add("rhs", AST.IDLit("create"))
                              )
                              .add("args", new ASTBuilder("ArgList"))
                          )
                      )
                  )
              )
          );
        }
        builder.add(if_a_ast);
//        (Expansion type:if_atom expansion:(If
//        cond:(Call function:(Member lhs:ast rhs:isValue) args:(ArgList ))
//        code:(member name:code)
//        otherwise:(if_member member:otherwise code:(member name:otherwise) otherwise:(List))
//       ))
        break;
      case "AST":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        builder.add(new ASTBuilder("Assign")
            .add("lhs", AST.IDLit("builder"))
            .add("rhs", new ASTBuilder("New")
                .add("type", AST.IDLit("ASTBuilder"))
                .add("args", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("value", AST.STRLit("Call"))
                    )
                )
            )
        );

        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("builder"))
                .add("rhs", AST.IDLit("add"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", AST.STRLit("function"))
                )
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", AST.STRLit("Member"))
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("add"))
                                )
                                .add("args", new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("value", AST.STRLit("lhs"))
                                    )
                                    .add(new ASTBuilder("Arg")
                                        .add("value", new ASTBuilder("Call")
                                            .add("function", new ASTBuilder("Member")
                                                .add("lhs", AST.IDLit("AST"))
                                                .add("rhs", AST.IDLit("IDLit"))
                                            )
                                            .add("args", new ASTBuilder("ArgList")
                                                .add(new ASTBuilder("Arg")
                                                    .add("value", AST.STRLit("bQue"))
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                            .add("rhs", AST.IDLit("add"))
                        )
                        .add("args",  new ASTBuilder("ArgList")
                            .add(new ASTBuilder("Arg")
                                .add("value", AST.STRLit("rhs"))
                            )
                            .add(new ASTBuilder("Arg")
                                .add("value", new ASTBuilder("Call")
                                    .add("function", new ASTBuilder("Member")
                                        .add("lhs", AST.IDLit("AST"))
                                        .add("rhs", AST.IDLit("IDLit"))
                                    )
                                    .add("args", new ASTBuilder("ArgList")
                                        .add(new ASTBuilder("Arg")
                                            .add("value", AST.STRLit("push"))
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        );
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("builder"))
                .add("rhs", AST.IDLit("add"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", AST.STRLit("args")
                    )
                )
                .add(new ASTBuilder("Arg")
                    .add("value", create_args(
                        create_call(
                            create_member(
                                create_new_astbuilder(create_lit(
                                    "STRLit",
                                    new ASTBuilder(AST.STRLit(ast.get("name").getValue().toString()))
                                )),
                                create_lit("IDLit", AST.STRLit("create"))
                            ),
                            create_args()
                        )
                    ))
                )
            )
        );
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", AST.STRLit("List"))
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("add"))
                                )
                                .add("args", new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("value", AST.IDLit("builder"))
                                    )
                                )
                            )
                            .add("rhs", AST.IDLit("create"))
                        )
                        .add("args", new ASTBuilder("ArgList"))
                    )
                )
            )
        );

        if ( ast.hasMember("params") ) {
            for (AST child : ast.get("params").getMemberList() ) {
                builder.add(expand(child.get("ast")));
                pop_and_push(builder);
                pop_pop_and_push_push(builder, child.get("name").toString());
            }
        } else if ( ast.hasMember("list") ) {
            builder.add(expand(ast.get("list")));
            pop_and_push(builder);
            pop_pop_and_push_push(builder);
        } else {
            builder.add(expand(ast.get("atom")));
            pop_and_push(builder);
            pop_pop_set_push_push(builder);
        }
//        (Expansion type:AST expansion:(List
//              (Call function:(Member lhs:bQue rhs:push) args:(ArgList (Arg name:ast value:(New type:ASTBuilder args:(ArgList (Arg name:name value:($ name)))))))
//        (members name:params)
//       ))
        break;
      case "param":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        builder.add(expand(ast.get("ast")));
        builder.add(new ASTBuilder("Assign")
            .add("lhs", AST.IDLit("builder"))
            .add("rhs", new ASTBuilder("New")
                .add("type", AST.IDLit("ASTBuilder"))
                .add("args", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("value", new ASTBuilder("Call")
                            .add("function", new ASTBuilder("Member")
                                .add("lhs", AST.IDLit("bQue"))
                                .add("rhs", AST.IDLit("pop"))
                            )
                            .add("args", new ASTBuilder("ArgList"))
                        )
                    )
                )
            )
        );
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", new ASTBuilder("Call")
                                                    .add("function", new ASTBuilder("Member")
                                                        .add("lhs", AST.IDLit("bQue"))
                                                        .add("rhs", AST.IDLit("pop"))
                                                    )
                                                    .add("args", new ASTBuilder("ArgList")))
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("add"))
                                )
                                .add("args", new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("value", AST.STRLit(ast.get("name").toString()))
                                    )
                                    .add(new ASTBuilder("Arg")
                                        .add("value", new ASTBuilder("Call")
                                            .add("function",  new ASTBuilder("Member")
                                                .add("lhs", AST.IDLit("builder"))
                                                .add("rhs", AST.IDLit("create"))
                                            )
                                            .add("args", new ASTBuilder("ArgList"))
                                        )
                                    )
                                )
                            )
                            .add("rhs", AST.IDLit("create"))
                        )
                        .add("args", new ASTBuilder("ArgList"))
                    )
                )
            )
        );

//        (Expansion type:param expansion:(List
//              (expand ast:ast)
//        (Assign lhs:builder rhs:(Call function:(Member lhs:bQue rhs:pop) args:(ArgList)))
//        (Call function:(Member lhs:(Call function:(Member lhs:bQue rhs:peek) args:(ArgList)) rhs:add) args:(ArgList (Arg name:name value:($ name)) (Arg name:ast value:(Call function:(Member lhs:builder rhs:create) args:(ArgList)))))
//       ))
        break;
      case "block":
        builder.setName("Block");
        builder.add(new AST("Comment", ast));
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("New")
                                .add("type", AST.IDLit("ASTBuilder"))
                                .add("args", new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("value", AST.STRLit("List"))
                                    )
                                )
                            )
                            .add("rhs", AST.IDLit("create"))
                        )
                        .add("args", new ASTBuilder("ArgList"))
                    )
                )
            )
        );
        for (AST child : ast.getMemberList()) {
          builder.add(expand(child));
          builder.add(new ASTBuilder("Assign")
              .add("lhs", AST.IDLit("builder"))
              .add("rhs", new ASTBuilder("New")
                  .add("type", AST.IDLit("ASTBuilder"))
                  .add("args", new ASTBuilder("ArgList")
                      .add(new ASTBuilder("Arg")
                          .add("value", new ASTBuilder("Call")
                              .add("function", new ASTBuilder("Member")
                                  .add("lhs", AST.IDLit("bQue"))
                                  .add("rhs", AST.IDLit("pop"))
                              )
                              .add("args", new ASTBuilder("ArgList")))
                      )
                  )
              )
          );
          builder.add(new ASTBuilder("Call")
              .add("function", new ASTBuilder("Member")
                  .add("lhs", AST.IDLit("bQue"))
                  .add("rhs", AST.IDLit("push"))
              )
              .add("args", new ASTBuilder("ArgList")
                  .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("New")
                                        .add("type", AST.IDLit("ASTBuilder"))
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("value", new ASTBuilder("Call")
                                                    .add("function", new ASTBuilder("Member")
                                                        .add("lhs", AST.IDLit("bQue"))
                                                        .add("rhs", AST.IDLit("pop"))
                                                    )
                                                    .add("args", new ASTBuilder("ArgList"))
                                                )
                                            )
                                        )
                                    )
                                    .add("rhs", AST.IDLit("add"))
                                )
                                .add("args", new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("value", AST.IDLit("builder"))
                                    )
                                ))
                            .add("rhs", AST.IDLit("create"))
                        )
                        .add("args", new ASTBuilder("ArgList"))
                    )
                  )
              )

          );
        }

//        (Expansion type:block expansion:(Block
//              (members )
//      ))
        break;
      case "list":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("New")
                                .add("type", AST.IDLit("ASTBuilder"))
                                .add("args", new ASTBuilder("ArgList")
                                    .add(new ASTBuilder("Arg")
                                        .add("value", AST.STRLit("List"))
                                    )
                                )
                            )
                            .add("rhs", AST.IDLit("create"))
                        )
                        .add("args", new ASTBuilder("ArgList"))
                    )
                )
            )
        );
        for (AST child : ast.getMemberList()) {
          builder.add(expand(child));
          builder.add(new ASTBuilder("Assign")
              .add("lhs", AST.IDLit("builder"))
              .add("rhs", new ASTBuilder("New")
                  .add("type", AST.IDLit("ASTBuilder"))
                  .add("args", new ASTBuilder("ArgList")
                      .add(new ASTBuilder("Arg")
                          .add("value", new ASTBuilder("Call")
                              .add("function", new ASTBuilder("Member")
                                  .add("lhs", AST.IDLit("bQue"))
                                  .add("rhs", AST.IDLit("pop"))
                              )
                              .add("args", new ASTBuilder("ArgList")))
                      )
                  )
              )
          );
          builder.add(new ASTBuilder("Call")
              .add("function", new ASTBuilder("Member")
                  .add("lhs", AST.IDLit("bQue"))
                  .add("rhs", AST.IDLit("push"))
              )
              .add("args", new ASTBuilder("ArgList")
                  .add(new ASTBuilder("Arg")
                      .add("value", new ASTBuilder("Call")
                          .add("function", new ASTBuilder("Member")
                              .add("lhs", new ASTBuilder("Call")
                                  .add("function", new ASTBuilder("Member")
                                      .add("lhs", new ASTBuilder("New")
                                          .add("type", AST.IDLit("ASTBuilder"))
                                          .add("args", new ASTBuilder("ArgList")
                                              .add(new ASTBuilder("Arg")
                                                  .add("value", new ASTBuilder("Call")
                                                      .add("function", new ASTBuilder("Member")
                                                          .add("lhs", AST.IDLit("bQue"))
                                                          .add("rhs", AST.IDLit("pop"))
                                                      )
                                                      .add("args", new ASTBuilder("ArgList"))
                                                  )
                                              )
                                          )
                                      )
                                      .add("rhs", AST.IDLit("add"))
                                  )
                                  .add("args", new ASTBuilder("ArgList")
                                      .add(new ASTBuilder("Arg")
                                          .add("value", AST.IDLit("builder"))
                                      )
                                  ))
                              .add("rhs", AST.IDLit("create"))
                          )
                          .add("args", new ASTBuilder("ArgList"))
                      )
                  )
              )

          );
        }
//        (Expansion type:list expansion:(List
//              (members )
//      ))
        break;
      case "param_list":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        for (AST child : ast.getMemberList()) {
          builder.add(expand(child));
        }
//        (Expansion type:param_list expansion:(List
//              (members )
//      ))
        break;
      case "typeName":
        builder.setName("List");

        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("AST"))
                            .add("rhs", AST.IDLit("create"))
                        )
                        .add("args", new ASTBuilder("ArgList")
                            .add(new ASTBuilder("Arg")
                                .add("value", AST.STRLit("STRLit"))
                            )
                            .add(new ASTBuilder("Arg")
                                .add("value", new ASTBuilder("Call")
                                    .add("function", new ASTBuilder("Member")
                                        .add("lhs", AST.IDLit("ast"))
                                        .add("rhs", AST.IDLit("getTypeName"))
                                    )
                                    .add("args", new ASTBuilder("ArgList"))
                                )
                            )
                        )
                    )
                )
            )
        );
        break;
      case "value":
        builder.setName("List");
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
            )
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("AST"))
                            .add("rhs", AST.IDLit("STRLit"))
                        )
                        .add("args", new ASTBuilder("ArgList")
                            .add(new ASTBuilder("Arg")
                                .add("value", new ASTBuilder("Call")
                                    .add("function", new ASTBuilder("Member")
                                        .add("lhs", new ASTBuilder("Call")
                                            .add("function", new ASTBuilder("Member")
                                                .add("lhs", AST.IDLit("ast"))
                                                .add("rhs", AST.IDLit("getValue"))
                                            )
                                            .add("args", new ASTBuilder("ArgList"))
                                        )
                                        .add("rhs", AST.IDLit("toString"))
                                    )
                                    .add("args", new ASTBuilder("ArgList"))
                                )
                            )
                        )
                    )
                )
            )
        );
        break;
      case "$":
        builder.setName("List");
        builder.add(new AST("Comment", ast));
        if (ast.isValue()) {
          builder.add(new ASTBuilder("Call")
              .add("function", new ASTBuilder("Member")
                  .add("lhs", AST.IDLit("bQue"))
                  .add("rhs", AST.IDLit("push"))
              )
              .add("args", new ASTBuilder("ArgList")
                  .add(new ASTBuilder("Arg")
                      .add("value", new ASTBuilder("Call")
                          .add("function", new ASTBuilder("Member")
                              .add("lhs", create_call(
                                  create_member(
                                      create_lit("IDLit", AST.STRLit("bQue")),
                                      create_lit("IDLit", AST.STRLit("push"))
                                  ),
                                  create_args(
                                      create_call(
                                          create_member(
                                              create_lit("IDLit", AST.STRLit("AST")),
                                              create_lit("IDLit", AST.STRLit("create"))
                                          ),
                                          create_args(
                                              create_lit("STRLit", (ASTBuilder) new ASTBuilder("Call")
                                                  .add("function", new ASTBuilder("Member")
                                                      .add("lhs", AST.IDLit("ast"))
                                                      .add("rhs", AST.IDLit("getTypeName"))
                                                  )
                                                  .add("args", new ASTBuilder("ArgList"))
                                              ),
                                              (ASTBuilder) new ASTBuilder("Call")
                                                  .add("function", new ASTBuilder("Member")
                                                      .add("lhs", AST.IDLit("AST"))
                                                      .add("rhs", AST.IDLit("STRLit"))
                                                  )
                                                  .add("args", new ASTBuilder("ArgList")
                                                      .add(new ASTBuilder("Arg")
                                                          .add("value", new ASTBuilder("Call")
                                                              .add("function", new ASTBuilder("Member")
                                                                  .add("lhs", new ASTBuilder("Call")
                                                                      .add("function", new ASTBuilder("Member")
                                                                          .add("lhs", AST.IDLit("ast"))
                                                                          .add("rhs", AST.IDLit("getValue"))
                                                                      )
                                                                      .add("args", new ASTBuilder("ArgList"))
                                                                  )
                                                                  .add("rhs", AST.IDLit("toString"))
                                                              )
                                                              .add("args", new ASTBuilder("ArgList"))
                                                          )

                                                      )
                                                  )
                                          )
                                      )
                                  )
                              ))
                              .add("rhs", AST.IDLit("create"))
                          )
                          .add("args", new ASTBuilder("ArgList"))
                      )
                  )
              )
          );
//          create_call(
//              create_member(
//                  create_lit("IDLit", AST.STRLit("bQue")),
//                  create_lit("IDLit", AST.STRLit("push"))
//              ),
//              create_args(
//                  create_call(
//                      create_member(
//                          create_lit("IDLit", AST.STRLit("AST")),
//                          create_lit("IDLit", AST.STRLit("create"))
//                      ),
//                      create_args(
//                          (ASTBuilder) new ASTBuilder("Call")
//                              .add("function", new ASTBuilder("Member")
//                                  .add("lhs", AST.IDLit("ast"))
//                                  .add("rhs", AST.IDLit("getTypeName"))
//                              )
//                              .add("args", new ASTBuilder("ArgList"))
//                          ,
//                          (ASTBuilder) new ASTBuilder("Call")
//                              .add("function", new ASTBuilder("Member")
//                                  .add("lhs", AST.IDLit("ast"))
//                                  .add("rhs", AST.IDLit("getValue"))
//                              )
//                              .add("args", new ASTBuilder("ArgList"))
//                      )
//                  )
//
//              )
//          )
        } else {
          builder.add(new ASTBuilder("Call")
              .add("function", new ASTBuilder("Member")
                  .add("lhs", AST.IDLit("bQue"))
                  .add("rhs", AST.IDLit("push"))
              )
              .add("args", new ASTBuilder("ArgList")
                  .add(new ASTBuilder("Arg")
                      .add("value", new ASTBuilder("Call")
                          .add("function", AST.IDLit("expand"))
                          .add("args", new ASTBuilder("ArgList")
                              .add(new ASTBuilder("Arg").add("value", new ASTBuilder("Call")
                                  .add("function", new ASTBuilder("Member")
                                      .add("lhs", AST.IDLit("ast"))
                                      .add("rhs", AST.IDLit("get"))
                                  )
                                  .add("args", new ASTBuilder("ArgList")
                                      .add(new ASTBuilder("Arg")
                                          .add("value", AST.STRLit(ast.getMemberList()[0].toString()))
                                      )
                                  ))
                              )
                          )
                      )
                  )
              )
          );
        }
//        (Expansion type:$ expansion:(if_atom code:(Block
//              (Call
//                      function:(Member lhs:bQue rhs:push)
//        args:(ArgList
//                (Arg name:ast value:(Call
//        function:(Member
//        lhs:(Call
//        function:(Member lhs:ast rhs:getValue)
//        args:(ArgList)
//                                        )
//        rhs:toString
//                                    )
//        args:(ArgList)
//                                 ))
//                              )
//                        )
//                    ) otherwise:(if_list code:(Block
//              (Comment "This is a bit of a hack, technically we should be using a labelled ast here")
//        (members template:(list
//              (Call
//                      function:(Member lhs:bQue rhs:push)
//        args:(ArgList
//                (Arg name:ast value:(Call
//        function:(Member lhs:ast rhs:get)
//        args:(ArgList
//                (Arg name:ast value:$)
//                                        )
//                                    ))
//                                )
//                            )
//                        ))
//                    ) otherwise:(Block))
//       ))

        break;
      default:
          return doStatic(ast);
    }

    return builder.create();
  }

  private ASTBuilder pop_pop_set_push_push(ASTBuilder builder) {
    ASTBuilder args = create_args(
        create_call(
            create_member(
                create_call(
                    create_member(
                        create_lit("IDLit", AST.STRLit("builder")),
                        create_lit("IDLit", AST.STRLit("create"))
                    ),
                    create_args()
                ),
                create_lit("IDLit", AST.STRLit("toString"))
            ),
            create_args()
        )
    );

    builder.add(new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", AST.IDLit("bQue"))
            .add("rhs", AST.IDLit("push"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", create_assign(
                            create_lit("IDLit", AST.STRLit("builder")),
                            create_new_astbuilder(
                                create_call(
                                    create_member(
                                        create_lit("IDLit", AST.STRLit("bQue")),
                                        create_lit("IDLit", AST.STRLit("pop"))
                                    ),
                                    create_args()
                                )
                            )

                        ))
                        .add("rhs", AST.IDLit("create"))
                    )
                    .add("args", new ASTBuilder("ArgList"))
                )
            )
        )
    );
    pop_and_push(builder);
    builder.add(new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", AST.IDLit("bQue"))
            .add("rhs", AST.IDLit("push"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", create_call(
                            create_member(
                                create_lit("IDLit", AST.STRLit("bQue")),
                                create_lit("IDLit", AST.STRLit("push"))
                            ),
                            create_args(
                                create_call(
                                    create_member(
                                        create_call(
                                            create_member(
                                                create_new_astbuilder(
                                                    create_call(
                                                        create_member(
                                                            create_lit("IDLit", AST.STRLit("bQue")),
                                                            create_lit("IDLit", AST.STRLit("pop"))
                                                        ),
                                                        create_args()
                                                    )
                                                ),
                                                create_lit("IDLit", AST.STRLit("set"))
                                            ),
                                            args
                                        ),
                                        create_lit("IDLit", AST.STRLit("create"))
                                    ),
                                    create_args()
                                )
                            )
                        ))
                        .add("rhs", AST.IDLit("create"))
                    )
                    .add("args", new ASTBuilder("ArgList"))
                )
            )
        )
    );
    pop_and_push(builder);
    return builder;
  }

  private ASTBuilder pop_pop_and_push_push(ASTBuilder builder) {
    return pop_pop_and_push_push(builder, null);
  }

  private ASTBuilder pop_pop_and_push_push(ASTBuilder builder, String name) {
    ASTBuilder args;

    if ( name != null ) {
        args = create_args(create_lit("STRLit", AST.STRLit(name)),
            create_call(
                create_member(
                    create_lit("IDLit", AST.STRLit("builder")),
                    create_lit("IDLit", AST.STRLit("create"))
                ),
                create_args()
            ));
    } else {
        args =create_args(
            create_call(
                create_member(
                    create_lit("IDLit", AST.STRLit("builder")),
                    create_lit("IDLit", AST.STRLit("create"))
                ),
                create_args()
            ));
    }
    builder.add(new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", AST.IDLit("bQue"))
            .add("rhs", AST.IDLit("push"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", create_assign(
                            create_lit("IDLit", AST.STRLit("builder")),
                            create_call(
                                create_member(
                                    create_new_astbuilder(
                                        create_lit("STRLit", AST.STRLit("ASTBuilder"))
                                    ),
                                    create_lit("IDLit", AST.STRLit("add"))
                                ),
                                create_args(
                                    create_call(
                                        create_member(
                                            create_lit("IDLit", AST.STRLit("bQue")),
                                            create_lit("IDLit", AST.STRLit("pop"))
                                        ),
                                        create_args()
                                    )
                                )
                            )
                        ))
                        .add("rhs", AST.IDLit("create"))
                    )
                    .add("args", new ASTBuilder("ArgList"))
                )
            )
        )
    );
    pop_and_push(builder);
    builder.add(new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", AST.IDLit("bQue"))
            .add("rhs", AST.IDLit("push"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", create_call(
                            create_member(
                                create_lit("IDLit", AST.STRLit("bQue")),
                                create_lit("IDLit", AST.STRLit("push"))
                            ),
                            create_args(
                                create_call(
                                    create_member(
                                        create_call(
                                            create_member(
                                                create_new_astbuilder(
                                                    create_call(
                                                        create_member(
                                                            create_lit("IDLit", AST.STRLit("bQue")),
                                                            create_lit("IDLit", AST.STRLit("pop"))
                                                        ),
                                                        create_args()
                                                    )
                                                ),
                                                create_lit("IDLit", AST.STRLit("add"))
                                            ),
                                            args
                                        ),
                                        create_lit("IDLit", AST.STRLit("create"))
                                    ),
                                    create_args()
                                )
                            )
                        ))
                        .add("rhs", AST.IDLit("create"))
                    )
                    .add("args", new ASTBuilder("ArgList"))
                )
            )
        )
    );
    pop_and_push(builder);
    return builder;
  }
  private ASTBuilder pop_and_push(ASTBuilder builder) {
    builder.add(new ASTBuilder("Assign")
        .add("lhs", AST.IDLit("builder"))
        .add("rhs", new ASTBuilder("New")
            .add("type", AST.IDLit("ASTBuilder"))
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", AST.IDLit("bQue"))
                            .add("rhs", AST.IDLit("pop"))
                        )
                        .add("args", new ASTBuilder("ArgList"))
                    )
                )
            )
        )
    );
    builder.add(new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", AST.IDLit("bQue"))
            .add("rhs", AST.IDLit("push"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", new ASTBuilder("Call")
                    .add("function", new ASTBuilder("Member")
                        .add("lhs", new ASTBuilder("Call")
                            .add("function", new ASTBuilder("Member")
                                .add("lhs", new ASTBuilder("New")
                                    .add("type", AST.IDLit("ASTBuilder"))
                                    .add("args", new ASTBuilder("ArgList")
                                        .add(new ASTBuilder("Arg")
                                            .add("value", new ASTBuilder("Call")
                                                .add("function", new ASTBuilder("Member")
                                                    .add("lhs", AST.IDLit("bQue"))
                                                    .add("rhs", AST.IDLit("pop"))
                                                )
                                                .add("args", new ASTBuilder("ArgList")))
                                        )
                                    )
                                )
                                .add("rhs", AST.IDLit("add"))
                            )
                            .add("args", new ASTBuilder("ArgList")
                                .add(new ASTBuilder("Arg")
                                    .add("value", new ASTBuilder("Call")
                                        .add("function", new ASTBuilder("Member")
                                            .add("lhs", AST.IDLit("builder"))
                                            .add("rhs", AST.IDLit("create"))
                                        )
                                        .add("args", new ASTBuilder("ArgList"))
                                    )
                                )
                            )
                        )
                        .add("rhs", AST.IDLit("create"))
                    )
                    .add("args", new ASTBuilder("ArgList"))
                )
            )
        )
    );
    return builder;
  }

  private ASTBuilder create_astbuilder(ASTBuilder name) {
    return (ASTBuilder) new ASTBuilder("New")
        .add("type", AST.IDLit("ASTBuilder"))
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", name)
            )
        );
  }

  private ASTBuilder create_new_astbuilder(AST name) {
    return create_new_astbuilder(new ASTBuilder(name));
  }

  private ASTBuilder create_new_astbuilder(ASTBuilder name) {
    ASTBuilder builder = create_astbuilder(new ASTBuilder(AST.STRLit("New")));
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", builder)
            .add("rhs", AST.IDLit("add"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", AST.STRLit("type"))
            )
            .add(new ASTBuilder("Arg")
                .add("value", create_lit("IDLit", AST.STRLit("ASTBuilder")))
            )
        );
    builder = (ASTBuilder) new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", builder)
            .add("rhs", AST.IDLit("add"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", AST.STRLit("args"))
            )
            .add(new ASTBuilder("Arg")
                .add("value", create_args(name))
            )
        );

    return builder;
  }


  private ASTBuilder create_args(ASTBuilder ... args ) {
    ASTBuilder builder = create_astbuilder(new ASTBuilder(AST.STRLit("ArgList")));
    for (ASTBuilder arg : args) {
      builder = (ASTBuilder) new ASTBuilder("Call")
          .add("function", new ASTBuilder("Member")
              .add("lhs", builder)
              .add("rhs", AST.IDLit("add"))
          ).add("args", new ASTBuilder("ArgList")
              .add(new ASTBuilder("Arg")
                  .add("value", new ASTBuilder("Call")
                      .add("function", new ASTBuilder("Member")
                          .add("lhs", create_astbuilder(new ASTBuilder(AST.STRLit("Arg"))))
                          .add("rhs", AST.IDLit("add"))
                      )
                      .add("args", new ASTBuilder("ArgList")
                          .add(new ASTBuilder("Arg").add("value", AST.STRLit("value")))
                          .add(new ASTBuilder("Arg").add("value", arg))
                      )
                  )
              )
          );
    }
    return builder;
  }

  private ASTBuilder create_call(AST function, AST args) {
    return create_call(new ASTBuilder(function), new ASTBuilder(args));
  }
  private ASTBuilder create_call(ASTBuilder function, AST args) {
    return create_call(function, new ASTBuilder(args));
  }

  private ASTBuilder create_call(AST function, ASTBuilder args) {
    return create_call(new ASTBuilder(function), args);
  }

  private ASTBuilder create_call(ASTBuilder function, ASTBuilder args) {
    return create_bin(AST.STRLit("Call"), "function", function, "args", args);
  }

  private ASTBuilder create_member(AST lhs, AST rhs) {

    return create_member(new ASTBuilder(lhs), new ASTBuilder(rhs));
  }
  private ASTBuilder create_member(ASTBuilder lhs, AST rhs) {

    return create_member(lhs, new ASTBuilder(rhs));
  }
  private ASTBuilder create_member(AST lhs, ASTBuilder rhs) {

    return create_member(new ASTBuilder(lhs), rhs);
  }

  private ASTBuilder create_member(ASTBuilder lhs, ASTBuilder rhs) {
    return create_bin( AST.STRLit("Member"), "lhs", lhs, "rhs", rhs);
  }

  private ASTBuilder create_assign(AST lhs, AST rhs) {

    return create_assign(new ASTBuilder(lhs), new ASTBuilder(rhs));
  }
  private ASTBuilder create_assign(ASTBuilder lhs, AST rhs) {

    return create_assign(lhs, new ASTBuilder(rhs));
  }
  private ASTBuilder create_assign(AST lhs, ASTBuilder rhs) {

    return create_assign(new ASTBuilder(lhs), rhs);
  }

  private ASTBuilder create_assign(ASTBuilder lhs, ASTBuilder rhs) {
    return create_bin(AST.STRLit("Assign"), "lhs", lhs, "rhs", rhs);
  }


  private ASTBuilder create_bin(AST name, String firstName, ASTBuilder lhs,  String secondName, ASTBuilder rhs) {
      return create_bin(new ASTBuilder(name), firstName, lhs, secondName, rhs);
  }

  private ASTBuilder create_bin(ASTBuilder name, String firstName, ASTBuilder lhs,  String secondName, ASTBuilder rhs) {
//    ASTBuilder builder = create_new_astbuilder(name);
    ASTBuilder builder = create_astbuilder(name);
    builder = (ASTBuilder)new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", builder)
            .add("rhs", AST.IDLit("add"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", AST.STRLit(firstName))
            )
            .add(new ASTBuilder("Arg")
                .add("value", lhs)
            )
        )
    ;
    builder = (ASTBuilder)new ASTBuilder("Call")
        .add("function", new ASTBuilder("Member")
            .add("lhs", builder)
            .add("rhs", AST.IDLit("add"))
        )
        .add("args", new ASTBuilder("ArgList")
            .add(new ASTBuilder("Arg")
                .add("value", AST.STRLit(secondName))
            )
            .add(new ASTBuilder("Arg")
                .add("value", rhs)
            )
        )
    ;
    return builder;
  }

  private ASTBuilder create_lit(String type, AST value) {
    return create_lit(type, new ASTBuilder(value));
  }

  private ASTBuilder create_lit(String type, ASTBuilder value) {
      return (ASTBuilder) new ASTBuilder("Call")
          .add("function", new ASTBuilder("Member")
              .add("lhs", AST.IDLit("AST"))
              .add("rhs", AST.IDLit(type))
          )
          .add("args", new ASTBuilder("ArgList")
              .add(new ASTBuilder("Arg")
                  .add("value", value)
              )
          );
  }

  private ASTBuilder createASTCreate(AST lhs, AST rhs) {
    return new ASTBuilder("Call")
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
                    .add(AST.IDLit("List"))
                    .create())
            .create());
    listAST.add(
        new ASTBuilder("Import")
            .add("name",
                new ASTBuilder("Name")
                    .add(AST.IDLit("java"))
                    .add(AST.IDLit("util"))
                    .add(AST.IDLit("ArrayList"))
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
    ASTBuilder retval = null;
    if ( ast.hasMember("default_expansion") ) {
      retval = new ASTBuilder(expand(ast.get("default_expansion")));

    } else {
      retval =  new ASTBuilder("Call")
              .add("function", AST.IDLit("doSimpleStatic"))
              .add("args", new ASTBuilder("ArgList")
                  .add(new ASTBuilder("Arg")
                      .add("name", AST.IDLit("ast"))
                      .add("value", AST.IDLit("ast"))
                      .create())
                  .create());
    }
    ASTBuilder defaultBlock = new ASTBuilder("Default");
    defaultBlock.add("block", new ASTBuilder("Block")
                .add(new ASTBuilder("Return")
                    .add("value", retval)
                    .create())
    );

    methodListExpansions.add(defaultBlock.create());


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
                                    .add("value", AST.STRLit("STRLit"))
                                    .create())
                                .add(new ASTBuilder("Arg")
                                    .add("name", AST.IDLit("value"))
                                    .add("value", AST.STRLit(name))
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


  private ASTBuilder createArgList(AST ... args) {
    ASTBuilder builder = new ASTBuilder("New")
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

  private ASTBuilder createDefineBuilder() {
    return (ASTBuilder)new ASTBuilder("Assign")
                    .add("lhs", AST.IDLit("builder"))
                    .add("rhs",
                            new ASTBuilder("New")
                                    .add("type", AST.IDLit("ASTBuilder"))
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
                            .add("type", new ASTBuilder("Generic")
                                .add("type", AST.IDLit("Deque"))
                                .add("gens", new ASTBuilder("List")
                                    .add(AST.IDLit("AST"))
                                    .create())
                                .create())
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

  private ASTBuilder createReturnBuild() {

      return (ASTBuilder)new ASTBuilder("Return")
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
              .create());
  }

  private ASTBuilder createPopAllToBuilder() {
    return (ASTBuilder)new ASTBuilder("List")
        .add(new ASTBuilder("Assign")
            .add("lhs", AST.IDLit("builder"))
            .add("rhs", new ASTBuilder("New")
                .add("type", AST.IDLit("ASTBuilder"))
                .add("args", new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("ast"))
                        .add("value", new ASTBuilder("Convert")
                            .add("type", AST.IDLit("AST"))
                            .add("value", new ASTBuilder("Call")
                                .add("function",
                                    new ASTBuilder("Member")
                                        .add("lhs", AST.create("IDLit", "bQue"))
                                        .add("rhs", AST.create("IDLit", "pop"))
                                        .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create()))
                        .create())
                    .create())
                .create())
            .create())
        .add(new ASTBuilder("Call")
            .add("function",
                new ASTBuilder("Member")
                    .add("lhs", AST.IDLit("bQue"))
                    .add("rhs", AST.IDLit("push"))
                    .create())
            .add("args",
                new ASTBuilder("ArgList")
                    .add(new ASTBuilder("Arg")
                        .add("name", AST.IDLit("ast"))
                        .add("value",
                            new ASTBuilder("Call")
                                .add("function", new ASTBuilder("Member")
                                    .add("lhs", new ASTBuilder("Call")
                                        .add("function", new ASTBuilder("Member")
                                            .add("lhs", new ASTBuilder("New")
                                                .add("type", AST.IDLit("ASTBuilder"))
                                                .add("args", new ASTBuilder("ArgList")
                                                    .add(new ASTBuilder("Arg")
                                                        .add("name", AST.IDLit("ast"))
                                                        .add("value", new ASTBuilder("Convert")
                                                            .add("type", AST.IDLit("AST"))
                                                            .add("value", new ASTBuilder("Call")
                                                                .add("function",
                                                                    new ASTBuilder("Member")
                                                                        .add("lhs", AST.create("IDLit", "bQue"))
                                                                        .add("rhs", AST.create("IDLit", "pop"))
                                                                        .create())
                                                                .add("args", AST.emptyList("ArgList"))
                                                                .create())
                                                            .create())
                                                        .create())
                                                    .create())
                                                .create())
                                            .add("rhs", AST.IDLit("addAll"))
                                            .create())
                                        .add("args", new ASTBuilder("ArgList")
                                            /*.add(new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("ast"))
                                                .add("value", AST.INTLit(0))
                                                .create())*/
                                            .add(new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("ast"))
                                                .add("value", new ASTBuilder("Call")
                                                    .add("function", new ASTBuilder("Member")
                                                        .add("lhs", AST.IDLit("builder"))
                                                        .add("rhs", AST.IDLit("getList"))
                                                        .create())
                                                    .add("args", AST.emptyList("ArgList"))
                                                    .create())
                                                .create())
                                            .create())
                                        .create())
                                    .add("rhs", AST.IDLit("create"))
                                    .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create())
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

  private ASTBuilder createPollAST() {
    return (ASTBuilder)new ASTBuilder("Assign")
        .add("lhs", AST.IDLit("ast"))
        .add("rhs",
            new ASTBuilder("Call")
                .add("function", AST.IDLit("peekAST"))
                .add("args", AST.emptyList("ArgList"))
                .create());
  }

  protected AST doStatic(AST ast) {
    ASTBuilder builder = new ASTBuilder("List");

    if (ast.isMembers()) {
      builder.setName("List");
      pushNewAST(builder, ast.getTypeName());
      for (String member : ast.getMembers()) {
        AST child = ast.get(member);
        AST exp = expand(child);
        if (exp != null) {
          builder.add(exp);
          addToMember(builder, member);
        }
      }
    } else if (ast.isList()) {
      assert ast.getMemberList() != null;
      pushNewAST(builder, ast.getTypeName());
      for (AST child : ast.getMemberList()) {
        AST exp = expand(child);
        if (exp != null) {
          builder.add(exp);
          addToList(builder);
        }
      }
    } else {
      assert ast.getValue() != null;
      return new ASTBuilder("Call")
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
                        .add("lhs", AST.IDLit("AST"))
                        .add("rhs", AST.IDLit("create"))
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
                        .add(
                          new ASTBuilder("Arg")
                            .add("name", AST.IDLit("value"))
                            .add("value",
                              new ASTBuilder("String")
                                .add("value", AST.IDLit(ast.getValue().toString()))
                                .create())
                            .create())
                        .create())
                    .create()
                )
                .create())
            .create())
        .create();
    }
    return builder.create();
  }

}
