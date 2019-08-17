package dsl.ast;

import dsl.Context;

import java.util.Collection;

public interface ASTListBuilder extends Builder {
    ASTListBuilder add(AST value);
    ASTListBuilder add(int index, AST value);
    default ASTListBuilder add(Builder value) {return add(value.create()); }
    ASTListBuilder addAll(Collection<AST> asts);
    ASTListBuilder addAll(AST ... asts);
}
