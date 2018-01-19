package Aufgabenblatt11;

public class Declaration {
    private String[] names;
    private String type = "int"; // Default to int here, would need an enum and additional parsing

    public Declaration(String[] names) {
        super();
        this.names = names;
    }

    public Declaration(String a) {
        this.names = new String[]{a};
    }

    public Declaration(String a, String b) {
        this.names = new String[]{a, b};
    }

    public String[] getNames() {
        return names;
    }

    public String toString() {
        String nms = "";
        for (String name : names) {
            nms = nms + name + ", ";
        }
        nms = nms.substring(0, Math.max(nms.length() - 2, 0));
        return type + " " + nms + ";";

    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
