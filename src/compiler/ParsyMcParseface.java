package compiler;

import java.util.ArrayList;
import java.util.Scanner;

public class ParsyMcParseface {
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

  private static final int PADDING = 3;

  public static String[] lex(String input) {
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
      compBinAssLoop: while (index < input.length()) {
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

  public static int parseNumber(String[] program, int from) {
    for (int i = 0; i < program[from].length(); i++) {
      char next = program[from].charAt(i);
      if (!(next >= '0' && next <= '9')) {
        return -from - 1;
      }
    }
    return from + 1;
  }

  public static boolean isKeyword(String token) {
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

  public static int parseName(String[] program, int from) {
    if (isKeyword(program[from]))
      return -from - 1;
    if (program[from].length() == 0)
      return -from - 1;
    char first = program[from].charAt(0);
    if (!(first >= 'a' && first <= 'z') && !(first >= 'A' && first <= 'Z'))
      return -from - 1;
    for (int i = 1; i < program[from].length(); i++) {
      char next = program[from].charAt(i);
      if (!(next >= 'a' && next <= 'z') && !(next >= 'A' && next <= 'Z')
          && !(next >= '0' && next <= '9')) {
        return -from - 1;
      }
    }
    return from + 1;
  }

  public static int parseType(String[] program, int from) {
    if (program[from].equals("int"))
      return from + 1;
    return -from - 1;
  }

  public static int parseDecl(String[] program, int from) {
    from = parseType(program, from);
    if (from < 0)
      return from;

    while (true) {
      from = parseName(program, from);
      if (from < 0)
        return from;
      if (!program[from].equals(","))
        break;
      from++;
    }
    if (program[from].equals(";"))
      from += 1;
    else
      return -from - 1;
    return from;
  }

  public static int parseUnop(String[] program, int from) {
    if (program[from].equals("-"))
      return from + 1;
    return -from - 1;
  }

  public static int parseBinop(String[] program, int from) {
    switch (program[from]) {
      case "+":
      case "-":
      case "*":
      case "/":
      case "%":
        return from + 1;
      default:
        return -from - 1;
    }
  }

  public static int parseComp(String[] program, int from) {
    switch (program[from]) {
      case "==":
      case "!=":
      case "<=":
      case "<":
      case ">=":
      case ">":
        return from + 1;
      default:
        return -from - 1;
    }
  }

  public static int parseExpressionNoBinop(String[] program, int from) {
    // <number>
    int number = parseNumber(program, from);
    if (number > 0)
      return number;
    // <name>
    int name = parseName(program, from);
    if (name > 0)
      return name;
    // (<expr>)
    if (program[from].equals("(")) {
      from += 1;
      from = parseExpression(program, from);
      if (from < 0)
        return from;
      if (program[from].equals(")"))
        return from + 1;
      else
        return -from - 1;
    }
    // <unop> <expr>
    from = parseUnop(program, from);
    if (from > 0)
      return parseExpression(program, from);
    else
      return from;
  }

  public static int parseExpression(String[] program, int from) {
    from = parseExpressionNoBinop(program, from);
    if (from < 0)
      return from;

    while (true) {
      int binop = parseBinop(program, from);
      if (binop < 0)
        break;
      from = parseExpressionNoBinop(program, binop);
      if (from < 0)
        return from;
    }
    return from;
  }

  public static int parseBbinop(String[] program, int from) {
    switch (program[from]) {
      case "&&":
        return from + 1;
      case "||":
        return from + 1;
      default:
        return -from - 1;
    }
  }
  
  public static int parseBunop(String[] program, int from) {
    if (program[from].equals("!"))
      return from + 1;
    return -from - 1;
  }

  public static int parseConditionNoBinary(String[] program, int from) {
    // true
    if (program[from].equals("true"))
      return from + 1;
    // false
    if (program[from].equals("false"))
      return from + 1;
    // (<cond>)
    if (program[from].equals("(")) {
      from += 1;
      from = parseCondition(program, from);
      if (program[from].equals(")"))
        return from + 1;
      else
        return -from - 1;
    }
    int bun = parseBunop(program, from);
    if(bun >= 0) {
      from = bun;
      if (program[from].equals("(")) {
        from += 1;
        from = parseCondition(program, from);
        if (program[from].equals(")"))
          return from + 1;
        else
          return -from - 1;
      } 
    }
    // <expr> <comp> <expr>
    int expr = parseExpression(program, from);
    if (expr >= 0) {
      from = parseComp(program, expr);
      if (from < 0)
        return from;
      return parseExpression(program, from);
    }
    return -from - 1;
  }

  public static int parseCondition(String[] program, int from) {
    from = parseConditionNoBinary(program, from);
    if (from < 0)
      return from;

    while (true) {
      int binop = parseBbinop(program, from);
      if (binop < 0)
        break;
      from = parseConditionNoBinary(program, binop);
      if (from < 0)
        return from;
    }
    return from;
  }

  public static int parseAssigment(String[] program, int from) {
    from = parseName(program, from);
    if (from < 0)
      return from;
    if (!program[from].equals("="))
      return -from - 1;
    from += 1;
    if (from + 2 < program.length
        && (program[from] + program[from + 1] + program[from + 2]).equals("read()"))
      return from + 3;
    return parseExpression(program, from);
  }

  public static int parseWhileIteCond(String[] program, int from) {
    if (program[from].equals("("))
      from++;
    else
      return -from - 1;
    from = parseCondition(program, from);
    if (from < 0)
      return from;
    if (!program[from].equals(")"))
      return -from - 1;
    return from + 1;
  }

  public static int parseIte(String[] program, int from) {
    if (!program[from].equals("if"))
      return -from - 1;
    from = parseWhileIteCond(program, from + 1);
    if (from < 0)
      return from;
    from = parseStatement(program, from);
    if (from < 0)
      return from;
    if (from < program.length && program[from].equals("else"))
      from = parseStatement(program, from + 1);
    return from;
  }

  public static int parseStatement(String[] program, int from) {
    switch (program[from]) {
      case ";":
        return from + 1;
      case "{":
        int next = from + 1;
        do {
          from = next;
          next = parseStatement(program, next);
        } while (next > 0);
        if (program[from].equals("}"))
          return from + 1;
        else
          return -next - 1;
      case "while":
        from = parseWhileIteCond(program, from + 1);
        if (from < 0)
          return from;
        return parseStatement(program, from);
      case "if":
        return parseIte(program, from);
      case "write":
        if (!program[from + 1].equals("("))
          return -from - 1;
        from = parseExpression(program, from + 2);
        if (from < 0 || !program[from].equals(")"))
          return -from - 1;
        if (!program[from + 1].equals(";"))
          return -from - 2;
        return from + 2;
      default: {
        int ass = parseAssigment(program, from);
        if (ass >= 0) {
          if (program[ass].equals(";"))
            return ass + 1;
          else
            return -ass - 1;
        }
        return -from - 1;
      }
    }
  }

  public static int parseProgram(String[] program) {
    int from;
    int next = 0;
    do {
      from = next;
      next = parseDecl(program, from);
    } while (next >= 0);
    while (from >= 0 && from < program.length - PADDING)
      from = parseStatement(program, from);
    if (from != program.length - PADDING)
      return -1;
    else
      return from;
  }

  public static void main(String[] args) {
    String textProgram = readProgramConsole();
    // System.out.println("Read program, lexing...");
    String[] program = lex(textProgram);

//    System.out.println(java.util.Arrays.toString(program));

    int parsed = parseProgram(program);
    if (parsed < 0)
      System.out.println("Error: Unable to parse program");
    else
      System.out.println("Parse successful.");
  }
}
