package Aufgabenblatt11_2;
import codegen.CodeGenerationVisitor;
import codegen.Program;
import compiler.Parser;

public class Compiler {
  public static int[] compile(String code) {
//     System.out.println(java.util.Arrays.toString(tokens));

    Parser p = new Parser(code);
    Program ast = p.parse();
    if (ast == null)
      throw new RuntimeException("Error: Unable to parse program");
    
    CodeGenerationVisitor cgv = new CodeGenerationVisitor();
    ast.accept(cgv);
//    System.out.println(Interpreter.programToString(cgv.getProgram()));
    return cgv.getProgram();
  }
}
