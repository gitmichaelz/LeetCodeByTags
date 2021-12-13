package design;

import java.util.Iterator;

/**
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 *
 * Implement the PeekingIterator class:
 *
 *     PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 *     int next() Returns the next element in the array and moves the pointer to the next element.
 *     boolean hasNext() Returns true if there are still elements in the array.
 *     int peek() Returns the next element in the array without moving the pointer.
 *
 * Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 */
//use only one variable "next" to cache the real next element
// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
public class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> it;
    private Integer next;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        it = iterator;
        next = it.hasNext()? it.next() : null;

    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;

    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        if(it.hasNext())
            next = it.next();
        else
            next = null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return (next != null);

    }
}
