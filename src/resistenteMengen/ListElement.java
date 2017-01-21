package resistenteMengen;

public class ListElement<T> {
    private final T value;
    private ListElement<T> next;

    public ListElement(T value, ListElement<T> next){
        this.value = value;
        this.next = next;
    }

    public ListElement(T value ) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public ListElement<T> getNext() {
        return next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void setNext(ListElement l) {
        this.next = l;
    }

}
