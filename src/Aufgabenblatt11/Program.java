package Aufgabenblatt11;


public class Program {
    private Function[] functions;

    public Program(Function[] functions) {
        super();
        this.functions = functions;
    }

    public Function[] getFunctions() {
        return functions;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        String fns = "";
        for (Function fn : functions) {
            fns = fns + fn.toString() + "\n\n";
        }
        return fns;
    }
}
