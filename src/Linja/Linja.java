package Linja;

public class Linja extends MiniJava {

    private static int[][] spielfeld = new int[8][6];

    /**
     * initialisiert das Spielfeld
     * Ziellinie fuer Spieler 1 ist Zeile 7
     * Ziellinie fuer Spieler -1 ist Zeile 0
     */
    private static void initSpiel() {
        for (int i = 0; i < spielfeld.length; i++) {
            if (i != 0 && i != spielfeld.length - 1) {
                spielfeld[i] = new int[]{-(12 - i + 1), 0, 0, 0, 0, 6 + i};
            }
            if (i == 0) {
                spielfeld[i] = new int[]{1, 2, 3, 4, 5, 6};
            }
            if (i == spielfeld.length - 1) {
                spielfeld[i] = new int[]{-6, -5, -4, -3, -2, -1};
            }
        }

    }

    /**
     * @return formatiertes aktuelles Spielfeld
     */
    private static String output() {
        String tmp = "Spieler 1 spielt von oben nach unten\n"
                + "Spieler -1 spielt von unten nach oben\n";
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                tmp = tmp + "\t" + spielfeld[i][j];
            }
            tmp = tmp + "\n";
        }
        return tmp;
    }

    /**
     * @return true, wenn die Eingabe stein im richtigen Wertebereich liegt und
     * zum Spieler gehoert; false, sonst
     */
    private static boolean gueltigeEingabe(int stein, int spieler) {
        if (spieler > 0 && stein > 0 && stein <= 12) {
            //prüfen ob stein zu spieler 1 gehört und zulässige Werte (1 bis 12) hat
            return true;
        } else if (spieler < 0 && stein < 0 && stein >= -12) {
            //prüfen ob stein zu spieler 2 gehört und zulässige Werte (-1 bis -12) hat
            return true;
        }
        //Ansonsten falsch zurückgeben
        return false;
    }

    /**
     * @param stein kann Werte -1 bis -12 und 1 bis 12 haben
     * @return gibt x-Koordinate von stein an Position 0 und die y-Koordinaten
     * von stein an Position 1 zurueck; falls stein nicht gefunden, wird {-1,-1}
     * zurueckgegeben
     */
    private static int[] findeStein(int stein) {
        //Default Wert -1,-1
        int[] steinPosition = {-1, -1};

        //Prüfen ob Stein korrekte Werte enthält
        if (stein < -12 || stein == 0 || stein > 12) {
            return steinPosition;
        }

        //Durchlaufen des Spielfelds durch Reihen
        for (int i = 0; i < spielfeld.length; i++) {
            //Durchlaufen des Spielfelds durch Spalten
            for (int j = 0; j < spielfeld[i].length; j++) {
                //Prüfen Feld mit Stein übereinstimmt
                if (spielfeld[i][j] == stein) {
                    //Position zurückgeben
                    steinPosition[0] = j;
                    steinPosition[1] = i;
                    return steinPosition;
                }
            }
        }

        return steinPosition;
    }

    /**
     * @param reihe hat Werte 0 bis 7
     * @return Anzahl der Steine in einer Reihe
     */
    private static int steineInReihe(int reihe) {
        //Standardmäßig Null Steine pro Reihe
        int anzahl = 0;
        //Durchlaufend der Spalten der Reihe
        for (int i = 0; i < spielfeld[reihe].length; i++) {
            //Prüfen ob ein Stein auf dem Feld liegt
            if (spielfeld[reihe][i] != 0) {
                //Anzahl inkrementieren
                anzahl++;
            }
        }
        //Berechnete Anzahl zurückgeben
        return anzahl;
    }

    /**
     * Ueberprueft, ob der Zug zulaessig ist und fuehrt diesen aus, wenn er
     * zulaessig ist.
     *
     * @param vorwaerts == true: Zug erfolgt vorwaerts aus Sicht des
     *                  Spielers/Steins vorwearts == false: Zug erfolgt rueckwaerts aus Sicht des
     *                  Spielers/Steins
     * @return Rueckgabe -1: Zug nicht zulaessig Rueckgabe 0-5: Weite des
     * potentiellen naechsten Zugs (falls Folgezug folgt) Rueckgabe 6: Ziellinie
     * wurde genau getroffen (potentieller Bonuszug)
     */
    private static int setzeZug(int stein, int weite, boolean vorwaerts) {
        int zielReihe;
        if (vorwaerts && stein > 0 || !vorwaerts && stein < 0) {
            //Vorwärtslaufen (Standardfall)
            //Zielreihe Berechnen
            zielReihe = findeStein(stein)[1] + weite;
        } else {
            //Rückwärtslaufen ist nur im Bonuszug erlaubt
            //Zielreihe Berechnen
            zielReihe = findeStein(stein)[1] - weite;
        }

        //Prüfe Treffen Ziellinie
        if (stein > 0) {
            //Spieler 1
            if (zielReihe >= spielfeld.length) {
                //Ziellinie überquert

                //bewege Stein vom Spielfeld
                setzeStein(stein, zielReihe);

                if (zielReihe == spielfeld.length) {
                    //Ziellline getroffen --> Bonuszug
                    return 6;
                }

                //Stein im Ziel --> keine Folgezüge
                return 0;
            } else if (zielReihe < 0) {
                //Prüfung eigenes Ende
                return -1;
            }
        } else if (stein < 0) {
            //Spieler 2
            if (zielReihe < 0) {
                //Ziellinie überquert

                //bewege Stein vom Spielfeld
                setzeStein(stein, zielReihe);

                if (zielReihe == -1) {
                    //Ziellline getroffen --> Bonuszug
                    return 6;
                }
                //Stein im Ziel --> keine Folgezüge
                return 0;
            } else if (zielReihe >= spielfeld.length) {
                //Prüfung eigenes Ende
                return -1;
            }
        }

        //Anzahl Steine in Zielreihe berechnen
        int anzahlSteineInZielReihe = steineInReihe(zielReihe);
        //Prüfung ob Reihe besetzt
        if (anzahlSteineInZielReihe >= 6) {
            return -1;
        }

        //Bewege Stein auf neue Position
        setzeStein(stein, zielReihe);
        //Gebe Anzahl Folgezüge zurück
        return anzahlSteineInZielReihe;

    }

    /**
     * Speichert die neue Position des Steins im Spielfeld und gibt alte Position frei
     *
     * @param stein     Der gezogene Stein
     * @param zielReihe Die Reihe in der der Stein gezogen werden soll
     */
    private static void setzeStein(int stein, int zielReihe) {
        //Finde Position des Steins
        int[] position = findeStein(stein);

        //Gib alte Position Frei
        spielfeld[position[1]][position[0]] = 0;


        //Prüfe ob zielReihe im Feld
        // wenn nicht (also Stein ist im Ziel), dann soll keine neue Position vergeben werden
        if (zielReihe >= 0 && zielReihe < spielfeld.length) {
            //Finde neue Position

            //Durchlaufe Spalten der Zielreihe
            for (int i = 0; i < spielfeld[zielReihe].length; i++) {
                //Prüfe ob Feld frei, also 0 ist
                if (spielfeld[zielReihe][i] == 0) {
                    spielfeld[zielReihe][i] = stein;
                    break;
                }
            }
        }
    }

    /**
     * @return true, falls die Bedingungen des Spielendes erfuellt sind, d.h.
     * alle Steine des einen Spielers sind an den Steinen des gegnerischen Spielers
     * vorbeigezogen
     */
    private static boolean spielende() {
        //Berechne letzte Reihe für beide Spieler
        int letzteReiheSpieler1 = getLetzterSteinReihe(1);
        int letzteReiheSpieler2 = getLetzterSteinReihe(-1);
        //Sollte einer von beiden alle Steine im Ziel haben, true zurückgeben
        if (letzteReiheSpieler1 < 0 || letzteReiheSpieler2 < 0) {
            return true;
        }
        //True, wenn der letzte Stein von Spieler 1 an Spieler 2 vorbei ist
        //Sonst false
        return letzteReiheSpieler1 > letzteReiheSpieler2;
    }

    /**
     * Gibt die Reihe des Steines der am weitesten vom Ziel entfernt ist
     *
     * @param spieler Spieler: entweder 1 oder -1
     * @return Reihe des Steins, der am weitesten vom Ziel des Spielers entfernt ist.
     */
    private static int getLetzterSteinReihe(int spieler) {
        if (spieler > 0) {
            //Spieler 1
            //Durchlaufen der Reihen vom Start zum Ziel
            for (int i = 0; i < spielfeld.length; i++) {
                //Prüfen ob überhaupt ein Stein in der Reihe ist
                if (steineInReihe(i) > 0) {
                    //Durchlaufen der Felder der Reihe
                    for (int j = 0; j < spielfeld[i].length; j++) {
                        //Prüfen ob Stein Spieler 1 gehört
                        if (spielfeld[i][j] > 0) {
                            //Reihe zurückgeben
                            return i;
                        }
                    }
                }
            }
        } else {
            //Spieler 2
            //Durchlaufen der Reihen vom Start zum Ziel
            for (int i = (spielfeld.length - 1); i >= 0; i--) {
                //Prüfen ob überhaupt ein Stein in der Reihe ist
                if (steineInReihe(i) > 0) {
                    //Durchlaufen der Felder der Reihe
                    for (int j = 0; j < spielfeld[i].length; j++) {
                        //Prüfen ob Stein Spieler 2 gehört
                        if (spielfeld[i][j] < 0) {
                            //Reihe zurückgeben
                            return i;
                        }
                    }
                }
            }
        }
        //Wenn keine Steine mehr auf dem Feld sind, -1 zurückgeben
        return -1;
    }

    /**
     * zaehlt die Punkte der beiden Spieler und gibt das Ergebnis aus
     */
    private static void zaehlePunkte() {
        //initialisiere Punkte
        int punkteSpieler1 = 0;
        int punkteSpieler2 = 0;
        //Steine noch nicht pro Spieler
        int steineUebrigSpieler1 = 12;
        int steineUebrigSpieler2 = 12;

        //Durchlaufen jedes Feldes des Spielfelds
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                //Prüfen welchem Spieler der Stein gehört
                if (spielfeld[i][j] > 0) {
                    //Spieler 1
                    punkteSpieler1 += berechneWert(i);
                    //Merke Stein als gezählt
                    steineUebrigSpieler1--;
                } else if (spielfeld[i][j] < 0) {
                    //Spieler 2
                    punkteSpieler2 += berechneWert(i);
                    //Merke Stein als gezählt
                    steineUebrigSpieler2--;
                }
            }
        }

        //Addieren der Steine die schon im Ziel sind
        punkteSpieler1 += steineUebrigSpieler1 * berechneWert(-1);
        punkteSpieler2 += steineUebrigSpieler2 * berechneWert(-1);

        //Zeige Ergebnis an
        zeigeErgebnis(punkteSpieler1, punkteSpieler2);
    }

    /**
     * Berechnet den Punktwert der Reihe
     *
     * @param reihe Index der Reihe
     * @return Punktwert
     */
    private static int berechneWert(int reihe) {
        if (reihe < 0 || reihe >= spielfeld.length) {
            return 5;
        }
        return betrag(reihe - 3);
    }

    /**
     * Berechnet den Betrag
     *
     * Da Math.abs nicht erlaubt ist, muss diese Funktion selbst geschrieben werden... :(
     *
     * @param x Wert
     * @return Betrag des Wertes x
     */
    private static int betrag(int x) {
        if (x >= 0) {
            return x;
        } else {
            return -x;
        }
    }

    /**
     * Zeigt das Ergebnis an
     *
     * @param punkteSpieler1 Punkte des Spieler 1
     * @param punkteSpieler2 Punkte des Spieler 2
     */
    private static void zeigeErgebnis(int punkteSpieler1, int punkteSpieler2) {
        String msg;
        //Vergleiche Punkte
        if (punkteSpieler1 > punkteSpieler2) {
            //Spieler 1 hat gewonnen
            msg = "Spieler 1 hat gewonnen!";
        } else if (punkteSpieler2 > punkteSpieler1) {
            //Spieler 2 hat gewonnen
            msg = "Spieler -1 hat gewonnen!";
        } else {
            //Unentschieden
            msg = "Unentschieden!";
        }
        //Punktzahl anzeigen
        msg += "\nSpieler 1:\t" + punkteSpieler1 + " Punkte\nSpieler -1:\t" + punkteSpieler2 + " Punkte";
        //Nachricht ausgeben
        write(msg);
    }

    /**
     * Spielablauf entsprechend Anfangszug, Folgezug, Bonuszug
     *
     * @param spieler ist 1 (Spielsteine 1 bis 12) oder -1 (Spielsteine -1 bis
     *                -12)
     * @param bonus   gibt an ob Bonusregel aktiviert ist
     */
    private static void spielerZieht(int spieler, boolean bonus) {
        //Nachricht wer an der Reihe ist
        write("Spieler " + spieler + " ist an der Reihe");

        int stein;
        int folgezuege;

        do {
            //Anfangszug
            //Abfrage welcher Stein gezogen werden soll.
            stein = readStein("Anfangszug: Weite 1\n", spieler, false);

            //Stein ist gültig --> Stein wird gezogen
            folgezuege = setzeZug(stein, 1, true);

            //prüfung ob Zug ungültig
            if (folgezuege < 0) {
                write("Bitte einen gültigen Zug auswählen!");
            }
            //Wiederhole bis zug gültig (also folgezuege >= 0)
        } while (folgezuege < 0);

        //Feld ausgeben
        renderSpielfeld();

        //Folgezug
        if (folgezuege > 0 && folgezuege < 6) {
            //Nachricht Folgezug
            write("Du hast bekommst einen Folgezug der Weite: " + folgezuege);
            //Stein abfragen
            stein = readStein("Folgezug: Weite " + folgezuege + "\n", spieler, false);
            //stein bewegen
            folgezuege = setzeZug(stein, folgezuege, true);

            //Feld ausgeben
            renderSpielfeld();
        }

        //Bonuszug
        if (folgezuege == 6) {
            //Nachricht Bonuszug
            write("Du bekommst einen Bonuszug!");

            do {
                //Stein abfragen
                stein = readStein("Bonuszug: Weite 1\n", spieler, bonus);
                //Richtung abfragen
                boolean vorwaerts = readVorwaerts();
                //stein bewegen
                folgezuege = setzeZug(stein, 1, vorwaerts);
                //prüfung ob Zug ungültig
                if (folgezuege < 0) {
                    write("Bitte einen gültigen Zug auswählen!");
                }
                //Wiederhole bis zug gültig (also folgezuege >= 0)
            }while (folgezuege < 0);

            //Feld ausgeben
            renderSpielfeld();
        }
    }

    /**
     * Liest eine Steinnummer vom Spieler ein
     *
     * @param msg     Nachricht beim Einlesen
     * @param spieler Spieler
     * @param bonus   Bonus-Regel
     * @return steinnummer
     */
    private static int readStein(String msg, int spieler, boolean bonus) {
        int stein;
        boolean steinGueltig;
        //Generiere Nachricht
        String nachricht = msg + "Bitte wähle einen Stein (" + spieler + " bis " + spieler * 12 + ")";
        if (bonus) {
            nachricht += "(" + spieler * -1 + " bis " + spieler * -12 + ")\n" +
                    "Du kannst auch einen Stein des Gegners bewegen.";
        }
        do {
            //Lese Steinnummer ein
            stein = read(nachricht);
            steinGueltig = true;

            //Prüfen ob Stein zulässig ist
            if (bonus) {
                //Wenn Bonus aktiv, dann sind auch Steine des Gegners gültig
                if(stein == 0 || stein < -12 || stein > 12) {
                    steinGueltig = false;
                    write("Bitte einen gültigen Stein auswählen!");
                    continue;
                }
            } else {
                if (!gueltigeEingabe(stein, spieler)) {
                    steinGueltig = false;
                    write("Bitte einen gültigen Stein auswählen!");
                    continue;
                }
            }

            //Prüfung ob Stein eine gültige Position hat
            //Wenn er keine Position hat, ist er bereits im Ziel und kann nicht mehr bewegt werden
            int steinPosition[] = findeStein(stein);
            if (steinPosition[0] < 0 || steinPosition[1] < 0) {
                steinGueltig = false;
                write("Bitte einen gültigen Stein auswählen!");
            }

            //Wiederhole solange bis Stein gültig
        } while (!steinGueltig);
        return stein;
    }

    /**
     * Fragt den User ob er seinen Zug nach vorne machen will
     *
     * @return vorwaerts
     */
    private static boolean readVorwaerts() {
        boolean gueltig;
        boolean vorwaerts = true;
        do {
            //Lese String ein
            String s = readString("Gib bitte die Richtung deines Zuges an\n 'vorwaerts' für vorwärts\n'rueckwaerts' für rückwärts");
            //Prüfe String
            if (gleicheStrings(s, "vorwaerts") || gleicheStrings(s, "vorwärts")) {
                vorwaerts = true;
                gueltig = true;
            } else if (gleicheStrings(s, "rueckwaerts") || gleicheStrings(s, "rückwärts")) {
                vorwaerts = false;
                gueltig = true;
            } else {
                gueltig = false;
                write("Bitte einen gültigen Wert angeben!");
            }
            //Wiederhole solange bis String gültig
        } while (!gueltig);
        return vorwaerts;
    }

    /**
     * Vergleicht zwei Strings auf Gleichheit
     * Normalerweise würde man anstelle die equals Methode verwenden, allerdings nicht erlaubt
     *
     * @param s1 String 1
     * @param s2 String 2
     * @return boolean
     */
    private static boolean gleicheStrings(String s1, String s2) {
        //Länge vergleichen
        if (s1.length() != s2.length()) {
            return false;
        }
        //einzelne Zeichen durchlaufen
        for (int i = 0; i < s1.length(); i++) {
            //wenn zeichen an der selben Stelle unterschiedlich sind --> false
            if (s1.charAt(i) != s2.charAt(i)) {
                return false;
            }
        }
        //Wurde bisher nicht falsch zurückgegeben, sind die Strings gleich
        return true;
    }

    /**
     * Wählt zufällig einen der Spieler
     *
     * Da Random.nextInt() und MiniJava.drawCard(x,y) nicht erlaubt ist, muss diese Funktion selbst geschrieben werden... :(
     *
     * @return 1 oder -1
     */
    private static int waehleZufaelligenSpieler() {
        int randInt;
        do {
            //Ziehe einen zufällige Zahl (intervall: [2,11]
            //Sieben werden abgezogen um Zahlen zu zentrieren
            randInt = drawCard() - 7;

            //Wiederhole solange bis eindeutiger Wert gefunden ist
        } while (randInt == 0);

        //Gib +1 oder -1 aus
        if (randInt < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Fragt die Spieler nach der Bonus Regel
     *
     * @return boolean
     */
    private static boolean frageBonus() {
        boolean gueltig;
        boolean bonus = false;
        do {
            //Lese String ein
            String s = readString("Bonus-Regel?\nWillst du mit Bonus-Regel spielen? \n 'ja' für Ja\n'nein' für Nein");
            //Prüfe String
            if (gleicheStrings(s, "Ja") || gleicheStrings(s, "ja")) {
                bonus = true;
                gueltig = true;
            } else if (gleicheStrings(s, "Nein") || gleicheStrings(s, "nein")) {
                bonus = false;
                gueltig = true;
            } else {
                gueltig = false;
                write("Bitte einen gültigen Wert angeben!");
            }
            //Wiederhole solange bis String gültig
        } while (!gueltig);
        return bonus;
    }

    /**
     *  Renders the Spielfeld
     *  either only in console or also in a window
     */
    private static void renderSpielfeld() {
        boolean RENDER_SPIELFELD_ONLY_IN_CONSOLE = false;

        String output = output();
        System.out.println(output);
        if(!RENDER_SPIELFELD_ONLY_IN_CONSOLE) {
            write(output);
        }
    }

    public static void main(String args[]) {
        boolean bonus = frageBonus();
        //Initialisiere Spiel
        initSpiel();
        //Gib Ausgangs-Spielfeld aus
        renderSpielfeld();

        //Wähle Startspieler
        int aktuellerSpieler = waehleZufaelligenSpieler();

        boolean spielBeendet;

        //Wiederhole solange bis Spiel beendet ist;
        do {
            //Spieler zieht
            spielerZieht(aktuellerSpieler, bonus);

            //Überprüfe Spiel beendet
            spielBeendet = spielende();

            if (!spielBeendet) {
                //Wechsle wer an der Reihe ist
                aktuellerSpieler *= -1;
            }
        } while (!spielBeendet);

        //Spiel beendet --> zählePunkte und zeige Ergebnis
        zaehlePunkte();
    }
}
