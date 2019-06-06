package dsl.ast;

import dsl.Context;

import java.util.Map;

public interface ASTMemberBuilder extends Builder {
    ASTMemberBuilder add(String name, AST value);
    default ASTMemberBuilder add(String name, Builder value) { return add(name, value.create()); }
    ASTMemberBuilder addAll(Map<String, AST> asts);
}
