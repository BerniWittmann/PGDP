import Linja.MiniJava;

public class DameSpiel extends MiniJava {

    private int nrRows, nrColumns; // Board dimensions
    private boolean[][] board;     // true = queen, false = empty
    private boolean whiteToMove;   // Whose turn it is
    private String white, black;   // Players' names

    private int minColumns = 5; //untere Grenze für Eingabe von Spaltenzahl
    private int maxColumns = 8; //obere Grenze für Eingabe von Spaltenzahl
    private boolean beendet = false; //gibt an ob das Spiel beendet ist.


    /**
     * List einen integer aus eineml gütigen Bereich ein
     *
     * @param text        AnzeigeTextt
     * @param lowerBorder Untere Grenze (inklusive)
     * @param upperBorder Obere Grenze (inklusive)
     * @return int
     */
    private int readInt(String text, int lowerBorder, int upperBorder) {
        boolean gueltig;
        int x;
        //wiederhole Abfrage solange bis Eingabe gültig ist
        do {
            //Einlesen
            x = readInt(text);
            //Prüfung ob x in den Grenzen
            gueltig = x >= lowerBorder && x <= upperBorder;
        } while (!gueltig);

        return x;
    }

    /**
     * Extrahiert die Reihe aus einer Position
     *
     * @param position
     * @return
     */
    private int getRow(int position) {
        return position % 10 - 1;
    }

    /**
     * Extrahiert die Spalte aus einer Position
     *
     * @param position
     * @return
     */
    private int getColumn(int position) {
        return position / 10 - 1;
    }

    /**
     * Prüft ob ein Zug erlaubt ist
     *
     * @param position
     * @return
     */
    private boolean checkZug(int position) {
        int col = getColumn(position);
        int row = getRow(position);

        //Prüfe Felder
        for (int x = 0; x < nrColumns; x++) {
            for (int y = 0; y < nrRows; y++) {
                //Prüfe direktes Feld
                if (col == x && row == y && board[x][y]) return false;

                //Prüfe Reihe
                if (row == y && board[x][y]) return false;

                //Prüfe Spalte
                if (col == x && board[x][y]) return false;
            }
        }

        //Prüfe Diagonale 1 (von position nach rechts oben)
        for (int i = 0; i < (nrColumns - col); i++) {
            int x = col + i;
            int y = row + i;
            if (x < nrColumns && y < nrRows) {
                if(board[x][y]) return false;
            }
        }

        //Prüfe Diagonale 2 (von position nach links unten)
        for (int i = (col - 1); i >= 0; i--) {
            int x = col - i;
            int y = row - i;
            if (x >= 0 && y >= 0) {
                if(board[x][y]) return false;
            }
        }

        //Prüfe Diagonale 3 (von position nach rechts unten)
        for (int i = 1; i < (nrColumns - col); i++) {
            int x = col + i;
            int y = row - i;
            if (x < nrColumns && y >= 0) {
                if(board[x][y]) return false;
            }
        }

        //Prüfe Diagonale 1 (von position nach links oben)
        for (int i = (col - 1); i >= 0; i--) {
            int x = col - i;
            int y = row + i;
            if (x >= 0 && y < nrRows) {
                if(board[x][y]) return false;
            }
        }

        return true;
    }

    /**
     * Spieler gibt auf
     */
    private void aufgabe() {
        beendet = true;
    }

    /**
     * Liest den Spielzug vom Spieler ein
     * @return
     */
    private int readMove() {
        //Speichert den Spielernamen der dran ist in Player
        String player = whiteToMove ? white : black;

        int pos;
        boolean gueltig;
        do {
            pos = readInt("Spieler " + player + ": Wähle ein Feld;");
            //wenn -1 eingegeben wird, gibt der Spieler auf
            if(pos == -1) {
                aufgabe();
                return -1;
            }
            //prüfung ob im Spielfeld
            if(pos < 11 || getRow(pos) > nrRows - 1 || getColumn(pos) > nrColumns -1) {
                gueltig = false;
                write("Falsche Eingabe! Gib -1 ein zum Aufgeben");
            } else if (!checkZug(pos)) {
                //Prüfung ob Zug gültig
                gueltig = false;
                write("Ungültiger Zug! Gib -1 ein zum Aufgeben");
            } else {
                gueltig = true;
            }
        }while(!gueltig);

        return pos;
    }

    /**
     * Prüft ob noch Züge möglich sind.
     * @return
     */
    private boolean checkEnd() {
        //Durchlaufe alle Felder und Prüfe ob gültiger Zug
        for (int x = 1; x <= nrColumns; x++) {
            for (int y = 1; y <= nrRows; y++) {
                if(checkZug((x*10 + y))) return false;
            }
        }
        return true;
    }


    /**
     * Der Konstruktor registriert Spielernamen fuer Weiss und Schwarz.
     *
     * @param white Name des als 'Weiss' bezeichneten Spielers
     * @param black Name des als 'Schwarz' bezeichneten Spielers
     */
    public DameSpiel(String white, String black) {
        this.white = white;
        this.black = black;
    }


    /**
     * Gibt das Spielbrett aus.
     */
    private void printBoard() {
        for (int j = board[0].length - 1; j >= 0; j--) {
            System.out.print("\n " + (1 + j));
            for (int i = 0; i < board.length; i++) {
                System.out.print(board[i][j] ? " X" : " -");
            }
        }
        System.out.print("\n  ");
        for (int i = 1; i <= board.length; i++) {
            System.out.print(" " + i);
        }
        System.out.println("\n" + (whiteToMove ? white : black) + " ist am Zug.");
    }


    /**
     * Initialisiert das Spielbrett ueberall mit false.
     * Dazu wird (ggf. neuer) Speicher allokiert.
     */
    private void initBoard() {
        //Board mit entsprechender Größe initialisieren, automatisch werden alle Felder mit False befüllt
        board = new boolean[nrColumns][nrRows];
    }


    /**
     * Ermittelt die Groesse des Spielbretts gemaess den Spielregeln.
     * Das Ergebnis der Abfrage wird in den Attributen nrRows und nrColumns abgelegt.
     */
    private void determineBoardSize() {
        //Weiß wählt eine Zahl
        String text = "Spieler " + white + ": Bitte wähle eine Zahl zwischen " + minColumns + " und " + maxColumns + ": ";
        nrColumns = readInt(text, minColumns, maxColumns);
        //Schwarz wählt Zahl
        text = "Spieler " + black + ": Bitte wähle eine Zahl zwischen " + (nrColumns - 1) + " und " + (nrColumns + 1) + ": ";
        nrRows = readInt(text, (nrColumns - 1), (nrColumns + 1));
    }


    /**
     * Ermittelt, wer anfaengt zu ziehen.
     * Das Ergebnis der Abfrage wird im Attribut whiteToMove abgelegt.
     */
    private void determineFirstPlayer() {
        boolean gueltig;
        String s;
        //Wiederhole solange bis gültige Eingabe
        do {
            //Lese String ein
            s = readString("Spieler " + white + ": Bitte gib an wer anfangen soll: (" + white + " oder " + black + ")");
            //prüfe ob string gültig ist. String Vergleich so nicht gut, allerdings String.equalsIgnoreCase() nicht erlaubt.. :(
            gueltig = s.equals(white) || s.equals(black);
        } while (!gueltig);
        //Setze white to move
        whiteToMove = s.equals(white);
    }


    /**
     * Fuehrt den Zug aus.
     *
     * @param move der auszufuehrende Zug!
     */
    private void applyMove(int move) {
        int col = getColumn(move);
        int row = getRow(move);
        board[col][row] = true;
    }


    /**
     * Startet die Hauptschleife des Spiels
     * mit der Abfrage nach Zuegen.
     * Die Schleife wird durch Eingabe von -1 beendet.
     */
    private void mainLoop() {
        int pos;
        do {
            pos = readMove();
            if(pos == -1) break;
            applyMove(pos);
            whiteToMove = !whiteToMove;
            printBoard();
            beendet = checkEnd();
        }while(!beendet);
    }


    /**
     * Informiert die Benutzerin ueber den Ausgang des Spiels.
     * Speziell: Wer hat gewonnen (Weiss oder Schwarz)?
     */
    private void reportWinner() {
        String player = !whiteToMove ? white : black;
        write("Spieler " + player + " hat gewonnen!");
    }


    /**
     * Startet das Spiel.
     */
    public void startGame() {
        determineBoardSize();
        initBoard();
        determineFirstPlayer();
        printBoard();
        mainLoop();
        reportWinner();
    }


    public static void main(String[] args) {
        DameSpiel ds = new DameSpiel("Weiß", "Schwarz");
        ds.startGame();
    }

}
