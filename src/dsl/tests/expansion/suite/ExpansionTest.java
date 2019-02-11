package dsl.tests.expansion.suite;

import dsl.ast.AST;
import dsl.expansion.Expansion;
import dsl.parser.Parser;
import dsl.tests.java.JavaTranslator;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ExpansionTest {
  public static AST testParser(String file) {
    try {
      Parser parser = new Parser(file);
      AST ast = parser.parse();
      assert ast != null;
      return ast;
    } catch (FileNotFoundException e) {
    }
    assert false;
    return null;
  }

  public static String testJavaTranslator(AST ast) {

    String str = new JavaTranslator().translate(ast);
    assert str != null && !Objects.equals(str, "");
    return str;
  }

  private static AST testExpansion(AST expansion, AST def) {
    AST exp = new dsl.tests.expansion.Expansion(expansion).expand(def);
    assert exp != null;
    return exp;
  }
  private static AST testExpandedExpansion(AST def) {
    AST exp = new dsl.tests.expansion.ExpandedExpansion(def).expand();
    assert exp != null;
    return exp;
  }

  private static String testJavaCompiler(AST astExp, String buildPath) {
//    AST expans = testExpansion(astExp, astExp);
    AST expans = testExpandedExpansion( astExp);
    String java = testJavaTranslator(expans);
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream("ExpansionExpansion.java"), StandardCharsets.UTF_8))) {
      writer.write(java);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try {
      Process pro = Runtime.getRuntime().exec("javac -d " + buildPath + " -cp " + buildPath + " ExpansionExpansion.java");
      pro.waitFor();

      BufferedReader is = new BufferedReader(new InputStreamReader(pro.getInputStream()));
      String line;
      while ( (line = is.readLine()) != null ) {
        System.out.println(line);
      }
      is = new BufferedReader(new InputStreamReader(pro.getErrorStream()));
      while ( (line = is.readLine()) != null ) {
        System.out.println(line);
      }


      assert pro.exitValue() == 0;
      Expansion exp = (Expansion)Class.forName("ExpansionExpansion").getConstructor().newInstance();
      AST newAST = exp.expand(astExp);
      System.out.println("=== Expanded Expanded Expansion ===");
      System.out.println(newAST);
//      assert Objects.equals(newAST, expans);
      String newJava = testJavaTranslator(newAST);
      System.out.println("=== Expanded Expanded Java ===");
      System.out.println(newJava);
//      assert Objects.equals(newJava, java);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } finally {
//      new File("ExpansionExpansion.java").delete();
    }
    return null;
  }


  public static void main(String[] args) throws FileNotFoundException {
    String buildPath = "out/production/javadsl/";
    if ( args.length >= 1 ) {
      buildPath = args[0];
    }
    AST ast = testParser("examples/expansion/expansion.dsl");
    System.out.println(ast);
    System.out.println("=== Expansion ===");
    AST expans = testExpansion(ast, ast);
    System.out.println(expans);
    System.out.println("=== Java Code ===");
    String java = testJavaTranslator(expans);
    System.out.println(java);


    System.out.println("=== ExpandedExpansion ===");
    AST expExpans = testExpandedExpansion(ast);
    System.out.println(expExpans);

    System.out.println("=== ExpandedJava Code ===");
    String java2 = testJavaTranslator(expExpans);
    System.out.println(java2);


    String java3 = testJavaCompiler(ast, buildPath);
    System.out.println(java3);
  }

}
