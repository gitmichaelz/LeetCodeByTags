package design;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 *     ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 *     boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 *     int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 *
 *
 * Example 1:
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 */
public class ZigzagIterator {
    Deque<Iterator<Integer>> queue;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2){
        queue = new LinkedList<>();
        if(!v1.isEmpty()) queue.offer(v1.iterator());
        if(!v2.isEmpty()) queue.offer(v2.iterator());
    }

    public int next(){
        Iterator cur = queue.poll();
        int next = (int)cur.next();//不要忘记加（int）
        if(cur.hasNext()){
            queue.offer(cur);
        }
        return next;
    }

    public boolean hasNext(){
        return !queue.isEmpty();
    }

//     Iterator<Integer>[] iterators;
//     int pos;
//     public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
//         iterators = new Iterator[]{v1.iterator(), v2.iterator()};
//         pos = 0;
//     }

//     public int next() {
//         int next = iterators[pos++].next();
//         pos %= iterators.length;
//         return next;
//     }

//     public boolean hasNext() {
//         for(int i = 0; i < iterators.length; i++){
//             if(iterators[pos].hasNext()){
//                 return true;
//             }
//             pos = ++pos % iterators.length;
//         }
//         return false;
//     }
}
