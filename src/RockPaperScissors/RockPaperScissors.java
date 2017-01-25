package RockPaperScissors;

public class RockPaperScissors implements Runnable {
    private int siegeP1;
    private int siegeP2;

    public RockPaperScissors() {
        siegeP1 = 0;
        siegeP2 = 0;
    }

    @Override
    public void run() {
        Player p1 = new Player("Spieler 1");
        Player p2 = new Player("Spieler 2");

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);

        t1.start();
        t2.start();

        int r1 = -1;
        int r2 = -1;
        for (int i = 0; i < 1000; i++) {
            try {
                r1 = p1.getChoice();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                r2 = p2.getChoice();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handleErgebnis(r1, r2);
        }
        t1.interrupt();
        t2.interrupt();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("### Ergebnis ###");
        System.out.println("Spieler 1: " + siegeP1);
        System.out.println("Spieler 2: " + siegeP2);
    }

    private void handleErgebnis(int r1, int r2) {
        if (r1 != r2 && r1 >= 0 && r2 >= 0 && r1 < 3 && r2 < 3) {
            if ((r1 == 0 && r2 == 1) || (r1 == 1 && r2 == 2) || (r1 == 2 && r2 == 0)) {
                siegeP2++;
            } else if ((r1 == 0 && r2 == 2) || (r1 == 1 && r2 == 0) || (r1 == 2 && r2 == 1)) {
                siegeP1++;
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new RockPaperScissors());
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
