package dsl.expansion;

import dsl.ast.AST;
import dsl.ast.ASTBuilder;

import java.util.*;

public abstract class Expansion {
  protected AST expansion;
  protected final List<String> expansionList = new ArrayList<>();
  protected final Deque<AST> asts = new ArrayDeque<>();
  protected final Deque<Deque<Map<String, AST>>> symbols = new ArrayDeque<>();

  public Expansion(AST expansion) {
    this.expansion = expansion;
    this.pushStack();
  }

  public abstract AST expand(AST ast);

  protected void update() {
    assert Objects.equals(expansion.getTypeName(), "Expansions");

    for (AST child : expansion.get("expansions").getMemberList()) {
      assert (Objects.equals(child.getTypeName(), "Expansion"));
      String type = dereference(child.get("type"));
      expansionList.add(type);
    }
  }

  protected void pushStack() {
    symbols.push(new ArrayDeque<>());
    pushLocal();
  }

  protected void pushAST(AST ast) {
    asts.push(ast);
  }

  protected AST popAST() {
    return asts.pop();
  }

  protected AST peekAST() {
    return asts.peek();
  }

  protected void popStack() {
    symbols.pop();
  }

  protected void pushLocal() {
    symbols.peek().push(new HashMap<>());
  }

  protected void popLocal() {
    symbols.peek().pop();
  }

  protected AST get(String name) {
    for ( Map<String, AST> map : symbols.peek() ) {
      if ( map.containsKey(name)) {
        return map.get(name);
      }
    }
    return null;
  }

  protected void declare(String name, AST ast) {
    symbols.peek().peek().put(name, ast);
  }

  protected void set(String name, AST ast) {
    for ( Map<String, AST> map : symbols.peek()) {
      if (map.containsKey(name)) {
        map.put(name, ast);
        return;
      }
    }
    symbols.peek().peek().put(name, ast);
  }



  protected static String dereference(AST nameLit) {
    assert nameLit != null;
    //AST refAST = nameLit.get("reference");
    AST refAST = nameLit;
    Object o = refAST.getValue();
    assert o != null;
    assert o instanceof String;
    return (String) o;
  }

  protected void popToBuilder(ASTBuilder builder) {
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
  }

  protected void pushList(ASTBuilder builder) {
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
                                .add("lhs", new ASTBuilder("New")
                                    .add("type", AST.IDLit("ASTBuilder"))
                                    .add("args", new ASTBuilder("ArgList")
                                        .add(new ASTBuilder("Arg")
                                            .add("name", AST.IDLit("name"))
                                            .add("value", AST.STRLit("List"))
                                            .create())
                                        .create())
                                    .create())
                                .add("rhs", AST.IDLit("create"))
                                .create())
                            .add("args", AST.emptyList("ArgList"))
                            .create())
                        .create())
                .create()));
  }

  protected void addToList(ASTBuilder builder) {
    popToBuilder(builder);
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
                                            .add("rhs", AST.IDLit("add"))
                                            .create())
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("index"))
                                                .add("value", AST.INTLit(0))
                                                .create())
                                            .add(new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("ast"))
                                                .add("value", AST.IDLit("builder"))
                                                .create())
                                            .create())
                                        .create())
                                    .add("rhs", AST.IDLit("create"))
                                    .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create()
                            )
                        .create())
                    .create())
            .create());
  }

  protected void addToMember(ASTBuilder builder, String name) {
    popToBuilder(builder);
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
                                            .add("rhs", AST.IDLit("add"))
                                            .create())
                                        .add("args", new ASTBuilder("ArgList")
                                            .add(new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("ast"))
                                                .add("value", new ASTBuilder("Convert")
                                                    .add("type", AST.IDLit("AST"))
                                                    .add("value", AST.STRLit(name))
                                                    .create())
                                                .create())
                                            .add(new ASTBuilder("Arg")
                                                .add("name", AST.IDLit("ast"))
                                                .add("value", AST.IDLit("builder"))
                                                .create())
                                            .create())
                                        .create())
                                    .add("rhs", AST.IDLit("create"))
                                    .create())
                                .add("args", AST.emptyList("ArgList"))
                                .create()
                        )
                        .create())
                    .create())
            .create());
  }

  private AST doSuperStatic(AST ast) {
    ASTBuilder builder = new ASTBuilder();

    if ( !(ast.getTypeName().charAt(0) == '$' || ast.isValue() && ast.toString().charAt(0) == '$') ) {
//      builder.setName("List");
//      builder.add(expand(ast));
      return expand(ast);
    } else {
      // TODO: change here so that list is added if one of the children is a non-sstatic
      // value
      if (ast.isMembers()) {
        builder.setName(ast.getTypeName().substring(1));
        for (String name : ast.getMembers()) {
            builder.add(name, doSuperStatic(ast.get(name)));
        }
      } else if (ast.isList()) {
        boolean nonStatic = false;
        builder.setName(ast.getTypeName().substring(1));
        for (AST member : ast.getMemberList()) {
//          if ( !nonStatic && !(member.getTypeName().charAt(0) == '$' || member.isValue() && member.toString().charAt(0) == '$') ) {
//            nonStatic = true;
//          }
          AST child = doSuperStatic(member);
          if (child != null) {
            builder.add(child);
          }
//          if (!(member.getTypeName().charAt(0) == '$' || member.isValue() && member.toString().charAt(0) == '$') ) {
//            addToList(builder);
//          }
        }
      } else {
        builder.setName(ast.getTypeName());
        builder.set(ast.getValue().toString().substring(1));
      }
    }
    return builder.create();
  }


  protected AST doStatic(AST ast) {
    // TODO: Rewrite all of this to add in members as we go along rather than at the end.
    //       We have the technology!
    ASTBuilder builder = new ASTBuilder("List");
    if ( ast.getTypeName().charAt(0) == '$' || ast.isValue() && ast.toString().charAt(0) == '$' ) {
      builder.setName("List");
      builder.add(doSuperStatic(ast));
//      pushList(builder);
//      pushList(builder);
//      addToList(builder);
//      return doSuperStatic(ast);
    } else {

      int childCount = 0;
//      builder.setName(ast.getTypeName());
      if (ast.isMembers()) {
//        pushList(builder);
        builder.setName("List");
        List<String> names = new ArrayList<>();
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
                .create())
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("ast"))
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                              .add("lhs", new ASTBuilder("New")
                                  .add("type", AST.IDLit("ASTBuilder"))
                                  .add("args",
                                      new ASTBuilder("ArgList")
                                          .add(new ASTBuilder("Arg")
                                              .add("name", AST.IDLit("name"))
                                              .add("value", AST.STRLit(ast.getTypeName()))
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
        for (String member : ast.getMembers()) {
          AST child = expand(ast.get(member));
          if (child != null) {
            names.add(member);
            builder.add(child);
            addToMember(builder, member);
            childCount++;
          }
        }
//        Collections.reverse(names);
//        builder.add(new ASTBuilder("Assign")
//            .add("lhs", AST.IDLit("builder"))
//            .add("rhs",
//                new ASTBuilder("New")
//                    .add("type", AST.IDLit("ASTBuilder"))
//                    .add("args",
//                        new ASTBuilder("ArgList")
//                            .add(new ASTBuilder("Arg")
//                                .add("name", AST.IDLit("name"))
//                                .add("value", AST.STRLit(ast.getTypeName()))
//                                .create())
//                            .create())
//                    .create()));
//        for (String name : names) {
//          builder.add(
//              new ASTBuilder("Call")
//                  .add("function",
//                      new ASTBuilder("Member")
//                          .add("lhs", AST.IDLit("builder"))
//                          .add("rhs", AST.IDLit("add"))
//                          .create())
//                  .add("args",
//                      new ASTBuilder("ArgList")
//                          .add(
//                              new ASTBuilder("Arg")
//                                  .add("name", AST.IDLit("name"))
//                                  .add("value",
//                                      new ASTBuilder("String")
//                                          .add("value", AST.IDLit(name))
//                                          .create())
//                                  .create())
//                          .add(
//                              new ASTBuilder("Arg")
//                                  .add("name", AST.IDLit("ast"))
//                                  .add("value", new ASTBuilder("Convert")
//                                      .add("type", AST.IDLit("AST"))
//                                      .add("value",
//                                          new ASTBuilder("Call")
//                                              .add("function",
//                                                  new ASTBuilder("Member")
//                                                      .add("lhs", AST.create("IDLit", "bQue"))
//                                                      .add("rhs", AST.create("IDLit", "pop"))
//                                                      .create())
//                                              .add("args", AST.emptyList("ArgList"))
//                                              .create()))
//                                  .create())
//                          .create())
//                  .create());
//        }
//        builder.add(new ASTBuilder("Call")
//            .add("function",
//                new ASTBuilder("Member")
//                    .add("lhs", AST.IDLit("bQue"))
//                    .add("rhs", AST.IDLit("push"))
//                    .create())
//            .add("args",
//                new ASTBuilder("ArgList")
//                    .add(
//                        new ASTBuilder("Arg")
//                            .add("name", AST.IDLit("name"))
//                            .add("value",
//                                new ASTBuilder("Call")
//                                    .add("function",
//                                        new ASTBuilder("Member")
//                                            .add("lhs", AST.IDLit("builder"))
//                                            .add("rhs", AST.IDLit("create"))
//                                            .create())
//                                    .add("args", AST.emptyList("ArgList"))
//                                    .create()
//                            )
//                            .create())
//                    .create()));
////        addToList(builder);

      } else if (ast.isList()) {
        assert ast.getMemberList() != null;
//        builder.setName("List");
        List<AST> asts = new ArrayList<>();
        builder.add(new ASTBuilder("Call")
            .add("function", new ASTBuilder("Member")
                .add("lhs", AST.IDLit("bQue"))
                .add("rhs", AST.IDLit("push"))
                .create())
            .add("args", new ASTBuilder("ArgList")
                .add(new ASTBuilder("Arg")
                    .add("name", AST.IDLit("ast"))
                    .add("value", new ASTBuilder("Call")
                        .add("function", new ASTBuilder("Member")
                            .add("lhs", new ASTBuilder("New")
                                .add("type", AST.IDLit("ASTBuilder"))
                                .add("args",
                                    new ASTBuilder("ArgList")
                                        .add(new ASTBuilder("Arg")
                                            .add("name", AST.IDLit("name"))
                                            .add("value", AST.STRLit(ast.getTypeName()))
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
        for (AST child : ast.getMemberList()) {
          AST exp = expand(child);
          if (exp != null) {
            asts.add(exp);
            addToList(builder);
            childCount++;
          }
        }

        for (AST child : asts) {
          pushList(builder);
          builder.add(child);
          addToList(builder);
        }

        builder.add(new ASTBuilder("Assign")
            .add("lhs", AST.IDLit("builder"))
            .add("rhs",
                new ASTBuilder("New")
                    .add("type", AST.IDLit("ASTBuilder"))
                    .add("args",
                        new ASTBuilder("ArgList")
                            .add(new ASTBuilder("Arg")
                                .add("name", AST.IDLit("name"))
                                .add("value", AST.STRLit(ast.getTypeName()))
                                .create())
                            .create())
                    .create()));
//        for (int ignored = 0; ignored < childCount; ++ignored) {
//          builder.add(
//              new ASTBuilder("Call")
//                  .add("function",
//                      new ASTBuilder("Member")
//                          .add("lhs", AST.IDLit("builder"))
//                          .add("rhs", AST.IDLit("add"))
//                          .create())
//                  .add("args",
//                      new ASTBuilder("ArgList")
//                          .add(
//                              new ASTBuilder("Arg")
//                                  .add("name", AST.IDLit("index"))
//                                  .add("value", AST.INTLit(0))
//                                  .create())
//                          .add(
//                              new ASTBuilder("Arg")
//                                  .add("name", AST.IDLit("ast"))
//                                  .add("value", new ASTBuilder("Convert")
//                                      .add("type", AST.IDLit("AST"))
//                                      .add("value",
//                                          new ASTBuilder("Call")
//                                              .add("function",
//                                                  new ASTBuilder("Member")
//                                                      .add("lhs", AST.create("IDLit", "bQue"))
//                                                      .add("rhs", AST.create("IDLit", "pop"))
//                                                      .create())
//                                              .add("args", AST.emptyList("ArgList"))
//                                              .create()))
//                                  .create())
//                          .create())
//                  .create());
//        }
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
                    .create()));

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
                    .create()).create();
      }
    }
    return builder.create();
  }

  protected AST[] reverse(AST []asts) {
    AST[] retval = new AST[asts.length];
    for ( int i = 0; i < asts.length; ++i) {
      retval[i] = asts[asts.length-i-1];
    }
    return retval;
  }

}
