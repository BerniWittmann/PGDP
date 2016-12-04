package Toolbox;

/**
 * Created by Berni on 04.12.16.
 */
public class Toolbox {
    public static void main(String[] args) {
        System.out.println(evenSum(6));
        System.out.println(evenSum(-8));
        System.out.println(multiplication(5, 4));
        System.out.println(multiplication(-8, 2));
        System.out.println(multiplication(2, -8));
        System.out.println(multiplication(-8, -2));
        int[] arr = {4, 7, 42, 5, 1, -5, 0, -4, -3};
        reverse(arr);
        System.out.println(numberOfOddIntegers(arr));
        int[] arr_filtered = filterOdd(arr);
        printArray(arr_filtered);
    }

    /**
     * Gibt zurück ob eine Zahl x gerade oder ungerade ist
     * <p>
     * Da Module nicht verwendet werden darf :(
     *
     * @param x
     * @return boolean
     */
    public static boolean istGerade(int x) {
        if (x <= 1 && x >= -1) {
            return false;
        }
        int i;
        if (x > 0) {
            i = 2;
            while (x > i) {
                i += 2;
                if (i > 10000) {
                    System.out.println("Zahl nicht zwischen -10^4 und 10^4");
                    return false;
                }
            }
        } else {
            i = -2;
            while (x < i) {
                i -= 2;
                if (i < -10000) {
                    System.out.println("Zahl nicht zwischen -10^4 und 10^4");
                    return false;
                }
            }
        }
        return x == i;
    }

    public static int evenSum(int n) {
        //Summe wird mit 0 initialisiert
        int summe = 0;

        //Rekursionsanker
        if (n == 0) {
            return 0;
        }

        if (istGerade(n)) {
            //n ist gerade
            //Addiere n zur Summe
            summe += n;
        }

        //Rekursives Fortschreiben von evenSum
        if (n < 0) {
            //Bei negativen Zahlen n hochzählen
            summe += evenSum(n + 1);
        } else {
            //Bei positiven Zahlen n runterzählen
            summe += evenSum(n - 1);
        }

        //Summe zurückgeben
        return summe;
    }

    public static int multiplication(int x, int y) {
        //Rekursionsanker
        if (x == 0) return 0;

        //Ergebnis initialisieren
        int ergebnis = 0;

        //Prüfung x größer/kleiner 0
        if (x > 0) {
            //Einmal y zum ergebnis addieren
            ergebnis += y;
            //Dafür x einmal dekrementieren
            x--;
        } else {
            //Einmal y vom Ergebnis abziehen (wegen negativen)
            ergebnis -= y;
            //Dafür x inkrementieren
            x++;
        }

        //Rekursiv restliche Schritte hinzufügen
        ergebnis += multiplication(x, y);

        return ergebnis;
    }

    public static void reverse(int[] m) {
        //Strategie: Nimm erstes und letztes Element und vertausche diese
        //dann immer weiter nach innen gehen, bis die Mitte erreicht ist

        if (m.length > 1) {
            //Rufe Hilfsmethode auf
            m = reverse_rec(m, 0);
        }

        //Ausgeben des Arrays (Auch wieder rekursiv...)
        printArray(m);
        System.out.println();
    }

    /**
     * Rekursive Hilfsmethode für reverse-Methode
     *
     * @param m     int Array
     * @param start Start Index
     * @return Array
     */
    public static int[] reverse_rec(int[] m, int start) {
        //Finde mittleren Index
        int mittlererIndex = m.length / 2;

        ////Rekursionanker
        if (start > mittlererIndex) {
            return m;
        }

        //Vertausche ersten und letzten, bzw. zweiten und vorletzten
        int temp = m[start];
        m[start] = m[m.length - start - 1];
        m[m.length - start - 1] = temp;

        //Rekursives Aufrufen
        m = reverse_rec(m, start + 1);

        return m;
    }

    public static int numberOfOddIntegers(int[] m) {
        return numberOfOddIntegers(m, 0);
    }

    /**
     * Rekursive methode zum Zählen der ungeraden
     *
     * @param m     int Array
     * @param start Start Index
     * @return Array
     */
    public static int numberOfOddIntegers(int[] m, int start) {
        //Initialisiere Ergebnis
        int ergebnis = 0;

        //Rekursionsanker
        if (start >= m.length) {
            return 0;
        }

        //Wenn Zahl ungerade Ergebnis hochzählen
        if (!istGerade(m[start]) && m[start] != 0) {
            ergebnis++;
        }

        //Rekursiv weitere Zahlen zählen
        ergebnis += numberOfOddIntegers(m, start + 1);

        return ergebnis;
    }

    public static int[] filterOdd(int[] m) {
        //Initialisiere Ergebnis
        int[] ergebnis = new int[numberOfOddIntegers(m)];

        return filterOdd(m, 0, ergebnis, 0);
    }

    /**
     * Rekursive methode zum Filtern der ungeraden
     *
     * @param m             int Array
     * @param start         Start Index
     * @param ergebnis      Ergebnis Array
     * @param ergebnisindex Index des Ergebnisses
     * @return Array
     */
    public static int[] filterOdd(int[] m, int start, int[] ergebnis, int ergebnisindex) {
        //Rekursionsanker
        if (start >= m.length) {
            return ergebnis;
        }
        //Wenn Zahl ungerade kopiere Zahl nach Ergebnis
        if (!istGerade(m[start]) && m[start] != 0) {
            ergebnis[ergebnisindex] = m[start];
            ergebnisindex++;
        }

        //Rekursiv weitere Zahlen filtern
        ergebnis = filterOdd(m, start + 1, ergebnis, ergebnisindex);

        return ergebnis;
    }

    public static void printArray(int[] m) {
        printArray(m, 0);
    }

    /**
     * Rekursive Hilfsmethode zum Ausgeben des Arrays
     *
     * @param m     int Array
     * @param start Start Index
     * @return Array
     */
    public static void printArray(int[] m, int start) {
        if (start < m.length && start >= 0) {
            System.out.print(m[start] + ", ");
            printArray(m, start + 1);
        }
    }
}
