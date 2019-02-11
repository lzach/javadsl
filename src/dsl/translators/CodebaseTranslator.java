package dsl.translators;

import dsl.ast.AST;
import dsl.codebase.Codebase;

public interface CodebaseTranslator extends Translator<AST, Codebase> {

  Codebase translate(AST ast);

}
