package Aufgabenblatt11;

public class Unary extends Expression {
    private Unop operator;
    private Expression operand;

    public Unary(Unop operator, Expression operand) {
        super();
        this.operator = operator;
        this.operand = operand;
    }

    public Unop getOperator() {
        return operator;
    }

    public Expression getOperand() {
        return operand;
    }

    public String toString() {
        return operator.toString() + (operand.firstLevelPriority() > 1 ? "(" + operand.toString() + ")" : operand.toString());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int firstLevelPriority() {
        return 1;
    }
}
