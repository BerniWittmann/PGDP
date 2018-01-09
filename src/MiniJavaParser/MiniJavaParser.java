package MiniJavaParser;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class MiniJavaParser {

    public static String readProgramConsole() {
        @SuppressWarnings("resource") Scanner sin = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (true) {
            String nextLine = sin.nextLine();
            if (nextLine.equals("")) {
                nextLine = sin.nextLine();
                if (nextLine.equals("")) break;
            }
            if (nextLine.startsWith("//")) continue;
            builder.append(nextLine);
            builder.append("\n");

        }
        return builder.toString();

    }

    public static String[] lex(String program) {
        String[] out = new String[program.length()];
        int outCounter = 0;
        String temp = "";
        for (int i = 0; i < program.length(); i++) {
            switch (program.charAt(i)) {
                case ' ':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }
                    break;

                case ',':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = ",";
                    outCounter++;
                    break;

                case ';':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = ";";
                    outCounter++;
                    break;

                case '(':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "(";
                    outCounter++;
                    break;

                case ')':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = ")";
                    outCounter++;
                    break;

                case '=':
                    if (program.charAt(i + 1) != '=') {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "=";
                        outCounter++;
                        break;
                    } else {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }
                        out[outCounter] = "==";
                        outCounter++;
                        i++;
                        break;
                    }

                case '<':
                    if (program.charAt(i + 1) != '=') {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "<";
                        outCounter++;
                        break;
                    } else {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "<=";
                        outCounter++;
                        i++;
                        break;
                    }

                case '>':
                    if (program.charAt(i + 1) != '=') {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = ">";
                        outCounter++;
                        break;
                    } else {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = ">=";
                        outCounter++;
                        i++;
                        break;
                    }

                case '{':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "{";
                    outCounter++;
                    break;

                case '}':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "}";
                    outCounter++;
                    break;

                case '%':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "%";
                    outCounter++;
                    break;

                case '+':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "+";
                    outCounter++;
                    break;

                case '-':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "-";
                    outCounter++;
                    break;

                case '*':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "*";
                    outCounter++;
                    break;

                case '/':
                    if (!temp.equals("") && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "/";
                    outCounter++;
                    break;

                case '!':
                    if (program.charAt(i + 1) != '=') {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "!";
                        outCounter++;
                        break;
                    } else {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }
                        out[outCounter] = "!=";
                        outCounter++;
                        i++;
                        break;
                    }

                case '|':
                    if (program.charAt(i + 1) != '|') {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "|";
                        outCounter++;
                        break;
                    } else {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }
                        out[outCounter] = "||";
                        outCounter++;
                        i++;
                        break;
                    }

                case '&':
                    if (program.charAt(i + 1) != '&') {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "&";
                        outCounter++;
                        break;
                    } else {
                        if (!temp.equals("") && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }
                        out[outCounter] = "&&";
                        outCounter++;
                        i++;
                        break;
                    }

                default:
                    temp += program.charAt(i);
                    break;

            }
        }

        return out;
    }

    public static int parseNumber(String[] program, int from) {
        String[] types = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
        String test = program[from];

        for (int i = 0; i < test.length(); i++) {
            if (!isInStringArray(types, test.charAt(i) + "")) return -1;
        }

        return from + 1;
    }

    public static int parseName(String[] program, int from) {
        String[] types = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String test = program[from];

        for (int i = 0; i < test.length(); i++) {
            if (!isInStringArray(types, test.charAt(i) + "")) return -1;
        }

        return from + 1;
    }

    public static int parseType(String[] program, int from) {
        String[] types = {"int", "boolean", "float", "double", "String", "char"};
        String test = program[from];

        if (isInStringArray(types, test)) return from + 1;
        return -1;
    }

    public static int parseCommaSeparatedNames(String[] program, int from) {
        if (!match(program, from, ",")) return from;
        int name = parseName(program, from + 1);
        if (name == -1) return -1;

        return parseCommaSeparatedNames(program, name);
    }

    public static int parseDecl(String[] program, int from) {
        if (parseType(program, from) == -1) return -1;
        if (parseName(program, from + 1) == -1) return -1;
        if (match(program, from + 2, "=")) {
            int exp = parseExpression(program, from + 3);
            if (exp != -1) return exp;
            return -1;
        }

        int next = parseCommaSeparatedNames(program, from + 2);
        if (next == -1) return -1;

        if (!match(program, next, ";")) return -1;
        return next + 1;
    }

    public static int parseAssignment(String[] program, int from) {
        if (parseName(program, from) == -1) return -1;
        if (!match(program, from + 1, "=")) return -1;
        int exp = parseExpression(program, from + 2);
        if (exp == -1) return -1;
        if (!match(program, exp, ";")) return -1;
        return exp + 1;
    }

    public static int parseMethodCall(String[] program, int from) {
        if (parseName(program, from) == -1) return -1;
        if (!match(program, from + 1, "(")) return -1;
        int next = from + 3;
        if (!match(program, next - 1, ")")) {
            if (parseName(program, from + 2) == -1) return -1;
            next = parseCommaSeparatedNames(program, from + 3);
            if (next == -1) return -1;
        }
        return next;
    }

    public static boolean isInStringArray(String[] arr, String str) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(str)) return true;
        }
        return false;
    }

    public static int parseUnop(String[] program, int from) {
        String[] types = {"-"};
        String test = program[from];

        if (isInStringArray(types, test)) return from + 1;
        return -1;
    }

    public static int parseBinop(String[] program, int from) {
        String[] types = {"*", "/", "%", "+", "-"};
        String test = program[from];

        if (isInStringArray(types, test)) return from + 1;
        return -1;
    }

    public static int parseComp(String[] program, int from) {
        String[] types = {"<", ">", "==", ">=", "<=", "!="};
        String test = program[from];

        if (isInStringArray(types, test)) return from + 1;
        return -1;
    }

    public static int parseExpression(String[] program, int from) {
        int methodCall = parseMethodCall(program, from);
        if (methodCall != -1) return methodCall;

        int bunop = parseBunop(program, from);
        if (bunop != -1) {
            return parseExpression(program, bunop);
        }
        int unop = parseUnop(program, from);
        if (unop != -1) {
            return parseExpression(program, unop);
        }

        int name = parseName(program, from);
        if (name != -1) return name;

        int number = parseNumber(program, from);
        if (number != -1) return number;

        int exp1 = parseExpression(program, from);
        if (exp1 == -1) return -1;

        int bbinop = parseBbinop(program, exp1);
        int comp = parseComp(program, exp1);
        int binop = parseBinop(program, exp1);

        if (bbinop == -1 && comp == -1 && binop == -1) return -1;

        int exp2 = parseExpression(program, exp1 + 1);
        if (exp2 == -1) return -1;

        return exp2;
    }

    public static int parseBbinop(String[] program, int from) {
        String[] types = {"||", "&&"};
        String test = program[from];

        if (isInStringArray(types, test)) return from + 1;
        return -1;
    }

    public static int parseBunop(String[] program, int from) {
        String[] types = {"!"};
        String test = program[from];

        if (isInStringArray(types, test)) return from + 1;
        return -1;
    }

    public static int parseCondition(String[] program, int from) {
        int exp1 = parseExpression(program, from);
        if (exp1 == -1) return -1;
        int comp = parseComp(program, exp1);
        if (comp == -1) return -1;
        int exp2 = parseExpression(program, comp);
        if (exp2 == -1) return -1;
        return exp2;
    }

    public static int parseStatement(String[] program, int from) {
        int decl = parseDecl(program, from);
        if (decl != -1) return decl;

        int assign = parseAssignment(program, from);
        if (assign != -1) return assign;

        int methodCall = parseMethodCall(program, from);
        if (methodCall != -1) {
            if (match(program, methodCall, ";")) return methodCall + 1;
        }

        return -1;
    }

    public static int parseWhile(String[] program, int from) {
        if (!match(program, from, "while") || !match(program, from + 1, "(")) {
            return -1;
        }
        int next = parseCondition(program, from + 2);
        if (next == -1) return -1;
        if (!match(program, next, ")") || !match(program, next + 1, "{")) {
            return -1;
        }
        next = parseBlock(program, next + 2);
        if (next == -1) return -1;

        return next + 1;
    }

    public static int parseIf(String[] program, int from) {
        if (!match(program, from, "if") || !match(program, from + 1, "(")) {
            return -1;
        }
        int next = parseCondition(program, from + 2);
        if (next == -1) return -1;
        if (!match(program, next, ")") || !match(program, next + 1, "{")) {
            return -1;
        }
        next = parseBlock(program, next + 2);
        if (next == -1) return -1;

        if (match(program, next + 1, "else")) {
            if (!match(program, next + 2, "{")) return -1;
            next = parseBlock(program, next + 3);
            if (next == -1) return -1;
        }
        return next + 1;
    }

    public static int parseFor(String[] program, int from) {
        return -1;
    }

    public static int parseEmptyBlock(String[] program, int from) {
        if (!match(program, from, "{")) {
            return -1;
        }
        return parseBlock(program, from + 1);
    }

    public static int parseDoWhile(String[] program, int from) {
        return -1;
    }

    public static int parseSwitch(String[] program, int from) {
        return -1;
    }

    public static boolean match(String[] program, int index, String match) {
        if (index >= program.length) return false;
        return program[index].equals(match);
    }

    public static int parseBlock(String[] program, int from) {
        switch (program[from]) {
            case "while":
                return parseWhile(program, from);
            case "for":
                return parseFor(program, from);
            case "if":
                return parseIf(program, from);
            case "do":
                return parseDoWhile(program, from);
            case "switch":
                return parseSwitch(program, from);
            case "{":
                return parseEmptyBlock(program, from);
            default:
                int i = from;
                while (i >= 0 && i < program.length) {
                    if (match(program, i, "}")) return i;
                    if (match(program, i, "while")) return i;
                    if (match(program, i, "for")) return i;
                    if (match(program, i, "if")) return i;
                    if (match(program, i, "do")) return i;
                    if (match(program, i, "switch")) return i;
                    i = parseStatement(program, i);
                }
                return i;
        }
    }

    public static int parseProgram(String[] program) {
        int i = 0;
        int last = program.length;
        for (int j = 0; j < program.length; j++) {
            if (program[j] == null) {
                last = j;
                break;
            }
        }
        String[] newProgram = new String[last];
        for (int j = 0; j < last; j++) {
            newProgram[j] = program[j];
        }

        while (i >= 0 && i < newProgram.length) {
            i = parseBlock(newProgram, i);
        }
        return i; // Todo
    }

    public static void main(String[] args) {
        // TODO Read Program from Console
        String test = "int i = i % 3 == 0 || i";
        String[] tokens = lex(test);
        int result = parseProgram(tokens);

        if (result >= 0) {
            System.out.println("Valid Java");
            return;
        }
        System.err.println("Invalid Java");
    }
}