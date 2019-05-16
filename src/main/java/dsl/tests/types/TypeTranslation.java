package dsl.tests.types;

import dsl.Context;
import dsl.ast.AST;

public class TypeTranslation {
  public static void main(String[] args) {
    Context ctx = dsl.definition.Type.getContext();

    AST ast1 = new AST("MemberPair", new String[]{"name", "String"}, new String[]{"type", "Type"});
//    AST ast2 = new AST("MemberPairList", "ParamPair");
    AST ast3 = new AST("Type", new Object[]{"name", AST.create("String", "String")}, new Object[]{"members", "MemberPairList"});
//    AST ast4 = new AST("Type", "Any");

    AST ast5 = new AST("Relation", new String[]{"from", "Type"}, new String[]{"to", "Type"});

  }
}
