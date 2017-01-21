package resistenteMengen;

public class List<T> {

    ListElement head;

    /**
     * constructor empty List
     */
    public List() {
        head = null;
    }

    /**
     * Appends a new element with value info to the end of this list
     *
     * @param info value of the new element
     */
    public void add(T info) {
        if (head != null) {
            ListElement current = head;
            //Durchlaufen, bis ein Element erreicht wird, das keinen Nächsten hat
            while (current.hasNext()) {
                current = current.getNext();
            }
            current.setNext(new ListElement(info));
        } else {
            head = new ListElement(info);
        }
    }

    /**
     * Checks if value is in list
     *
     * @param o The value to be checked
     * @return wether the value is contained in the list
     */
    public boolean contains(Object o) {
        if (head == null) return false;

        ListElement current = head;

        do {
            if (current.getValue() == o) {
                return true;
            }

            current = current.getNext();
        } while (current != null);
        return false;
    }

    /**
     * returns the size of the list
     *
     * @return amount of elements in the list
     */
    public int size() {
        if (head == null) return 0;

        int result = 0;
        ListElement current = head;

        do {
            result++;

            current = current.getNext();
        } while (current != null);
        return result;
    }

    /**
     * remove a value from the list
     *
     * @param info the value to be removed
     */
    public void remove(T info) {
        if (info == null) throw new NullPointerException();
        if (head == null) return;
        if (!this.contains(info)) return;

        if (head.getValue() == info) {
            if (head.hasNext()) {
                head = head.getNext();
            } else {
                head = null;
            }
        } else {
            ListElement current = head;

            while (current.hasNext()) {
                if (current.getNext().getValue() == info) {
                    ListElement next = current.getNext();
                    if (next.hasNext()) {
                        current.setNext(next.getNext());
                        return;
                    } else {
                        current.setNext(null);
                        return;
                    }
                }
                current = current.getNext();
            }
        }
    }

    @Override
    public String toString() {
        String out = "[";
        if (head != null) {
            out += head.getValue();
            ListElement tmp = head.getNext();
            while (tmp != null) {
                out = out + "," + tmp.getValue();
                tmp = tmp.getNext();
            }
        }
        out += "]";
        return out;
    }

    public List clone() {
        List<T> l = new List<>();
        if (head != null) {
            ListElement current = head;
            l.head = new ListElement(head.getValue());
            if (current.hasNext()) {
                current = current.getNext();
                do {
                    if (current == null) break;
                    T val = (T) current.getValue();
                    l.add(val);
                    current = current.getNext();
                } while (current != null);
            }
        }
        return l;
    }
}

