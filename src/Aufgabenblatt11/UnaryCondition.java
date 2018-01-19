package Aufgabenblatt11;

public class UnaryCondition extends Condition {
    private Bunop operator;
    private Condition operand;

    public UnaryCondition(Bunop operator, Condition operand) {
        super();
        this.operator = operator;
        this.operand = operand;
    }

    public Bunop getOperator() {
        return operator;
    }

    public Condition getOperand() {
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
