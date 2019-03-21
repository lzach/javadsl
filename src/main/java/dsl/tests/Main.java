package dsl.tests;

import dsl.ast.AST;
import dsl.parser.Parser;
import dsl.expansion.impl.Expansion;
import dsl.translators.impl.JavaTranslator;

import java.io.FileNotFoundException;
import java.util.Objects;

public class Main {
  public static final String TYPE_DEF = "(Language " +
                                             "(Type members:(MemberPairList (MemberPair name:(NameLit reference:name) type:(NameLit reference:String)) (MemberPair name:(NameLit reference:type) type:(NameLit reference:Type))) name:(NameLit reference:MemberPair)) " +
                                             "(Type arrayType:(NameLit reference:MemberPair) name:(NameLit reference:MemberPairList)) " +
                                             "(Type members:(MemberPairList (MemberPair name:(NameLit reference:name) type:(NameLit reference:String)) " +
                                                                           "(MemberPair name:(NameLit reference:members) type:(NameLit reference:MemberPairList)))" +
                                                             " name:(NameLit reference:Type)))";
  public static final String DEF = "(Language (CreateLang name:(NameLit reference:AnySymbol) arguments:(NameLit reference:NoneArgs) " +
                                                         "returnType:(NameLit reference:Any)) " +
                                             "(CreateLang name:(NameLit reference:AnyRecord) arguments:(NameLit reference:NoneArgs) " +
                                                         "returnType:(NameLit reference:AnyRecord)) " +
                                             "(CreateLang name:(NameLit reference:AnyClass) arguments:(NameLit reference:NoneArgs) " +
                                                         "returnType:(NameLit reference:AnyClass)) " +
                                             "(CreateLang name:(NameLit reference:AnyLanguage) " +
                                                         "arguments:(NameLit reference:NoneArgs) returnType:(NameLit reference:AnyLanguage)) " +
                                             "(CreateLang name:(NameLit reference:AnyLangArray) arguments:(NameLit reference:NoneArgs) " +
                                                         "returnType:(NameLit reference:AnyLangArray)) " +
                                             "(CreateLang name:(NameLit reference:AnyFunction) arguments:(NameLit reference:NoneArgs) " +
                                                         "returnType:(NameLit reference:AnyFunction)) " +
                                             "(CreateLang name:(NameLit reference:AnyMethod) arguments:(NameLit reference:NoneArgs) " +
                                                         "returnType:(NameLit reference:AnyMethod)) " +
                                             "(Restrict superset:(NameLit reference:AnySymbol) subset:(NameLit reference:AnyRecord)) " +
                                             "(Restrict superset:(NameLit reference:AnySymbol) subset:(NameLit reference:AnyClass)) " +
                                             "(Restrict superset:(NameLit reference:AnySymbol) subset:(NameLit reference:AnyLanguage)) " +
                                             "(Restrict superset:(NameLit reference:AnySymbol) subset:(NameLit reference:AnyLangArray)) " +
                                             "(Restrict superset:(NameLit reference:AnySymbol) subset:(NameLit reference:AnyFunction)) " +
                                             "(Restrict superset:(NameLit reference:AnySymbol) subset:(NameLit reference:AnyMethod)) " +
                                             "(CreateLang name:(NameLit reference:Restrict) arguments:(NameLit reference:AnyArgs) " +
                                                          "returnType:(NameLit reference:AnyLanguage)) " +
                                             "(CreateLang name:(NameLit reference:Relax) arguments:(NameLit reference:AnyArgs) " +
                                                          "returnType:(NameLit reference:AnyLanguage)))";
  public static final String EXPANSION = "(Expansions (Expansion type:(NameLit reference:Language) expansion:(Class methods:(MethodList (Method code:(Block (Define name:(NameLit reference:ctx) type:(NameLit reference:Context)) (Assign lhs:(NameLit reference:ctx) rhs:(New args:ArgList type:(NameLit reference:Language))) (members template:(Call args:(ArgList (Arg name:(NameLit reference:language) value:memberItem)) function:(Member lhs:(NameLit reference:ctx) rhs:(NameLit reference:define)))) (Return value:(NameLit reference:ctx))) name:(NameLit reference:getContext) params:ParamList returnType:(NameLit reference:Context))) name:(NameLit reference:Language) attrs:AttrList)) (Expansion type:(NameLit reference:Type) expansion:(New args:(ArgList (Arg name:(NameLit reference:name) value:(String value:(member name:(NameLit reference:name)))) (hasArg arg:(Arg name:(NameLit reference:members) value:(New args:ArgList type:(NameLit reference:HashMap) body:(Block (Block (members template:(Call args:memberItem function:(NameLit reference:put)) name:(NameLit reference:members)))))) name:(NameLit reference:members)) (hasArg arg:(Arg name:(NameLit reference:arrayType) value:(String value:(member name:(NameLit reference:arrayType)))) name:(NameLit reference:arrayType))) type:(NameLit reference:Type))) (Expansion type:(NameLit reference:MemberPair) expansion:(ArgList (Arg name:(NameLit reference:name) value:(String value:(member name:(NameLit reference:name)))) (Arg name:(NameLit reference:value) value:(String value:(member name:(NameLit reference:type)))))) (Expansion type:(NameLit reference:Relation) expansion:l))";
//    "(Expansions " +
//                                             "(Expansion type:(NameLit reference:Language) " +
//                                                          "expansion:(Class methods:(MethodList " +
//                                                                              "(Method code:(Block " +
//                                                                                   "(Define name:(NameLit reference:lang) type:(NameLit reference:Language)) " +
//                                                                                   "(Assign lhs:(NameLit reference:lang) rhs:(New args:ArgList type:(NameLit reference:Language))) " +
//                                                                                   "(members template:(Call args:(ArgList (Arg name:(NameLit reference:language) value:memberItem)) " +
//                                                                                                          "function:(Member lhs:(NameLit reference:lang) rhs:(NameLit reference:addLang)))) " +
//                                                                                   "(Return value:(NameLit reference:lang))) " +
//                                                                                 "name:(NameLit reference:getLanguage) " +
//                                                                                 "params:ParamList returnType:(NameLit reference:Language))) " +
//                                                                            "name:(NameLit reference:Language) attrs:AttrList)) " +
//                                             "(Expansion type:(NameLit reference:Type) expansion:(New args:(ArgList (Arg name:(NameLit reference:name) " +
//                                                                                                                        "value:(String value:(member name:(NameLit reference:name)))) " +
//                                                                                                                   "(hasArg arg:(Arg name:(NameLit reference:members) " +
//                                                                                                                              "value:(New args:ArgList type:(NameLit reference:HashMap) " +
//                                                                                                                                        "body:(Block (Block (members template:(Call args:memberItem " +
//                                                                                                                                       "function:(NameLit reference:put)) name:(NameLit reference:members)))))) name:(NameLit reference:members)) " +
//                                                                                                                   "(hasArg arg:(Arg name:(NameLit reference:arrayType) " +
//                                                                                                                                   "value:(String value:(member name:(NameLit reference:arrayType)))) " +
//                                                                                                                            "name:(NameLit reference:arrayType))) type:(NameLit reference:Type))) " +
//                                             "(Expansion type:(NameLit reference:MemberPair) expansion:(ArgList (Arg name:(NameLit reference:name) value:(String value:(member name:(NameLit reference:name)))) (Arg name:(NameLit reference:value) value:(String value:(member name:(NameLit reference:type)))))) " +
//                                             "(Expansion type:(NameLit reference:Relation) expansion:l))";
  public static final String JAVA = "class Language {\n" +
    "public Context getContext() {\n" +
    "Context ctx;;\n" +
    "ctx = new Language();\n" +
    "ctx.define(new Type(\"MemberPair\",new HashMap(){\n" +
    "{\n" +
    "put(\"name\",\"String\");\n" +
    "put(\"type\",\"Type\");;\n" +
    "}\n" +
    ";\n" +
    "}\n" +
    "));\n" +
    "ctx.define(new Type(\"MemberPairList\",\"MemberPair\"));\n" +
    "ctx.define(new Type(\"Type\",new HashMap(){\n" +
    "{\n" +
    "put(\"name\",\"String\");\n" +
    "put(\"members\",\"MemberPairList\");;\n" +
    "}\n" +
    ";\n" +
    "}\n" +
    "));;\n" +
    "return ctx;\n" +
    "}\n" +
    "}\n";
//  "class Language {\n"+
//                                    "public Language getLanguage() {\n" +
//                                    "Language lang;;\n" +
//                                    "lang = new Language();\n" +
//
//                                    "lang.addLang(new Type(\"MemberPair\",new HashMap(){\n" +
//                                    "{\n" +
//                                    "put(\"name\",\"String\");\n" +
//                                    "put(\"type\",\"Type\");;\n" +
//                                    "}\n" +
//                                    ";\n" +
//                                    "}\n" +
//                                    "));\n" +
//                                    "lang.addLang(new Type(\"MemberPairList\",\"MemberPair\"));\n" +
//                                    "lang.addLang(new Type(\"Type\",new HashMap(){\n" +
//                                    "{\n" +
//                                    "put(\"name\",\"String\");\n" +
//                                    "put(\"members\",\"MemberPairList\");;\n" +
//                                    "}\n" +
//                                    ";\n" +
//                                    "}\n" +
//                                    "));;\n" +
//                                    "return lang;\n" +
//                                    "}\n" +
//                                    "}\n";
  
  public static AST testParser(String file, String expected) {
    try {
      Parser parser = new Parser(file);
      AST ast = parser.parse();
      assert ast != null;
      assert Objects.equals(ast.toString(), expected) : "\n" + ast.toString() + "\n\n" + expected;
      return ast;
    } catch (FileNotFoundException e) {
    }
    assert false;
    return null;
  }

  public static String testJavaTranslator(AST ast, String expected) {

    String str = new JavaTranslator().translate(ast);
    assert str != null && !Objects.equals(str, "");
    assert Objects.equals(str, expected) : "\n" + str + "\n\n" + expected;
    return str;
  }

  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("=== Type AST ===");
    AST def = testParser("examples/definition.dsl", DEF);
    System.out.println(def);
    System.out.println("=== Type AST ===");
    AST typedef = testParser("examples/typedef.dsl", TYPE_DEF);
    System.out.println(typedef);
    System.out.println("=== Expansion AST === ");
    AST ast = testParser("examples/expansion.dsl", EXPANSION);
    System.out.println(ast);
    System.out.println("=== Expansion ===");
    AST expans = testExpansion(ast, typedef);
    System.out.println(expans);
    System.out.println("=== Java Code ===");
    String java = testJavaTranslator(expans, JAVA);
    System.out.println(java);
  }

  private static AST testExpansion(AST expansion, AST def) {
    AST exp = new Expansion(expansion).expand(def);
    assert exp != null;
    return exp;
  }
}
