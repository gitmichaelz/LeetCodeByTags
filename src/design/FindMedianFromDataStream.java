package design;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.
 *
 *     For example, for arr = [2,3,4], the median is 3.
 *     For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * Implement the MedianFinder class:
 *
 *     MedianFinder() initializes the MedianFinder object.
 *     void addNum(int num) adds the integer num from the data stream to the data structure.
 *     double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 */
public class FindMedianFromDataStream {
    //follow up
    //If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
    //If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?

    class MedianFinder {
    /*follow up 1>
    int A[] = new int[101], n = 0;

        public void addNum(int num) {
            A[num]++;
            n++;
        }

        public double findMedian() {
            int count = 0, i = 0;
            while (count < n/2) count += A[i++];//找出第(n/2)个数，i - 1 (因为最后跳出循环的时候i++了一次)
            int j = i;
            while (count < n/2+1) count += A[j++];//找出第(n/2 + 1)个数， 同理，是j - 1
            return (n%2 == 1) ? j-1 : (i+j-2) / 2.0;//为什么要j - 1, 以及(i + j - 2)呢？因为跳出循环的时候i, j都++了一次。
        }

    follow up 2>, use need an integer array of length 101 and a treemap to store numbers out of range and their numbers.
    或者，用两个变量，一个less_than_0, 一个greater_than_100来计算超过范围的count,但median不会落在他们之中，只会在【1， 100】之间
    */

        //use two heap, minHeap and maxHeap
        //minHeap has the larger half of the numbers
        //maxHeap has the smaller half of the numbers
        //this givens us the one or two middle values(the top of the heaps), so getting the median takes O(1) time and adding a number takes O(logN) time

        /** initialize your data structure here. */
        Queue<Integer> minHeap, maxHeap;
        public MedianFinder() {
            minHeap = new PriorityQueue<>();//defaulting to minHeap
            maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        }
        //每次添加一个数到maxHeap, 都从起里面拿一个最大的数放到minHeap里
        public void addNum(int num) {
            maxHeap.offer(num);//maxHeap里存的是小的一半
            minHeap.offer(maxHeap.poll());//每次添加一个数到maxHeap, 都从起里面拿一个最大的数放到minHeap里
            if(maxHeap.size() < minHeap.size()){//我们让maxHeap的size永远大于等于minHeap size
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            return minHeap.size() == maxHeap.size()? (minHeap.peek() + maxHeap.peek())/2.0 : maxHeap.peek();
        }
    }
}
