package Aufgabenblatt11;

public class BinaryCondition extends Condition {
    private Condition lhs;
    private Bbinop operator;
    private Condition rhs;

    public BinaryCondition(Condition lhs, Bbinop operator, Condition rhs) {
        super();
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    public Condition getLhs() {
        return lhs;
    }

    public Bbinop getOperator() {
        return operator;
    }

    public Condition getRhs() {
        return rhs;
    }

    public String toString() {
        String result = "";
        if (lhs.firstLevelPriority() > 1) {
            result += "(" + lhs.toString() + ")";
        } else {
            result += lhs.toString();
        }
        if (rhs != null) {
            result += " " + operator.toString() + " ";
            if (rhs.firstLevelPriority() > 1) {
                result += "(" + rhs.toString() + ")";
            } else {
                result += rhs.toString();
            }
        }
        return result;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int firstLevelPriority() {
        return (lhs != null && rhs != null) ? 2 : 1;
    }

}
