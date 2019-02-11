package dsl.ast;

import dsl.Context;

import java.util.Map;

public interface ASTMemberBuilder extends Builder {
    ASTMemberBuilder add(String name, AST value);
    ASTMemberBuilder addAll(Map<String, AST> asts);
}
