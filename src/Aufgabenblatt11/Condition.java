package Aufgabenblatt11;

public abstract class Condition {
    public abstract void accept(Visitor visitor);

    public abstract int firstLevelPriority();
}

