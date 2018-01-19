package Aufgabenblatt11;

public enum Bunop {
    Not;

    @Override
    public String toString() {
        switch(this) {
            case Not: return "!";
            default: return super.toString();
        }
    }
}
