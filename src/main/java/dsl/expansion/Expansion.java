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

  protected AST doStatic(AST ast) {
    ASTBuilder builder = new ASTBuilder();
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
                                .add("value", new ASTBuilder("Convert")
                                    .add("type", AST.IDLit("AST"))
                                    .add("value",
                                        new ASTBuilder("Call")
                                            .add("function",
                                                new ASTBuilder("Member")
                                                    .add("lhs", AST.create("IDLit", "bQue"))
                                                    .add("rhs", AST.create("IDLit", "pop"))
                                                    .create())
                                            .add("args", AST.emptyList("ArgList"))
                                            .create()))
                                .create())
                        .create())
                .create());
      }
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
      for (AST ignored : asts) {
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
                                .add("value", new ASTBuilder("Convert")
                                    .add("type", AST.IDLit("AST"))
                                    .add("value",
                                        new ASTBuilder("Call")
                                            .add("function",
                                                new ASTBuilder("Member")
                                                    .add("lhs", AST.create("IDLit", "bQue"))
                                                    .add("rhs", AST.create("IDLit", "pop"))
                                                    .create())
                                            .add("args", AST.emptyList("ArgList"))
                                            .create()))
                                .create())
                        .create())
                .create());
      }
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
//            builder = new ASTBuilder("List");
//            builder.add(
//                new ASTBuilder("Assign")
//                    .add("lhs", AST.IDLit("builder"))
//                    .add("rhs",
//                        new ASTBuilder("New")
//                            .add("type", AST.IDLit("ASTBuilder"))
//                            .add("args",
//                                new ASTBuilder("ArgList")
//                                    .add(
//                                        new ASTBuilder("Arg")
//                                            .add("name", AST.IDLit("name"))
//                                            .add("value",
//                                                new ASTBuilder("String")
//                                                    .add("value", AST.IDLit(ast.getTypeName()))
//                                                    .create())
//                                            .create())
//                                    .create())
//                            .create())
//                    .create());
//            builder.add(
//                    new ASTBuilder("Call")
//                        .add("function",
//                            new ASTBuilder("Member")
//                                .add("lhs", AST.IDLit("builder"))
//                                .add("rhs", AST.IDLit("set"))
//                                .create())
//                        .add("args",
//                            new ASTBuilder("ArgList")
//                                .add(
//                                    new ASTBuilder("Arg")
//                                        .add("name", AST.IDLit("value"))
//                                        .add("value",
//                                            new ASTBuilder("String")
//                                                .add("value", AST.IDLit(ast.getValue().toString()))
//                                                .create())
//                                        .create())
//                                .create())
//                        .create());
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
                                                          .add("value", AST.IDLit(ast.getTypeName()) )
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
    return builder.create();
  }

}
