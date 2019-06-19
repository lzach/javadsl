package common;

import dsl.ast.AST;
import dsl.expansion.Expansion;
import dsl.parser.Parser;
import dsl.translators.impl.JavaTranslator;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AstCompiler {
    public static final String BUILD_PATH = "target/classes/";


    public static <E extends Expansion> AST  expand(AST ast, Class<E> expClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return expClass.getConstructor(AST.class).newInstance(ast).expand(ast);
    }


    public static <E extends Expansion> AST  expand(String fileName, Class<E> expClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try {
            return expand(new Parser(fileName).parse(), expClass);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void writeAST(AST ast, String filename) throws IOException {
        String java = new JavaTranslator().translate(ast);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream(filename), StandardCharsets.UTF_8))) {
            writer.write(java);
        }
    }

    public static Class compileAST(AST ast, String className) throws IOException, InterruptedException, ClassNotFoundException {
        String outputName = className + ".java";
        writeAST(ast, outputName);

        new File(AstCompiler.BUILD_PATH + className  + ".class").delete();

        Process pro = Runtime.getRuntime().exec("javac -d " + BUILD_PATH + " -g -cp " + BUILD_PATH + " " + outputName);
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
//        new File(outputName).delete();
        assertEquals(0, pro.exitValue(), "Class " + className + " failed to compile");

        return new CustomClassLoader().loadClass(className);
    }
}
