public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /** resizes the underlying array to the target capacity */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i = i + 1) {
            int pos = plusOne(i + nextFirst);
            a[i] = items[pos];
        }
        items = a;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** Adds x to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        size = size + 1;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);

    }

    /** index minus one and allows circular */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    /** Adds x to the end of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);

    }

    /** index plus one and allows circular */
    private int plusOne(int index) {
        return (index + 1) % items.length;
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
        int i = plusOne(nextFirst);
        while (i != nextLast) {
            System.out.println(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println("\n");
    }

    /** removes and return the item at the front of the deque */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int firstIndex = plusOne(nextFirst);
        T firstItem = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size = size - 1;
        return firstItem;
    }

    /** removes and return the item at the back of the deque */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int lastIndex = minusOne(nextLast);
        T lastItem = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size = size - 1;
        return lastItem;
    }

    /** gets the item at the given index */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int pos = plusOne(index + nextFirst);
        return items[pos];
    }

    /** creates a deep copy of other */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[INIT_CAPACITY];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
        for (int i = 0; i < other.size; i = i + 1) {
            addLast((T) other.get(i));
        }
    }




}
