package RockPaperScissors;

import java.util.Random;

public class Player implements Runnable {
    private int wert;
    private String name;
    private boolean verfuegbar;


    public Player(String name) {
        this.name = name;
        this.verfuegbar = false;
    }

    public synchronized int getChoice() throws InterruptedException {
        int i = 0;
        while (!verfuegbar) {
            i++;
        }
        verfuegbar = false;
        return wert;
    }

    @Override
    public void run() {
        Random randomGenerator = new Random();
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            wert = randomGenerator.nextInt(3);
            verfuegbar = true;
            while (verfuegbar && !Thread.currentThread().isInterrupted()) {
                i++;
            }
        }
    }
}
