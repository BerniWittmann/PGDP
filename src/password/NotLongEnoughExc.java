package password;

public class NotLongEnoughExc extends Exception{
    private final int should;
    private final int is;

    public NotLongEnoughExc(int should, int is) {
        super("Passwort nicht lang genug! Sollte " + should + " Zeichen haben, hat aber nur " + is + " Zeichen.");
        this.should = should;
        this.is = is;

    }

    public String toString() {
        return "Passwort nicht lang genug! Sollte eine Länge von " + should + " Zeichen haben, hat aber nur " + is + " Zeichen.";
    }
}
