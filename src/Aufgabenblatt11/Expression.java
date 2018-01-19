package Aufgabenblatt11;

public abstract class Expression {
    public abstract void accept(Visitor visitor);

    public abstract int firstLevelPriority();
}
