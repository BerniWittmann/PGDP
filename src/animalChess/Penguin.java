package animalChess;

import java.util.Arrays;

public class Penguin {
    private String square = "e3";

    public Move[] possibleMoves() {
        Move[] moves = new Move[8];
        int countMoves = 0;
        String position = this.square;

        //Umschreiben der Position in Integer, z.b. e == 101
        //start Position ist links oben ausgehend von der aktuellen position
        int startPositionX = ((int) position.charAt(0)) - 1;
        int startPositionY =((int) position.charAt(1)) - 1;

        //endposition ist rechts unten ausgehend von der aktuellen position
        int endPositionX = startPositionX + 2;
        int endPositionY = startPositionY + 2;

        //Grenzen der Integer (aus Buchstaben) a == 97, h == 104
        int letterMinInt = (int) 'a';
        int letterMaxInt = (int) 'h';
        int numberMinInt = (int) '1';
        int numberMaxInt = (int) '8';

        //Durchlaufen der Felder
        for (int i = startPositionX; i <= endPositionX; i++) {
            //Prüfung ob position im Feld ist
            if(i >= letterMinInt && i <= letterMaxInt) {
                for (int j = startPositionY; j <= endPositionY; j++) {
                    if(j >= numberMinInt && j <= numberMaxInt) {
                        //Generierung des Positionsstrings
                        String newPosition = "" + ((char) i)  + ((char) j);
                        //Prüfen ob ncht die aktuelle Position
                        if(!newPosition.equals(position)) {
                            moves[countMoves] = new Move(position, newPosition);
                            countMoves++;
                        }
                    }
                }
            }
        }

        //Abschneiden von überflüssigen, leeren Feld-Elementen
        return Arrays.copyOfRange(moves, 0, countMoves);
    }

    public static void main(String[] args) {
        Penguin penguin = new Penguin();
        Move[] moves = penguin.possibleMoves();
        for (Move m : moves) {
            System.out.println(m.toString());
        }

    }
}
