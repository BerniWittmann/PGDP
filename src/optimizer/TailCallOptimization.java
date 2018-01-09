package optimizer;

import interpreter.Interpreter;
import interpreter.SteckInstruction;
import interpreter.SteckOperation;

public class TailCallOptimization {
    public static void optimize(int[] program) {
        // Tail Cast Test fails, since the Parser (sample solution) is not able to handle function declarations..
        //System.out.println(Interpreter.programToString(program));
        int start = -1;
        int end = -1;
        for (int i = 0; i < program.length; i++) {
            if (SteckInstruction.decode(program[i]).getOperation() == SteckOperation.RETURN) {
                start = i;
            }
            if (start >= 0 && SteckInstruction.decode(program[i]).getOperation() == SteckOperation.CALL) {
                end = i;
            }

            if (end >= 0 && start >= 0) {
                //Function
                for (int j = start; j <= end; j++) {
                    System.out.println(SteckInstruction.decode(program[j]));
                }
                System.out.println("---------------");
                start = -1;
                end = -1;
            }
        }
    }
}
