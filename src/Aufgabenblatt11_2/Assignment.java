package Aufgabenblatt11_2;

public class Assignment extends Statement {
  private String name;
  
  public String getName() {
    return name;
  }

  private int index; // Array reference. Index of -1 means that it's not an array

  public int getIndex() {
    return index;
  }
  
  private Expression expression;
  
  public Expression getExpression() {
    return expression;
  }
  
  public Assignment(String name, Expression expression, int index) {
    super();
    this.name = name;
    this.expression = expression;
    this.index = index;
  }

  public Assignment(String name, Expression expression) {
    super();
    this.name = name;
    this.expression = expression;
    this.index = -1;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
