package dsl.ast;

import dsl.Context;

public interface Builder {
    AST create();
    AST create(Context ctx);
}
