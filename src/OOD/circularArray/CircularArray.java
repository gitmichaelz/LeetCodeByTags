package OOD.circularArray;

import java.util.Iterator;

public class CircularArray<T> implements Iterable<T> {
    private T[] items;
    private int head = 0;

    public CircularArray(int size) {
        items = (T[])new Object[size];//注意这个写法
    }
    //given a idx, convert it to actually pos in array
    //for example, given idx = 3, cur head is 2, so actual idx is (2 + 3) % arr.length
    private int convert(int idx) {
        if(idx < 0) {
            idx += items.length;
        }
        return (head + idx) % items.length;
    }

    public void rotate(int shiftRight) {
        head = convert(shiftRight);
    }

    public T get(int i) {
        if(i < 0 || i >= items.length) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds");
        }
        return items[convert(i)];
    }

    public void set(int i , T item) {
        items[convert(i)] = item;
    }

    public Iterator<T> iterator() {
        return new CircularArrayIterator();
    }

    private class CircularArrayIterator<T> implements Iterator<T> {
        private int current = -1;

        public CircularArrayIterator(){}

        @Override
        public boolean hasNext() {
            return current < items.length - 1;
        }

        @Override
        public T next() {
            current++;
            return (T) items[convert(current)];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove is not supported in Circular Array.");
        }
    }
}
