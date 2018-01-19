package Aufgabenblatt11;

public class Variable extends Expression {
    private String name;

    public Variable(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
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
