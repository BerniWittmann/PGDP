/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiniJavaParser;

/**
 *
 * @author finnr
 */
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class MiniJavaParserFinn {

    public static String readProgramConsole() {
        @SuppressWarnings("resource")
        Scanner sin = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (true) {
            String nextLine = sin.nextLine();
            if (nextLine.equals("")) {
                nextLine = sin.nextLine();
                if (nextLine.equals("")) {
                    break;
                }
            }
            if (nextLine.startsWith("//")) {
                continue;
            }
            builder.append(nextLine);
            builder.append('\n');
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String test = "while (n > 0) { n == read(); }";
        String[] out = lex(test);

        System.out.println(parseWhile(out, 0));

        // for (int i = 0; i < out.length; i++) {
        // System.out.println(i + " " + out[i]);
        // }
    }

    public static String[] lex(String program) {
        String[] out = new String[program.length()];
        int outCounter = 0;
        String temp = "";
        for (int i = 0; i < program.length(); i++) {
            switch (program.charAt(i)) {

                case ' ':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }
                    break;

                case ',':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = ",";
                    outCounter++;
                    break;

                case ';':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = ";";
                    outCounter++;
                    break;

                case '(':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "(";
                    outCounter++;
                    break;

                case ')':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = ")";
                    outCounter++;
                    break;

                case '=':
                    if (program.charAt(i + 1) != '=') {
                        if (temp != "" && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "=";
                        outCounter++;
                        break;
                    } else {
                        if (temp != "" && !temp.equals(null)) {
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
                        if (temp != "" && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "<";
                        outCounter++;
                        break;
                    } else {
                        if (temp != "" && !temp.equals(null)) {
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
                        if (temp != "" && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = ">";
                        outCounter++;
                        break;
                    } else {
                        if (temp != "" && !temp.equals(null)) {
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
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "{";
                    outCounter++;
                    break;

                case '}':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "}";
                    outCounter++;
                    break;

                case '%':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "%";
                    outCounter++;
                    break;

                case '+':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "+";
                    outCounter++;
                    break;

                case '-':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "-";
                    outCounter++;
                    break;

                case '*':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "*";
                    outCounter++;
                    break;

                case '/':
                    if (temp != "" && !temp.equals(null)) {
                        out[outCounter] = temp;
                        temp = "";
                        outCounter++;
                    }

                    out[outCounter] = "/";
                    outCounter++;
                    break;

                case '!':
                    if (program.charAt(i + 1) != '=') {
                        if (temp != "" && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "!";
                        outCounter++;
                        break;
                    } else {
                        if (temp != "" && !temp.equals(null)) {
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
                        if (temp != "" && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "|";
                        outCounter++;
                        break;
                    } else {
                        if (temp != "" && !temp.equals(null)) {
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
                        if (temp != "" && !temp.equals(null)) {
                            out[outCounter] = temp;
                            temp = "";
                            outCounter++;
                        }

                        out[outCounter] = "&";
                        outCounter++;
                        break;
                    } else {
                        if (temp != "" && !temp.equals(null)) {
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
        char[] ziffern = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String test = program[from];

        boolean isNumber = false;
        for (int i = 0; i < test.length(); i++) {
            for (int j = 0; j < ziffern.length; j++) {
                if (test.charAt(i) == ziffern[j]) {
                    isNumber = true;
                    break;
                } else {
                    isNumber = false;
                }
            }

            if (!isNumber) {
                return -1;
            }
        }
        if (isNumber) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseName(String[] program, int from) {
        // hier kann man eventuell noch hinzufügen, dass zahlen auch nach
        // buchstaben kommen können
        char[] symbols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'V', 'W', 'X', 'Y', 'Z'};
        String test = program[from];

        boolean isSymbol = false;
        for (int i = 0; i < test.length(); i++) {
            for (int j = 0; j < symbols.length; j++) {
                if (test.charAt(i) == symbols[j]) {
                    isSymbol = true;
                    break;
                } else {
                    isSymbol = false;
                    ;
                }
            }

            if (!isSymbol) {
                return -1;
            }
        }
        if (isSymbol) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseType(String[] program, int from) {
        String[] types = {"int", "boolean", "float", "double", "String", "char"};
        String test = program[from];

        boolean isType = false;

        for (int j = 0; j < types.length; j++) {
            if (test.equals(types[j])) {
                isType = true;
                break;
            } else {
                isType = false;
            }
        }
        if (isType) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseDecl(String[] program, int from) {
//		String test = program[from];
//		if (test.equals("=") {
//			return from + 1;
//		} else {
//			return -1;
//		}

        //hier müssten beliebig viele Namen parsebar sein, ebenso wie kommas (dafür bräuchte man noch eine kurze Hilfsfunktion
        //aktuell ist aber nur eins parsebar
        int temp_from = parseSemicolon(program, parseName(program, parseType(program, from)));
        return -1;
    }

    public static int parseUnop(String[] program, int from) {
        String[] types = {"-"};
        String test = program[from];

        boolean isUnop = false;

        for (int j = 0; j < types.length; j++) {
            if (test.equals(types[j])) {
                isUnop = true;
                break;
            } else {
                isUnop = false;
            }
        }
        if (isUnop) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseBinop(String[] program, int from) {
        String[] types = {"*", "/", "%", "+", "-"};
        String test = program[from];

        boolean isBinop = false;

        for (int j = 0; j < types.length; j++) {
            if (test.equals(types[j])) {
                isBinop = true;
                break;
            } else {
                isBinop = false;
            }
        }
        if (isBinop) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseComp(String[] program, int from) {
        String[] types = {"<", ">", "==", ">=", "<=", "!="};
        String test = program[from];

        boolean isBinop = false;

        for (int j = 0; j < types.length; j++) {
            if (test.equals(types[j])) {
                isBinop = true;
                break;
            } else {
                isBinop = false;
            }
        }
        if (isBinop) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseExpression(String[] program, int from) {

        if (parseName(program, from) > 0) {
            return from + 1;
        }

        if (parseNumber(program, from) > 0) {
            return from + 1;
        }

        if (parseUnop(program, from) > 0 && parseExpression(program, from + 1) > 0) {
            return from + 2;
        }

        int new_from = parseExpression(program, parseBinop(program, parseExpression(program, from)));

        if (new_from > 0) {
            return new_from;
        } else {
            return -1;
        }
    }

    public static int parseBbinop(String[] program, int from) {
        String[] Bbinop = {"||", "&&"};
        String test = program[from];

        boolean isBbinop = false;

        for (int j = 0; j < Bbinop.length; j++) {
            if (test.equals(Bbinop[j])) {
                isBbinop = true;
                break;
            } else {
                isBbinop = false;
            }
        }
        if (isBbinop) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseBunop(String[] program, int from) {
        String test = program[from];
        if (test.equals("!")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseCondition(String[] program, int from) {

        int new_from[] = new int[4];

        new_from[0] = parseTrueFalse(program, from);

        new_from[1] = parseExpression(program, parseComp(program, parseExpression(program, from)));

        new_from[2] = parseCondition(program, parseBunop(program, from));

        new_from[3] = parseCondition(program, parseBbinop(program, parseCondition(program, from)));

        for (int i = 0; i < new_from.length; i++) {
            if (new_from[i] > 0) {
                return new_from[i];
            }
        }
        return -1;

    }

    public static int parseTrueFalse(String[] program, int from) {
        String[] types = {"true", "false"};
        String test = program[from];

        boolean isType = false;

        for (int j = 0; j < types.length; j++) {
            if (test.equals(types[j])) {
                isType = true;
                break;
            } else {
                isType = false;
            }
        }
        if (isType) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseStatement(String[] program, int from) {
        int new_from[] = new int[8];

        new_from[0] = parseSemicolon(program, from);

        new_from[1] = parseGeschweifteKlammerZu(program, parseStatement(program, parseGeschweifteKlammerAuf(program, from)));

        new_from[2] = parseSemicolon(program, parseExpression(program, parseEquals(program, parseName(program, from))));

        new_from[3] = parseSemicolon(program, parseRundeKlammerZu(program, parseRundeKlammerAuf(program, parseRead(program, parseEquals(program, parseName(program, from))))));

        new_from[4] = parseSemicolon(program, parseRundeKlammerZu(program, parseExpression(program, parseRundeKlammerAuf(program, parseWrite(program, from)))));

        new_from[5] = parseStatement(program, parseRundeKlammerZu(program, parseCondition(program, parseRundeKlammerAuf(program, parseIf(program, from)))));

        new_from[6] = parseStatement(program, parseElse(program, parseStatement(program, parseRundeKlammerZu(program, parseCondition(program, parseRundeKlammerAuf(program, parseIf(program, from)))))));

        new_from[7] = parseStatement(program, parseRundeKlammerZu(program, parseCondition(program, parseRundeKlammerAuf(program, parseWhile(program, from)))));

        for (int i = 0; i < new_from.length; i++) {
            if (new_from[i] > 0) {
                return new_from[i];
            }
        }
        return -1;
    }

    public static int parseSemicolon(String[] program, int from) {
        String test = program[from];
        if (test.equals(";")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseGeschweifteKlammerAuf(String[] program, int from) {
        String test = program[from];
        if (test.equals("{")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseEquals(String[] program, int from) {
        String test = program[from];
        if (test.equals("=")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseGeschweifteKlammerZu(String[] program, int from) {
        String test = program[from];
        if (test.equals("}")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseRundeKlammerZu(String[] program, int from) {
        String test = program[from];
        if (test.equals(")")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseRundeKlammerAuf(String[] program, int from) {
        String test = program[from];
        if (test.equals("(")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseRead(String[] program, int from) {
        String test = program[from];
        if (test.equals("read")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseWrite(String[] program, int from) {
        String test = program[from];
        if (test.equals("write")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseIf(String[] program, int from) {
        String test = program[from];
        if (test.equals("if")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseElse(String[] program, int from) {
        String test = program[from];
        if (test.equals("else")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseWhile(String[] program, int from) {
        String test = program[from];
        if (test.equals("while")) {
            return from + 1;
        } else {
            return -1;
        }
    }

    public static int parseProgram(String[] program) {
        return -1; // Todo

        //Folie 48 aus den VOrlesungsfolien muss hier noch implementiert werden, analog wie alles andere
    }

}
