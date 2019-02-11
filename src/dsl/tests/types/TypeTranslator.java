package dsl.tests.types;

import dsl.ast.AST;
import dsl.translators.AstTranslator;

public class TypeTranslator implements AstTranslator {
  @Override
  public AST translate(AST ast) {
    switch (ast.getTypeName()) {
      case "Type":
        return new AST("Class", new Object[]{"name",    new AST("String", "Type")},
                                      new Object[]{"attrs",   new AST("list", new AST("Attr", new Object[]{"name", new AST("String", "..")},
                                                                                                          new Object[]{"value", new AST("String", "...")}))},
                                      new Object[]{"methods", new AST("list", (Object)null)});
      case "Relation":
        break;
      case "MemberPairList":
        break;
      case "MemberPair":
        break;
    }
    return null;
  }
}
