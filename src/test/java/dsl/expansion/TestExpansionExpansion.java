package dsl.expansion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import dsl.ast.AST;
import dsl.expansion.impl.ExpandedExpansion;
import dsl.expansion.impl.ExpandedSwitchExpansion;
import dsl.parser.Parser;
import dsl.translators.impl.JavaTranslator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class TestExpansionExpansion  {
  private static final String AST_CODE = "examples/expansion/expansion.dsl";
  private static final String BUILD_PATH = "target/classes/";

  private static AST expansion;

  static {
    try {
      expansion = new Parser(AST_CODE).parse();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static String expansionClassName = "dsl.expansion.impl.ExpandedSwitchExpansion"; // Original expander
  private static AST result = null; // result of expansion

  private <E extends Expansion> AST  expand(AST ast, Class<E> expClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return expClass.getConstructor(AST.class).newInstance(ast).expand(ast);
  }

  private void writeAST(AST ast, String filename) throws IOException {
    String java = new JavaTranslator().translate(ast);
    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream(filename), StandardCharsets.UTF_8))) {
      writer.write(java);
    }
  }


//  @Test
//  public void testExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
//    AST exp = expand(expansion, dsl.expansion.impl.Expansion.class);
//    assertNotNull(exp);
//  }


  @Test
  public void testExpandedExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = expand(expansion, ExpandedExpansion.class);
    assertNotNull(exp);
  }


  @Test
  public void testExpandedSwitchExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = expand(expansion, ExpandedSwitchExpansion.class);
    assertNotNull(exp);
  }


  @RepeatedTest(3)
  public void testCompile(RepetitionInfo repetitionInfo) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException, ClassNotFoundException {

    String outputClass = "ExpansionExpansion" ;
    String outputName = outputClass + ".java";

    AST expans = expand(expansion, (Class<Expansion>)Class.forName(expansionClassName));

    if ( result != null ) {
      assertEquals(expans, result, () -> "Expansion " + repetitionInfo.getCurrentRepetition() + " doesn't match expansion " + (repetitionInfo.getCurrentRepetition()-1));
    }
    result = expans;

    writeAST(expans, outputName);

    Process pro = Runtime.getRuntime().exec("javac -d " + BUILD_PATH + " -g -cp " + BUILD_PATH + " " + outputName);
    pro.waitFor();

    assertEquals(0, pro.exitValue(), "Expansion failed to compile");
    expansionClassName = outputClass;
  }
}
