package Aufgabenblatt11;

public class False extends Condition {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int firstLevelPriority() {
        return 1;
    }

    public String toString() {
        return "false";
    }

}
