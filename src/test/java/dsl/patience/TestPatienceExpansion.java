package dsl.patience;

import common.AstCompiler;
import common.CustomClassLoader;
import dsl.ast.AST;
import dsl.expansion.Expansion;
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

public class TestPatienceExpansion {
  private static final String PATIENCE_CODE = "examples/patience/patience.dsl";
  private static final String EXP_CODE = "examples/patience/expansion.dsl";
  private static final String BUILD_PATH = "target/classes/";
  private static final CustomClassLoader classLoader = new CustomClassLoader();

  private static AST expansion;
  private static AST patience;

  static {
    try {
      expansion = new Parser(EXP_CODE).parse();
      patience = new Parser(PATIENCE_CODE).parse();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static String expansionClassName = "dsl.expansion.impl.ExpandedSwitchExpansion"; // Original expander
  private static String repeatExpander = null;
  private static AST result = null; // result of expansion
  private static AST patResult = null; // result of expansion

  @Test
  public void testPatienceExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = AstCompiler.expand(expansion, ExpandedSwitchExpansion.class);
    assertNotNull(exp);
  }

  @Test
  public void testPatience() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, InterruptedException {

    String outputClass = "ExpansionPatience" ;
    AST expans = AstCompiler.expand(expansion, ExpandedSwitchExpansion.class);

    AstCompiler.compileAST(expans, outputClass);
    expansionClassName = outputClass;

    AST exp = AstCompiler.expand(patience, (Class<Expansion>)new CustomClassLoader().loadClass(expansionClassName));
    assertNotNull(exp);

    AstCompiler.compileAST(exp, "PatienceKlondike");
  }

  @RepeatedTest(3)
  public void testRepeatCompile(RepetitionInfo repetitionInfo) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException, ClassNotFoundException {

    String outputClass = "ExpansionPatience" ;
    if (repeatExpander == null ) {
      repeatExpander = expansionClassName;
    }

    AST expans = AstCompiler.expand(expansion, (Class<Expansion>)new CustomClassLoader().loadClass(repeatExpander));

    AstCompiler.writeAST(expans, "ExpansionPatience_try" +repetitionInfo.getCurrentRepetition()+".java");
    if (repetitionInfo.getCurrentRepetition() > 2 ) {
      assertEquals(result, expans, () -> "Expansion " + repetitionInfo.getCurrentRepetition() + " doesn't match expansion " + (repetitionInfo.getCurrentRepetition()-1));
    }
    result = expans;

    AstCompiler.compileAST(expans, outputClass);
    AST exp = AstCompiler.expand(patience, (Class<Expansion>)new CustomClassLoader().loadClass(outputClass));
    assertNotNull(exp);
    if (repetitionInfo.getCurrentRepetition() > 1 ) {
      assertEquals(result, exp, () -> "Expansion " + repetitionInfo.getCurrentRepetition() + " doesn't match expansion " + (repetitionInfo.getCurrentRepetition()-1));
    }
    patResult = exp;
    AstCompiler.compileAST(exp, "PatienceKlondike");

    repeatExpander = outputClass;
  }


  @Test
  public void testExpandedSwitchExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = AstCompiler.expand(expansion, ExpandedSwitchExpansion.class);
    assertNotNull(exp);
  }


}
