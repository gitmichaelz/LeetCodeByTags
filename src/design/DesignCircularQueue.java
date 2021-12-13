package design;

/**
 * Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".
 *
 * One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.
 *
 * Implementation the MyCircularQueue class:
 *
 *     MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 *     int Front() Gets the front item from the queue. If the queue is empty, return -1.
 *     int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 *     boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 *     boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 *     boolean isEmpty() Checks whether the circular queue is empty or not.
 *     boolean isFull() Checks whether the circular queue is full or not.
 *
 * You must solve the problem without using the built-in queue data structure in your programming language.
 */
public class DesignCircularQueue {
    class MyCircularQueue {

        int capacity;
        int headIdx;
        int[] queue;
        int count;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            this.capacity = k;
            this.queue = new int[k];
            this.headIdx = 0;
            this.count = 0;
        }

        /**
         * Insert an element into the circular queue. Return true if the operation is
         * successful.
         */
        public boolean enQueue(int value) {

            if (count < capacity) {
                queue[(headIdx + count) % capacity] = value;
                count++;
                return true;
            }

            return false;
        }

        /**
         * Delete an element from the circular queue. Return true if the operation is
         * successful.
         */
        public boolean deQueue() {
            if (count > 0) {
                headIdx = (headIdx + 1) % capacity;
                count--;
                return true;
            }
            return false;
        }

        /** Get the front item from the queue. */
        public int Front() {
            if (count == 0) {
                return -1;
            }
            return queue[headIdx];
        }

        /** Get the last item from the queue. */
        public int Rear() {

            if (count == 0) {
                return -1;
            }
            int tailIdx = (headIdx + count - 1) % capacity;
            return queue[tailIdx];
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return count == 0;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return count == capacity;
        }
    }
}
