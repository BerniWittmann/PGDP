package optimizer;

import interpreter.Interpreter;
import interpreter.SteckInstruction;
import interpreter.SteckOperation;

import java.util.ArrayList;
import java.util.List;

public class TailCallOptimization {
    private static List<Integer> instructions;

    private static SteckInstruction getInstr(int index) {
        return SteckInstruction.decode(instructions.get(index));
    }

    public static void optimize(int[] program) {
        //System.out.println(Interpreter.programToString(program));

        instructions = new ArrayList<>();
        for (int index = 0; index < program.length; index++) {
            instructions.add(program[index]);
        }

        int start = 0;
        int end = -1;
        int startMain = -1;
        int maxLength = program.length;
        boolean patchedMain = false;
        for (int i = 0; i < maxLength; i++) {
            if (getInstr(i).getOperation() == SteckOperation.RETURN || getInstr(i).getOperation() == SteckOperation.HALT) {
                end = i;
            }

            if (end >= 0 && start >= 0) {
                //Function

                if (start > 0 && end < program.length) {
                    for (int j = start; j <= end; j++) {
                        // Patch Return 1
                        if (getInstr(i).getOperation() == SteckOperation.RETURN && getInstr(i).getImmediate() == 1) {
                            instructions.set(i, SteckOperation.RETURN.encode(2));
                        }
                        // Patch Call 1
                        if (getInstr(i).getOperation() == SteckOperation.CALL && getInstr(i).getImmediate() == 1) {
                            instructions.set(i, SteckOperation.CALL.encode(2));
                        }
                    }
                }
                if (start == 3 || (start > 3 && end < program.length - 1)) {
                    for (int j = start; j <= end; j++) {
                        // Patch LDS 0
                        if (getInstr(j).getOperation() == SteckOperation.LDS && getInstr(j).getImmediate() == 0) {
                            instructions.set(j, SteckOperation.LDS.encode(-1));
                        }
                        // Patch LDI 1
                        if (getInstr(j).getOperation() == SteckOperation.LDI && getInstr(j).getImmediate() == 1) {
                            instructions.set(j, SteckOperation.LDS.encode(0));
                        }
                    }
                }
                if (start > 3 && getInstr(end).getImmediate() != 0) {
                    // Patch before CALL
                    for (int j = start; j <= end; j++) {
                        SteckInstruction instr = getInstr(j);
                        if (instr.getOperation() == SteckOperation.CALL) {
                            break;
                        }
                        if (getInstr(j - 1).getOperation() == SteckOperation.SUB) {
                            instructions.set(j, SteckOperation.MUL.encode());
                            instructions.add(j, SteckOperation.LDS.encode(0));
                            instructions.add(j, SteckOperation.LDS.encode(-1));
                        } else if (getInstr(j + 1).getOperation() != SteckOperation.MUL && instr.getOperation() == SteckOperation.LDS && instr.getImmediate() == 0) {
                            instructions.set(j, SteckOperation.LDI.encode(1));
                            instructions.add(j, SteckOperation.LDI.encode(0));
                        }
                        if (instr.getOperation() == SteckOperation.MUL && getInstr(j + 1).getOperation() != SteckOperation.RETURN) {
                            for (int k = 0; k < 3; k++) {
                                int op = -1;
                                if (k == 0) {
                                    op = SteckOperation.LDI.encode(3);
                                } else if (k == 1) {
                                    op = SteckOperation.CALL.encode(2);
                                } else if (k == 2) {
                                    op = SteckOperation.ADD.encode();
                                }
                                if (op == -1) continue;
                                instructions.set(j + 1 + k, op);
                            }
                        }

                    }
                }
                if (!patchedMain && start > 0 && end == maxLength - 1) {
                    // Patch Main function
                    patchedMain = true;
                    int limit = end;
                    for (int j = start; j <= limit; j++) {
                        if (getInstr(j).getOperation() == SteckOperation.ADD) {
                            instructions.add(j, SteckOperation.LDI.encode(1));
                            instructions.add(j, SteckOperation.LDI.encode(0));
                            instructions.add(j, SteckOperation.ADD.encode());
                            j += 3;
                            limit += 1;
                        }
                        if (getInstr(j).getOperation() == SteckOperation.CALL) {
                            instructions.set(j, SteckOperation.CALL.encode(2));
                        }
                    }
                    startMain = start;
                }
                start = i + 1;
                end = -1;
                maxLength = instructions.size();
            }
            if (startMain >= 0) {
                if (getInstr(0).getOperation() == SteckOperation.LDI) {
                    instructions.set(0, SteckOperation.LDI.encode(startMain));
                }
            }
        }

        int[] newProgram = new int[instructions.size()];
        for (int i = 0; i < instructions.size(); i++) {
            newProgram[i] = instructions.get(i);
        }
        program = newProgram;

        System.out.println("####################");
        System.out.println(Interpreter.programToString(program));
        System.out.println("####################");
    }
}
