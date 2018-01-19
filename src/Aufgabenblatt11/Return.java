package Aufgabenblatt11;

public class Return extends Statement {
    private Expression expr;

    public Return(Expression expr) {
        super();
        this.expr = expr;
    }

    public Expression getExpression() {
        return expr;
    }

    //** ich weiß dass die Expression in String umgewandelt werden muss komme aber nicht weiter
    public String toString() {
        return "return " + expr.toString() + ";";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
