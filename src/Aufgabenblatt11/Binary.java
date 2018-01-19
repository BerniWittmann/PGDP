package Aufgabenblatt11;

public class Binary extends Expression {
    private Expression lhs;
    private Binop operator;
    private Expression rhs;

    public Binary(Expression lhs, Binop operator, Expression rhs) {
        super();
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    public Expression getLhs() {
        return lhs;
    }

    public Binop getOperator() {
        return operator;
    }

    public Expression getRhs() {
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

    public int firstLevelPriority() {
        return (lhs != null && rhs != null) ? 2 : 1;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
