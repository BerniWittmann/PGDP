package Aufgabenblatt11;

public enum Unop {
    Minus;

    @Override
    public String toString() {
        switch(this) {
            case Minus: return "-";
            default: return super.toString();
        }
    }
}
