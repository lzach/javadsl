package dsl.expansion;

import dsl.ast.AST;

public abstract class Expansion {
  public abstract AST expand(AST ast);
}
