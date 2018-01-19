package Aufgabenblatt11;

public class True extends Condition {

    public String toString() {
        return "true";
    }

    public int firstLevelPriority() {
        return 1;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
