package Map;

public class IntToString implements Fun<Integer, String> {
    public IntToString() {
    }

    @Override
    public String apply(Integer x) {
        return Integer.toString(x, 10);
    }
}
