package password;

public class IllegalCharExc extends Exception {
    private final char used;

    public IllegalCharExc(char used) {
        super("Password darf das Zeichen \\" + used + "nicht enthalten.");
        this.used = used;

    }

    public String toString() {
        String maskedChar;
        switch ((int) used) {
            case 0:
                maskedChar = "Nullzeichen (\\0)";
                break;
            case 7:
                maskedChar = "Tonsignal (\\a)";
                break;
            case 8:
                maskedChar = "Rückschritt (\\b)";
                break;
            case 9:
                maskedChar = "Tabulator (\\t)";
                break;
            case 10:
                maskedChar = "Zeilenumbruch (\\n)";
                break;
            case 11:
                maskedChar = "Vertikaler Tabulator (\\v)";
                break;
            case 12:
                maskedChar = "Seitenvorschub (\\f)";
                break;
            case 13:
                maskedChar = "Wagenrücklauf (\\r)";
                break;
            case 32:
                maskedChar = "Leerzeichen";
                break;
            default:
                maskedChar = used + "";
                break;
        }
        return "Password darf das Zeichen " + maskedChar + " nicht enthalten.";
    }

}
