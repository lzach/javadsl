package dsl.expansion;

import dsl.ast.AST;

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

}
