package Aufgabenblatt11_2;

public class Declaration {
  private String[] names;
  
  public String[] getNames() {
    return names;
  }

  private int[] indices;

  public int[] getIndices() {
    return indices;
  }
  
  public Declaration(String[] names, int[] indices) {
    super();
    this.names = names;
    this.indices = indices;
  }

  public Declaration(String[] names) {
    super();
    this.names = names;
    this.indices = new int[names.length];
    for (int i = 0; i < names.length; i++) {
      indices[i] = -1;
    }
  }
  
  public Declaration(String a) {
    this.names = new String [] { a };
    this.indices = new int[] { -1 };
  }

  public Declaration(String a, String b) {
    this.names = new String [] { a, b };
    this.indices = new int[] { -1, -1 };
  }

  public Declaration(String a, int index) {
    this.names = new String [] { a };
    this.indices = new int[] { index };
  }

  public Declaration(String a, String b, int indexA, int indexB) {
    this.names = new String [] { a, b };
    this.indices = new int[] { indexA, indexB };
  }

  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
}
