package compiler;

import codegen.CodeGenerationVisitor;
import codegen.Program;
import interpreter.Interpreter;

import java.util.Scanner;

public class Compiler {
    public static String readProgramConsole() {
        @SuppressWarnings("resource")
        Scanner sin = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (true) {
            String nextLine = sin.nextLine();
            if (nextLine.equals("")) {
                nextLine = sin.nextLine();
                if (nextLine.equals(""))
                    break;
            }
            if (nextLine.startsWith("//"))
                continue;
            builder.append(nextLine);
            builder.append('\n');
        }
        return builder.toString();
    }

    public static int[] compile(String code) {
        Parser parser = new Parser(code);
        Program program = parser.parse();

        if (program == null) throw new RuntimeException("Parsen Fehlgeschlagen");

        System.out.println("Compiling...");
        CodeGenerationVisitor cgv = new CodeGenerationVisitor();
        try {
            program.accept(cgv);
        } catch(Exception e) {
            //e.printStackTrace();
            throw new RuntimeException("Compilen ist fehlgeschlagen.");
        }

        //System.out.println(Interpreter.programToString(cgv.getProgram()));

        System.out.println("Compiled");
        return cgv.getProgram();
    }

    public static void main(String[] args) {
        //String textProgram = readProgramConsole();
        String textProgram = "int n;";

        int[] compiled = compile(textProgram);

    }
}
