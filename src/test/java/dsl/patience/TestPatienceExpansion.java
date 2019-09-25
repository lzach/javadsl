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
  private static AST result = null; // result of expansion

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


  @Test
  public void testExpandedSwitchExpansion() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    AST exp = AstCompiler.expand(expansion, ExpandedSwitchExpansion.class);
    assertNotNull(exp);
  }


}
