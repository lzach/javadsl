package dsl.expansion;

import common.AstCompiler;
import common.CustomClassLoader;
import dsl.ast.AST;
import dsl.expansion.impl.ExpandedExpansion;
import dsl.expansion.impl.ExpandedSwitchExpansion;
import dsl.parser.Parser;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestExpansionExpansionFix {
  private static final String AST_CODE = "examples/expansion/expansion-fix.dsl";
  private static final String BUILD_PATH = "target/classes/";
  private static final CustomClassLoader classLoader = new CustomClassLoader();

  private static AST expansion;

  static {
    try {
      expansion = new Parser(AST_CODE).parse();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static String expansionClassName = "dsl.expansion.impl.ExpandedSwitchFixExpansion"; // Original expander
  private static AST result = null; // result of expansion

  @Test
  public void testExpandedExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = AstCompiler.expand(expansion, ExpandedExpansion.class);
    assertNotNull(exp);
  }


  @Test
  public void testExpandedSwitchExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = AstCompiler.expand(expansion, ExpandedSwitchExpansion.class);
    assertNotNull(exp);
  }


  @RepeatedTest(3)
  public void testCompile(RepetitionInfo repetitionInfo) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException, ClassNotFoundException {

    String outputClass = "ExpansionExpansion" ;

    AST expans = AstCompiler.expand(expansion, (Class<Expansion>)new CustomClassLoader().loadClass(expansionClassName));

    AstCompiler.writeAST(expans, "ExpansionExpansion_try" +repetitionInfo.getCurrentRepetition()+".java");
    if (repetitionInfo.getCurrentRepetition() > 2 ) {
      assertEquals(result, expans, () -> "Expansion " + repetitionInfo.getCurrentRepetition() + " doesn't match expansion " + (repetitionInfo.getCurrentRepetition()-1));
    }
    result = expans;

    AstCompiler.compileAST(expans, outputClass);

    expansionClassName = outputClass;
  }
}
