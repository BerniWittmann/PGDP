package password;

public class NotEnoughNumber extends NotEnoughExc{
    private final int should;
    private final int is;

    public NotEnoughNumber(int should, int is) {
        super(should, is);
        this.should = should;
        this.is = is;
    }

    public String toString() {
        return "Passwort sollte mindestens " + should + " Zahlen haben, hat aber nur " + is + " Zahlen.";
    }
}
