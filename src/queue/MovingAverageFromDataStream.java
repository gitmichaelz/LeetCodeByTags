package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 *     MovingAverage(int size) Initializes the object with the size of the window size.
 *     double next(int val) Returns the moving average of the last size values of the stream.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream {
    class MovingAverage{
        Queue<Integer> queue;
        double sum;//注意是double类型
        int size;
        /** Initialize your data structure here. */
        public MovingAverage(int size) {
            queue = new LinkedList<>();
            this.size = size;
            sum = 0;
        }

        public double next(int val) {
            queue.offer(val);
            sum += val;
            if(queue.size() > size){
                sum -= queue.poll();
            }
            return sum/queue.size();
        }
    }


/* Circular array
    class MovingAverage {
        int size, head = 0, windowSum = 0, count = 0;
        int[] queue;
        public MovingAverage(int size) {
            this.size = size;
            queue = new int[size];
        }

        public double next(int val) {
            ++count;
            // calculate the new sum by shifting the window
            int tail = (head + 1) % size;
            windowSum = windowSum - queue[tail] + val;
            // move on to the next head
            head = (head + 1) % size;
            queue[head] = val;
            return windowSum * 1.0 / Math.min(size, count);
        }
    }

 */
}
