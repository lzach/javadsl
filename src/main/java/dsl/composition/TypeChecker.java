package dsl.composition;

import dsl.ast.AST;

public abstract class TypeChecker {

    public abstract boolean typecheck(AST ast);
}
