package password;

public class NotEnoughLetter extends NotEnoughExc{
    private final int should;
    private final int is;

    public NotEnoughLetter(int should, int is) {
        super(should, is);
        this.should = should;
        this.is = is;
    }
}
