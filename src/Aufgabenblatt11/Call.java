package Aufgabenblatt11;

public class Call extends Expression {
    private String functionName;
    private Expression[] arguments;

    public Call(String functionName, Expression[] arguments) {
        super();
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Expression[] getArguments() {
        return arguments;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int firstLevelPriority() {
        return 1;
    }

    public String toString() {
        String args = "";
        for (Expression arg : arguments) {
            args = args + arg.toString() + ", ";
        }
        args = args.substring(0, Math.max(args.length() - 2, 0));
        return functionName + "(" + args + ")";
    }

}
