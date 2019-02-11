package dsl.translators;


import dsl.ast.AST;

public interface StringTranslator extends Translator<AST, String> {

  String translate(AST ast);
}
