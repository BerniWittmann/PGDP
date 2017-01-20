package password;

public class NotEnoughExc extends Exception{
    private final int should;
    private final int is;

    public NotEnoughExc(int should, int is) {
        super("Passwort sollte " + should + " Zeichen einer Kategorie haben, hat aber nur " + is + " Zeichen.");
        this.should = should;
        this.is = is;

    }

    public String toString() {
        return "Passwort sollte " + should + " Zeichen einer Kategorie haben, hat aber nur " + is + " Zeichen.";
    }
}
