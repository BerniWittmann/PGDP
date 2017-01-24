package Map;

import java.util.Arrays;

public class Map {
    public static <T, R> void map(Fun<T, R> f, T[] a, R[] b, int n) throws InterruptedException {
        if (a.length != b.length) throw new IllegalArgumentException();
        if (!(n > 0 && n <= a.length)) throw new IllegalArgumentException();

        Thread[] threads = new Thread[n];

        int currentIndex = 0;
        int currentAmount = 0;
        int k = a.length;
        for (int i = 0; i < n; i++) {
            int length;
            if (currentAmount < k % n) {
                length = k / n + 1;
            } else {
                length = k / n;
            }
            threads[i] = new MapThread<T, R>(f, a, currentIndex, length);
            currentIndex += length;
            currentAmount++;
        }

        for (int i = 0; i < n; i++) {
            threads[i].start();
        }

        for (int i = 0; i < n; i++) {
            threads[i].join();
        }

        for (int i = 0; i < n; i++) {
            MapThread<T, R> mapThread = (MapThread<T, R>) threads[i];
            int start = mapThread.getStartIndex();
            int length = mapThread.getLength();
            R[] res = mapThread.getResult();
            for (int j = start; j < start + length; j++) {
                b[j] = res[j];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5, 6, 7};
        String[] b = new String[7];
        int n = 3;
        Fun<Integer, String> f = new IntToString();

        try {
            map(f, a, b, n);
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println(Arrays.toString(b));
    }
}
