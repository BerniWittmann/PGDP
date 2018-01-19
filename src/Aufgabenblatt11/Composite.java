package Aufgabenblatt11;

public class Composite extends Statement {
    private Statement[] statements;

    public Composite(Statement[] statements) {
        super();
        this.statements = statements;
    }

    public Composite(Statement s) {
        this.statements = new Statement[]{s};
    }

    public Composite(Statement s1, Statement s2) {
        this.statements = new Statement[]{s1, s2};
    }

    public Statement[] getStatements() {
        return statements;
    }

    public String toString() {
        String stmnts = "";
        for (Statement st : statements) {
            stmnts = stmnts + st.toString() + "\n";
        }
        return stmnts;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
