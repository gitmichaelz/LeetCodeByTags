package queue;

import java.util.Stack;

/**
 * Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).
 *
 * Implement the MyQueue class:
 *
 *     void push(int x) Pushes element x to the back of the queue.
 *     int pop() Removes the element from the front of the queue and returns it.
 *     int peek() Returns the element at the front of the queue.
 *     boolean empty() Returns true if the queue is empty, false otherwise.
 *
 * Notes:
 *
 *     You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
 *     Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * Output
 * [null, null, null, 1, 1, false]
 *
 * Explanation
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 */
public class ImplementQueueUsingStacks {
    class MyQueue {
        //idea: push(): we put the elment into in stack
        //
        //      pop(): check out stack, if empty, pour in stack to out stack
        Stack<Integer> in = new Stack();
        Stack<Integer> out = new Stack();

        // Push element x to the back of queue.
        public void push(int x) {
            in.push(x);
        }

        // Removes the element from in front of queue.
        public int pop() {
            peek();
            return out.pop();
        }

        // Get the front element.
        public int peek() {
            if(out.isEmpty()){
                while(!in.isEmpty()){
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        // Return whether the queue is empty.
        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }

    }
}
