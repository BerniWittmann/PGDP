package Map;

import java.util.Arrays;

public class MapThread<T, R> extends Thread {
    private Fun fun;
    private T[] arr;
    private int startIndex;
    private int length;
    private R[] result;

    public MapThread(Fun fun, T[] arr, int startIndex, int length) {
        this.fun = fun;
        this.arr = arr;
        this.startIndex = startIndex;
        this.length = length;
        this.result = (R[]) new Object[arr.length];
    }

    @Override
    public void run() {
        for (int i = startIndex; i < startIndex + length; i++) {
            System.out.println("Iteration Thread " + startIndex + ": currently handled element: " + arr[i]);
            result[i] = (R) fun.apply(arr[i]);
        }
    }

    public R[] getResult() {
        return result;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getLength() {
        return length;
    }
}
