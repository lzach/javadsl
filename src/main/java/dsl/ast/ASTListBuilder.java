package dsl.ast;

import dsl.Context;

import java.util.Collection;

public interface ASTListBuilder extends Builder {
    ASTListBuilder add(AST value);
    ASTListBuilder addAll(Collection<AST> asts);
    ASTListBuilder addAll(AST ... asts);
}
