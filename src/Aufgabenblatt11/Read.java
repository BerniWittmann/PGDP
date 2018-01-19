package Aufgabenblatt11;

public class Read extends Statement {
    private String name;

    public Read(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "read(" + name + ");";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
