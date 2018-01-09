package compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Map.Fun;
import codegen.*;
import codegen.Number;

public class Parser {
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

    private final int PADDING = 3;
    private final String[] program;
    private int from;

    public String[] lex(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        int index = 0;
        while (index < input.length()) {
            char next = input.charAt(index);
            if (next == ' ' || next == '\n' || next == '\r') {
                index++;
                continue;
            }
            switch (next) {
                case '{':
                case '}':
                case '(':
                case ')':
                case '+':
                case '-':
                case '/':
                case '*':
                case '%':
                case ';':
                    index++;
                    tokens.add("" + next);
                    continue;

            }
            StringBuilder tokenBuilder = new StringBuilder();
            compBinAssLoop:
            while (index < input.length()) {
                char nextOp = input.charAt(index);
                switch (nextOp) {
                    case '=':
                    case '!':
                    case '<':
                    case '>':
                    case '&':
                    case '|':
                        tokenBuilder.append(nextOp);
                        break;
                    default:
                        break compBinAssLoop;
                }
                index++;
            }
            if (tokenBuilder.length() == 0) {
                while (index < input.length()) {
                    char nextLetterNumber = input.charAt(index);
                    if (nextLetterNumber >= 'a' && nextLetterNumber <= 'z'
                            || nextLetterNumber >= 'A' && nextLetterNumber <= 'Z'
                            || nextLetterNumber >= '0' && nextLetterNumber <= '9')
                        tokenBuilder.append(nextLetterNumber);
                    else
                        break;
                    index++;
                }
            }
            if (tokenBuilder.length() > 0)
                tokens.add(tokenBuilder.toString());
            else {
                index++;
                tokens.add("" + next);
            }
        }
        // Padding
        for (int i = 0; i < PADDING; i++)
            tokens.add("");
        return tokens.toArray(new String[0]);
    }

    public Number parseNumber() {
        for (int i = 0; i < program[from].length(); i++) {
            char next = program[from].charAt(i);
            if (!(next >= '0' && next <= '9')) {
                from = -from - 1;
                return null;
            }
        }
        Number number = new Number(Integer.parseInt(program[from]));
        from++;
        return number;
    }

    public boolean isKeyword(String token) {
        switch (token) {
            case "true":
            case "false":
            case "for":
            case "while":
            case "if":
            case "else":
            case "read":
            case "write":
                return true;
            default:
                return false;
        }
    }

    public String parseName() {
        if (from < 0) return null;
        if (isKeyword(program[from])) {
            from = -from - 1;
            return null;
        }
        if (program[from].length() == 0) {
            from = -from - 1;
            return null;
        }
        char first = program[from].charAt(0);
        if (!(first >= 'a' && first <= 'z') && !(first >= 'A' && first <= 'Z')) {
            from = -from - 1;
            return null;
        }
        for (int i = 1; i < program[from].length(); i++) {
            char next = program[from].charAt(i);
            if (!(next >= 'a' && next <= 'z') && !(next >= 'A' && next <= 'Z')
                    && !(next >= '0' && next <= '9')) {
                from = -from - 1;
                return null;
            }
        }
        String name = program[from];
        from++;
        return name;
    }

    public String parseType() {
        if (program[from].equals("int")) {
            from++;
            return program[from - 1];
        }
        from = -from - 1;
        return null;
    }

    public Declaration parseDecl() {
        String type = parseType();
        if (from < 0)
            return null;

        List<String> names = new ArrayList<>();
        while (true) {
            String name = parseName();
            if (name != null) names.add(name);
            if (from < 0)
                return null;
            if (!program[from].equals(","))
                break;
            from++;
        }
        if (program[from].equals(";"))
            from += 1;
        else {
            from = -from - 1;
            return null;
        }
        return new Declaration(names.toArray(new String[]{}));
    }

    public Unop parseUnop() {
        if (program[from].equals("-")) {
            from++;
            return Unop.Minus;
        }
        from = -from - 1;
        return null;
    }

    public Binop parseBinop() {
        switch (program[from]) {
            case "+":
                from++;
                return Binop.Plus;
            case "-":
                from++;
                return Binop.Minus;
            case "*":
                from++;
                return Binop.MultiplicationOperator;
            case "/":
                from++;
                return Binop.DivisionOperator;
            case "%":
                from++;
                return Binop.Modulo;
            default:
                from = -from - 1;
                return null;
        }
    }

    public Comp parseComp() {
        switch (program[from]) {
            case "==":
                from++;
                return Comp.Equals;
            case "!=":
                from++;
                return Comp.NotEquals;
            case "<=":
                from++;
                return Comp.LessEqual;
            case "<":
                from++;
                return Comp.Less;
            case ">=":
                from++;
                return Comp.GreaterEqual;
            case ">":
                from++;
                return Comp.Greater;
            default:
                from = -from - 1;
                return null;
        }
    }

    public Expression parseExpressionNoBinop() {
        // <number>
        int before = from;
        Number number = parseNumber();
        if (from > 0)
            return number;
        from = before;
        // <call>
        Call call = parseCall();
        if (from > 0)
            return call;
        from = before;
        // <name>
        String name = parseName();
        if (from > 0)
            return new Variable(name);
        from = before;

        // (<expr>)
        if (program[from].equals("(")) {
            from += 1;
            Expression exp1 = parseExpression();
            if (from < 0)
                return null;
            if (program[from].equals(")")) {
                from++;
                return exp1;
            } else {
                from = -from - 1;
                return null;
            }
        }
        from = before;
        // <unop> <expr>
        Unop unop = parseUnop();
        if (from > 0) {
            Expression exp = parseExpression();
            if (from > 0) {
                return new Unary(unop, exp);
            }
        }

        return null;
    }

    public Expression parseExpression() {
        Expression expr1 = parseExpressionNoBinop();
        if (from < 0)
            return null;

        Binop binop;
        Expression expr2 = null;
        int before;

        before = from;
        binop = parseBinop();
        if (from >= 0) {
            expr2 = parseExpressionNoBinop();
            if (from < 0)
                return null;
            before = from;
        }
        if (from < 0) from = before;
        if (binop == null && expr2 == null) {
            binop = Binop.Plus;
            expr2 = new Number(0);
        }
        return new Binary(expr1, binop, expr2);
    }

    public Bbinop parseBbinop() {
        switch (program[from]) {
            case "&&":
                from++;
                return Bbinop.And;
            case "||":
                from++;
                return Bbinop.Or;
            default:
                from = -from - 1;
                return null;
        }
    }

    public Bunop parseBunop() {
        if (program[from].equals("!")) {
            from++;
            return Bunop.Not;
        }
        from = -from - 1;
        return null;
    }

    public Condition parseConditionNoBinary() {
        // true
        if (program[from].equals("true")) {
            from++;
            return new True();
        }
        // false
        if (program[from].equals("false")) {
            from++;
            return new False();
        }
        // (<cond>)
        int before = from;
        if (program[from].equals("(")) {
            from += 1;
            Condition cond = parseCondition();
            if (program[from].equals(")")) {
                from++;
                return cond;
            } else {
                from = -from - 1;
                return null;
            }
        }
        from = before;
        Bunop bun = parseBunop();
        if (from >= 0) {
            if (program[from].equals("(")) {
                from += 1;
                Condition cond = parseCondition();
                if (program[from].equals(")")){
                    from++;
                    return new UnaryCondition(bun, cond);
                } else {
                    from = -from - 1;
                    return null;
                }
            }
        }
        from = before;
        // <expr> <comp> <expr>
        Expression expr1 = parseExpression();
        if (from >= 0) {
            Comp comp = parseComp();
            if (from < 0)
                return null;
            Expression expr2 = parseExpression();
            if (comp == null && expr2 == null) {
                comp = Comp.Equals;
                expr2 = expr1;
            }
            return new Comparison(expr1, comp, expr2);
        }
        from = -from - 1;
        return null;
    }

    public Condition parseCondition() {
        int before = from;
        Condition cond1 = parseConditionNoBinary();
        if (from < 0)
            return null;

        Bbinop bbinop;
        Condition cond2 = null;

        before = from;
        bbinop = parseBbinop();
        if (from >= 0) {
            cond2 = parseConditionNoBinary();
            if (from < 0)
                return null;
            before = from;
        }
        if (from < 0) from = before;
        if (bbinop == null && cond2 == null) {
            bbinop = Bbinop.And;
            cond2 = new True();
        }
        return new BinaryCondition(cond1, bbinop, cond2);
    }

    public Call parseCall() {
        String name = parseName();
        if (from < 0)
            return null;
        if (!program[from].equals("(")) {
            from = -from - 1;
            return null;
        }
        from += 1;
        Expression[] params = parseCallParams();
        if (from < 0) return null;
        if (!program[from].equals(")")) {
            from = -from - 1;
            return null;
        }
        from += 1;

        return new Call(name, params);
    }

    public Assignment parseAssigment() {
        String name = parseName();
        if (from < 0)
            return null;
        if (!program[from].equals("=")) {
            from = -from - 1;
            return null;
        }
        from += 1;
        Expression exp;
        int before = from;
        exp = parseCall();
        if (from < 0) {
            from = before;
            exp = parseExpression();
        }
        if (from < 0) return null;
        return new Assignment(name, exp);
    }

    public Condition parseWhileIteCond() {
        if (program[from].equals("("))
            from++;
        else {
            from = -from - 1;
            return null;
        }
        Condition cond = parseCondition();
        if (from < 0) {
            return null;
        }
        if (!program[from].equals(")")) {
            from = -from - 1;
            return null;
        }
        from++;
        return cond;
    }

    public Statement parseIte() {
        if (!program[from].equals("if")) {
            from = -from - 1;
            return null;
        }

        from++;
        Condition cond = parseWhileIteCond();
        if (from < 0) {
            return null;
        }
        Statement ifBody = parseStatement();
        if (from < 0)
            return null;

        if (from < program.length && program[from].equals("else")) {
            from++;
            Statement elseBody = parseStatement();
            if (from < 0)
                return null;
            return new IfThenElse(cond, ifBody, elseBody);
        }
        return new IfThen(cond, ifBody);
    }

    public While parseWhile() {
        if (!program[from].equals("while")) {
            from = -from - 1;
            return null;
        }

        from++;
        Condition cond = parseWhileIteCond();
        if (from < 0) {
            return null;
        }
        Statement body = parseStatement();
        if (from < 0)
            return null;

        return new While(cond, body);
    }

    public Statement parseStatement() {
        switch (program[from]) {
            case ";":
                from++;
                return new EmptyStatement();
            case "{":
                from++;
                List<Statement> statements = new ArrayList<>();
                int before;
                do {
                    before = from;
                    Statement stmnt = parseStatement();
                    if (stmnt != null) statements.add(stmnt);
                } while (from > 0);
                from = before;
                if (program[from].equals("}")) {
                    from++;
                    return new Composite(statements.toArray(new Statement[]{}));
                } else {
                    from = -from - 1;
                    return null;
                }
            case "while":
                return parseWhile();
            case "if":
                return parseIte();
            case "write":
                if (!program[from + 1].equals("(")) {
                    from = -from - 1;
                    return null;
                }
                from += 2;
                Expression exp = parseExpression();
                if (from < 0 || !program[from].equals(")")) {
                    from = -from - 1;
                    return null;
                }
                if (!program[from + 1].equals(";")) {
                    from = -from - 2;
                    return null;
                }
                from += 2;
                return new Write(exp);
            case "return":
                return parseReturn();
            default: {
                Assignment ass = parseAssigment();
                if (from >= 0) {
                    if (program[from].equals(";")) {
                        from += 1;
                        return ass;
                    } else {
                        from = -from - 1;
                        return null;
                    }
                }
                return null;
            }
        }
    }

    public Return parseReturn() {
        if (!program[from].equals("return")) {
            from = -from - 1;
            return null;
        }
        from++;
        Expression exp = parseExpression();
        if (from < 0) return null;
        if (program[from].equals(";")) {
            from += 1;
            return new Return(exp);
        } else {
            from = -from - 1;
            return null;
        }
    }

    public String[] parseParams() {
        List<String> names = new ArrayList<>();
        while (true) {
            String type = parseType();
            if (from < 0) return null;
            String name = parseName();
            if (name != null) names.add(name);
            if (from < 0)
                return null;
            if (!program[from].equals(","))
                break;
            from++;
        }
        return names.toArray(new String[]{});
    }

    public Expression[] parseCallParams() {
        List<Expression> params = new ArrayList<>();
        while (true) {
            Expression exp = parseExpression();
            if (exp != null) params.add(exp);
            if (from < 0)
                return null;
            if (!program[from].equals(","))
                break;
            from++;
        }
        return params.toArray(new Expression[]{});
    }

    public Function parseFunction() {
        String type = parseType();
        if (from < 0)
            return null;

        String name = parseName();
        if (from < 0)
            return null;

        if (!program[from].equals("(")) {
            from = -from - 1;
            return null;
        }
        from++;
        int before = from;
        String[] params = parseParams();
        if (from < 0) {
            from = before;
            params = new String[]{};
        }
        if (from < 0 || !program[from].equals(")")) {
            from = -from - 1;
            return null;
        }
        from++;
        if (from < 0 || !program[from].equals("{")) {
            from = -from - 1;
            return null;
        }
        from++;

        List<Declaration> declarations = new ArrayList<>();
        List<Statement> statements = new ArrayList<>();
        do {
            before = from;
            Declaration dec = parseDecl();
            if (dec != null) declarations.add(dec);
        } while (from >= 0);
        from = before;
        while (from >= 0) {
            if (program[from].equals("}")) {
                from++;
                break;
            }
            Statement stmnt = parseStatement();
            if (stmnt != null) statements.add(stmnt);
        }
        if (from < 0) {
            return null;
        }
        return new Function(name, params, declarations.toArray(new Declaration[]{}), statements.toArray(new Statement[]{}));
    }

    public Program parseProgram() {
        List<Function> functions = new ArrayList<>();
        while (from >= 0 && from < program.length - PADDING) {
            Function fn = parseFunction();
            if (fn != null) functions.add(fn);
        }
        if (from != program.length - PADDING)
            return null;
        else {
            return new Program(functions.toArray(new Function[]{}));
        }
    }

    // Duplicate function, because of unclear task description
    public Program parse() {
        return parseProgram();
    }

    public Parser(String code) {
        program = lex(code);
        from = 0;
    }

    public static void main(String[] args) {
        //String textProgram = readProgramConsole();
        String textProgram = "int prim(int n) {\n" +
                "  int divisors, i;\n" +
                "  divisors = 0;\n" +
                "  \n" +
                "  i = 2;\n" +
                "  while (i < n) {\n" +
                "    if (n % i == 0)\n" +
                "      divisors = divisors + 1;\n" +
                "    i = i + 1;\n" +
                "  }\n" +
                "  \n" +
                "  if (divisors == 0 && n >= 2) {\n" +
                "    return 1;\n" +
                "  } else {\n" +
                "    return 0;\n" +
                "  }\n" +
                "}\n" +
                "\n" +
                "int main() {\n" +
                "  int prims;\n" +
                "  prims = 0;\n" +
                "  prims = prims + prim(997);\n" +
                "  prims = prims + prim(120);\n" +
                "  prims = prims + prim(887);\n" +
                "  prims = prims + prim(21);\n" +
                "  prims = prims + prim(379);\n" +
                "  prims = prims + prim(380);\n" +
                "  prims = prims + prim(757);\n" +
                "  prims = prims + prim(449);\n" +
                "  prims = prims + prim(5251);\n" +
                "  return prims;\n" +
                "}";
                System.out.println("Read program, lexing...");
        Parser parser = new Parser(textProgram);

        // System.out.println(java.util.Arrays.toString(program));

        Program program = parser.parse();
        if (program == null)
            System.out.println("Error: Unable to parse program");
        else
            System.out.println("Parse successful.");
    }
}
