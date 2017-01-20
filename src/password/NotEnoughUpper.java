package password;

public class NotEnoughUpper extends NotEnoughLetter{
    private final int should;
    private final int is;

    public NotEnoughUpper(int should, int is) {
        super(should, is);
        this.should = should;
        this.is = is;
    }

    public String toString() {
        return "Passwort sollte mindestens " + should + " Großbuchstaben haben, hat aber nur " + is + " Großbuchstaben.";
    }
}
