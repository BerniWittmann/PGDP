package resistenteMengen;

import java.util.Iterator;

public class Set<T> implements Iterable<T>{
    private final List<T> list;

    public Set() {
        this.list = new List<T>();
    }

    public Set(List<T> l) {
        this.list = l;
    }

    public Set add(T e) {
        Set<T> newSet = new Set<>(list.clone());
        newSet.list.add(e);
        return newSet;
    }

    public boolean contains(T e) {
        return list.contains(e);
    }

    public Set remove(T e) {
        Set<T> newSet = new Set<>(list.clone());
        newSet.list.remove(e);
        return newSet;
    }

    public int size() {
        return list.size();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (this.getClass() != o.getClass()) return false;
        Set obj = (Set) o;
        if (this.size() != obj.size()) return false;

        ListElement current = this.list.head;
        do {
            if (!obj.contains(current.getValue())) {
                return false;
            }
            current = current.getNext();
        }while (current != null);
        return true;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        Set<Integer> set0 = new Set<>();
        System.out.println("initial set: " + set0);
        Set<Integer> set1 = set0.add(1);
        Set<Integer> set2 = set1.add(2);
        System.out.println("\n");
        System.out.println("set 0: " + set0 + " Size: " + set0.size());
        System.out.println("set 1: " + set1 + " Size: " + set1.size());
        System.out.println("set 2: " + set2 + " Size: " + set2.size());
        Set<Integer> set3 = set2.remove(1);
        System.out.println("\n");
        System.out.println("set 0: " + set0 + " Size: " + set0.size());
        System.out.println("set 1: " + set1 + " Size: " + set1.size());
        System.out.println("set 2: " + set2 + " Size: " + set2.size());
        System.out.println("set 3: " + set3 + " Size: " + set3.size());
        Set<Integer> set4 = set3.add(1);
        Set<Integer> set5 = set1.add(3);
        System.out.println("\n");
        System.out.println("set 0: " + set0 + " Size: " + set0.size());
        System.out.println("set 1: " + set1 + " Size: " + set1.size());
        System.out.println("set 2: " + set2 + " Size: " + set2.size());
        System.out.println("set 3: " + set3 + " Size: " + set3.size());
        System.out.println("set 4: " + set4 + " Size: " + set4.size());
        System.out.println("set 5: " + set5 + " Size: " + set5.size());
        if (set4.equals(set2)) {
            System.out.println("set 4 is equal to set 2");
        }
        if (!set5.equals(set2)) {
            System.out.println("set 5 is not equal to set 2");
        }
    }
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {

            private ListElement current = list.head;

            @Override
            public boolean hasNext() {
                return current.hasNext();
            }

            @Override
            public T next() {
                return (T) current.getNext().getValue();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
