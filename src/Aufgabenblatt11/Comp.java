package Aufgabenblatt11;

public enum Comp {
    Equals, NotEquals, LessEqual, Less, GreaterEqual, Greater;

    @Override
    public String toString() {
        switch(this) {
            case Equals: return "==";
            case NotEquals: return "!=";
            case LessEqual: return "<=";
            case Less: return "<";
            case GreaterEqual: return ">=";
            case Greater: return ">";
            default: return super.toString();
        }
    }
}
