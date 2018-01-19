package Aufgabenblatt11;

public class EmptyStatement extends Statement {

    public String toString() {
        return "";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
