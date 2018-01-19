package Aufgabenblatt11;

public class ExpressionStatement extends Statement {
    private Expression expression;

    public ExpressionStatement(Expression expression) {
        super();
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public void accept(Visitor visitor) {
    }

    public String toString() {
        return expression.toString() + ";";
    }

    public int firstLevelPriority() {
        return 1;
    }
}

