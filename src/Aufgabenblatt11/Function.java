package Aufgabenblatt11;

public class Function {
    private String name;
    private String[] parameters;
    private Declaration[] declarations;
    private Statement[] statements;

    public Function(String name, String[] parameters, Declaration[] declarations,
                    Statement[] statements) {
        super();
        this.name = name;
        this.parameters = parameters;
        this.declarations = declarations;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public String[] getParameters() {
        return parameters;
    }

    public Declaration[] getDeclarations() {
        return declarations;
    }

    public Statement[] getStatements() {
        return statements;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String toString() {
        String stmnts = "";
        for (Statement st : statements) {
            stmnts = stmnts + st.toString() + "\n";
        }
        String dclrts = "";
        for (Declaration dcl : declarations) {
            dclrts = dclrts + dcl.toString() + "\n";
        }
        String params = "";
        for (String param : parameters) {
            params = params + param + ", ";
        }
        params = params.substring(0, Math.max(params.length() - 2, 0));

        return name + "(" + params + ") {\n" + dclrts + stmnts + "\n}";
    }
}
