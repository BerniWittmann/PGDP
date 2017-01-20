package password;

public class NotEnoughSpecial extends NotEnoughExc{
    private final int should;
    private final int is;

    public NotEnoughSpecial(int should, int is) {
        super(should, is);
        this.should = should;
        this.is = is;
    }

    public String toString() {
        return "Passwort sollte mindestens " + should + " Sonderzeichen haben, hat aber nur " + is + " Sonderzeichen.";
    }
}
