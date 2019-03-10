package dsl.expansion;

import dsl.ast.AST;

import java.util.*;

public abstract class Expansion {
  protected AST expansion;
  protected final List<String> expansionList = new ArrayList<>();
  protected final Map<String, List<String>> functionMap = new HashMap<>();
  protected final Map<String, List<String>> opParams = new HashMap<>();
  protected final Map<String, AST> opMap = new HashMap<>();
  protected final Map<String, List<String>> literalParams = new HashMap<>();
  protected final Map<String, AST> literalMap = new HashMap<>();

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

    for (AST child : expansion.get("functions").getMemberList()) {
      assert (Objects.equals(child.getTypeName(), "Function"));
      String type = dereference(child.get("name"));

      setParams(child, type, functionMap);
    }

    for (AST child : expansion.get("operations").getMemberList()) {
      assert (Objects.equals(child.getTypeName(), "Operation"));
      extractMethods(child, opMap, opParams);
    }

  }

  private void extractMethods(AST child, Map<String, AST> literalMap, Map<String, List<String>> literalParams) {
    String type = dereference(child.get("name"));
    literalMap.put(type, child.get("expansion"));
    setParams(child, type, literalParams);
  }

  protected void setParams(AST child, String type, Map<String, List<String>> opMap) {
    List<String> pList = new ArrayList<>();
    opMap.put(type, pList);
    for (AST param : child.get("params").getMemberList()) {
      pList.add(dereference(param.get("name")));
    }
  }

  protected void pushStack() {
    symbols.push(new ArrayDeque<>());
    pushLocal();
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

  protected AST lookup(String name) {
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
    assert refAST != null;
    Object o = refAST.getValue();
    assert o != null;
    assert o instanceof String;
    return (String) o;
  }

}
