package dsl.expansion;

import common.AstCompiler;
import dsl.ast.AST;
import dsl.composition.Composer;
import dsl.definition.impl.LanguageLanguage;
import dsl.expansion.impl.ExpandedSwitchExpansion;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestComposeExpansion {
  private static final String EXP_CODE = "examples/expansion/composition.dsl";
  private static final String AST_CODE = "examples/composition/composition.dsl";

  private static Class<Expansion> languageExpander = null;

  @BeforeAll
  public static void initialise()  throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException, ClassNotFoundException {
    String outputClass = "LanguageExpansion" ;

    languageExpander = (Class<Expansion>) AstCompiler.compileAST(AstCompiler.expand(EXP_CODE, ExpandedSwitchExpansion.class), outputClass);
    assertNotNull(languageExpander, "Failed to load class");
  }
  @Test
  public void testLanguageLanguage() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, InterruptedException, ClassNotFoundException {
      String outputClass = "CompositionComposition" ;
      String outputName = outputClass + ".java";
      AST language = AstCompiler.expand(AST_CODE, languageExpander);

      Composer lang = (Composer)AstCompiler.compileAST(language, outputClass).getConstructor().newInstance();
      assertNotNull(lang, "Failed to load class");

      assertEquals(lang, new LanguageLanguage());
  }
}
