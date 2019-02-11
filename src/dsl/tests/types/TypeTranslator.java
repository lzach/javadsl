package dsl.tests.types;

import dsl.ast.AST;
import dsl.translators.AstTranslator;

public class TypeTranslator implements AstTranslator {
  @Override
  public AST translate(AST ast) {
    switch (ast.getTypeName()) {
      case "Type":
        return new AST("Class", new Object[]{"name",    AST.create("String", "Type")},
                                      new Object[]{"attrs",   AST.create("list", new AST("Attr", new Object[]{"name", AST.create("String", "..")},
                                                                                                          new Object[]{"value", AST.create("String", "...")}))},
                                      new Object[]{"methods", AST.create("list", (Object)null)});
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
