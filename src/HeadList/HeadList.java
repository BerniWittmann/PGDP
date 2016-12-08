
public class HeadList {

    Entry head;

    /**
     * constructor empty HeadList
     */
    public HeadList() {
        head = null;
    }

    /**
     * Appends a new element with value info to the end of this list
     *
     * @param info value of the new element
     */
    public void add(int info) {
        if (head != null) {
            Entry current = head;
            //Durchlaufen, bis ein Element erreicht wird, das keinen Nächsten hat
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Entry(head, null, info);
        } else {
            head = new Entry(null, null, info);
            head.first = head;
        }
    }

    /**
     * Removes and returns the element at position index from this list.
     *
     * @param index position of the element that is removed
     * @return value of the removed element
     */
    public int remove(int index) {
        if (head != null && index >= 0) {
            Entry current = head;
            if (index == 0) {
                //Der Header wird entfernt
                int val = head.elem;
                if(head.next != null) {
                    head = head.next;
                    setHead(head);
                }else {
                    head = null;
                }
                return val;
            }
            for (int i = 1; i < index; i++) {
                if(current.next == null) {
                    return Integer.MIN_VALUE;
                }
                current = current.next;
            }
            if(current.next == null) {
                return Integer.MIN_VALUE;
            }
            int val = current.next.elem;
            current.next = current.next.next;
            return val;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * sets the head of each list element to newHead
     *
     * @param newHead reference to the new head
     */
    private void setHead(Entry newHead) {
        Entry current = head;
        current.first = newHead;
        while (current.next != null) {
            current = current.next;
            current.first = newHead;
        }
    }

    /**
     * reverse the list
     * example: [1,2,3,4,5] --> [5,4,3,2,1], [] --> [], [1] --> [1]
     */
    public void reverse() {
        if(head != null || head.next != null) {
            Entry current = head;
            int length = 0;
            while(current.next != null) {
                current = current.next;
                length++;
            }
            Entry newHead = current;
            newHead.first = current;
            for (int i = length - 1; i >= 0; i--) {
                current.next = new Entry(newHead, null, remove(i));
                current = current.next;
            }
            head = newHead;
        }
    }

    @Override
    public String toString() {
        String out = "[";
        if (head != null) {
            out += head.elem;
            Entry tmp = head.next;
            while (tmp != null) {
                out = out + "," + tmp.elem;
                tmp = tmp.next;
            }
        }
        out += "]";
        return out;
    }

    public static void main(String[] args) {
        HeadList l = new HeadList();
        System.out.println("empty list: " + l);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        System.out.println("list: " + l);
        System.out.println(l.remove(0)); //Sollte 1 ergeben
        System.out.println("list: " + l);
        System.out.println(l.remove(1)); //Sollte 3 ergeben
        System.out.println(l.remove(2)); //Sollte 5 ergeben
        System.out.println("list: " + l);
        l.remove(0);
        l.remove(0);
        System.out.println("list: " + l);
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);
        System.out.println("list: " + l);
        l.reverse();
        System.out.println("list: " + l);
    }

    class Entry {

        Entry first;
        Entry next;
        int elem;

        public Entry(Entry first, Entry next, int elem) {
            this.first = first;
            this.next = next;
            this.elem = elem;
        }

    }

}
 
