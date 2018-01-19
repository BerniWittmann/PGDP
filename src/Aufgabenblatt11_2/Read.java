package Aufgabenblatt11_2;

public class Read extends Statement {
  private String name;
  
  public String getName() {
    return name;
  }

  private int index;

  public int getIndex() {
    return index;
  }
  
  public Read(String name, int index) {
    super();
    this.name = name;
    this.index = index;
  }

  public Read(String name) {
    super();
    this.name = name;
    this.index = -1;
  }

  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }


}
