public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /**Adds x to the front of the deque.*/
    public void addFirst(T item) {
        size += 1;
        Node temp = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = temp;
        sentinel.next = temp;

    }

    /** Adds x to the end of the deque. */
    public void addLast(T item) {
        size += 1;
        Node temp = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = temp;
        sentinel.prev = temp;

    }

    /** if the deque is empty */
    public boolean isEmpty() {
        return size == 0;
    }


    /** return number of items in the deque */
    public int size() {
        return size;
    }

    /** print all the items in the deque */
    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.println(p.next.item + " ");
            p = p.next;
        }
        System.out.println("\n");
    }

    /** removes and return the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size = size - 1;
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        return first.item;
    }

    /** removes and return the item at the back of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size = size - 1;
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        return last.item;
    }

    /** gets the item at the given index */
    public T get(int index) {
        if (index >= 0 && index < size) {
            int count = 0;
            Node p = sentinel;
            while (count < index + 1) {
                p = p.next;
                count = count + 1;
            }
            return p.item;
        }
        return null;

    }

    /** gets the item at the given index recursively */
    public T getRecursive(int index) {
        if (index >= 0 && index < size) {
            return getRecursiveHelper(sentinel, index);

        }
        return null;


    }

    private T getRecursiveHelper(Node cur, int i) {
        if (i == 0) {
            return cur.next.item;
        }
        return getRecursiveHelper(cur.next, i - 1);
    }

    /** creates a deep copy of other */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }

    }


}
