package password;

public class NotEnoughLower extends NotEnoughLetter{
    private final int should;
    private final int is;

    public NotEnoughLower(int should, int is) {
        super(should, is);
        this.should = should;
        this.is = is;
    }

    public String toString() {
        return "Passwort sollte mindestens " + should + " Kleinbuchstaben haben, hat aber nur " + is + " Kleinbuchstaben.";
    }
}
