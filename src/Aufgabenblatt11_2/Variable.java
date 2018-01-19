package Aufgabenblatt11_2;

public class Variable extends Expression {
  private String name;
  
  public String getName() {
    return name;
  }

  private int index;

  private int getIndex() {
    return index;
  }
  
  public Variable(String name, int index) {
    super();
    this.name = name;
    this.index = index;
  }

  public Variable(String name) {
    super();
    this.name = name;
    this.index = -1;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
