package Aufgabenblatt11;


public class Assignment extends Statement {
    private String name;
    private Expression expression;

    public Assignment(String name, Expression expression) {
        super();
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public Expression getExpression() {
        return expression;
    }

    public String toString() {
        return getName() + " = " + getExpression().toString() + ";";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
