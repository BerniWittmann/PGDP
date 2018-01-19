package Aufgabenblatt11;

public class While extends Statement {
    private Condition cond;
    private Statement body;

    public While(Condition cond, Statement body) {
        super();
        this.cond = cond;
        this.body = body;
    }

    public Condition getCond() {
        return cond;
    }

    public Statement getBody() {
        return body;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    //**Hier muss definitiv ein toString Statement implementiert werden jedoch scheitert die Umsetzung
    public String toString() {
        return "while (" + cond.toString() + ") {\n" + body.toString() + "\n}";
    }
}
