package MatrixMultOptimization;

/**
 * Created by BerniWittmann on 01.12.16.
 */
public class MatrixMultOptimization {

    public static void main(String[] args) {
        int[][] mm = {{10, 30}, {30, 5}, {5, 60}};

        System.out.println(f(mm)); //Sollte 4500 ergeben
    }


    public static int f(int[][] mm) {
        return f(mm, 0, mm.length - 1);
    }

    public static int f(int[][] mm, int i, int j) {
        //Gib 0 Zurück wenn i == j
        if (i == j) return 0;

        //Finde kleinsten Wert. Daher setzen wir am Anfang minimum möglichst hoch
        int minimum = Integer.MAX_VALUE;

        // X Setzen
        for (int x = i; x < j; x++) {
            //Berechne Anzahl per Formel (rekursiv)
            int anzahl = f(mm, i, x) + f(mm, x + 1, j) + (mm[i][0] * mm[x][1] * mm[j][1]);

            //Wenn Anzahl jetzt kleiner ist, dann setzen wir das als neues Minimum
            if (anzahl < minimum) {
                minimum = anzahl;
            }
        }

        // Return minimale Anzahl
        return minimum;
    }
}
