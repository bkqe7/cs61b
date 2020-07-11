public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for(int i =0;i<size;i=i+1) {
            int pos = plusOne(i+nextFirst);
            a[i] = items[pos];
        }
        items = a;
        nextFirst = capacity-1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if(size == items.length) {
            resize(size * RFACTOR);
        }
        size = size+1;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);

    }
    private int minusOne(int index) {
        return (index-1+items.length)%items.length;
    }

    public void addLast(T item) {
        if(size == items.length) {
            resize(size * RFACTOR);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);

    }
    private int plusOne(int index) {
        return (index+1)%items.length;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int i = plusOne(nextFirst);
        while(i != nextLast) {
            System.out.println(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println("\n");
    }

    public T removeFirst() {
        if(size ==0) {
            return null;
        }
        int firstIndex = plusOne(nextFirst);
        T firstItem = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        size = size -1;
        return firstItem;
    }

    public T removeLast() {
        if(size==0) {
            return null;
        }
        int lastIndex = minusOne(nextLast);
        T lastItem = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size = size - 1;
        return lastItem;
    }

    public T get(int index) {
        if(index<0 || index >=size) {
            return null;
        }
        int pos = plusOne(index+nextFirst);
        return items[pos];
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[INIT_CAPACITY];
        nextFirst = 4;
        nextLast = 5;
        size =0;
        for(int i=0;i<other.size;i = i+1) {
            addLast((T) other.get(i));
        }
    }




}
