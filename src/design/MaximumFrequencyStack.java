package design;

import java.util.*;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 *     FreqStack() constructs an empty frequency stack.
 *     void push(int val) pushes an integer val onto the top of the stack.
 *     int pop() removes and returns the most frequent element in the stack.
 *         If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["FreqStack", "push", "push", "push", "push", "push", "push", "pop", "pop", "pop", "pop"]
 * [[], [5], [7], [5], [7], [4], [5], [], [], [], []]
 * Output
 * [null, null, null, null, null, null, null, 5, 7, 5, 4]
 */
public class MaximumFrequencyStack {
    class FreqStack {
        /* use a stackList with stackList[i] store elements that appears i + 1 times
         * use a map to update the frequency of each element
         * in the case of
         * ["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
         * [[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
         * ========================================
         * push 5
         * stackList: stackList[0]: 5
         * ========================================
         * push 7
         * stackList: stackList[0]: 5  7
         * ========================================
         * push 5
         * stackList: stackList[0]: 5  7
         *            stackList[1]: 5
         * ========================================
         * push 7
         * stackList: stackList[0]: 5  7
         *            stackList[1]: 5  7
         * ========================================
         * push 4
         * stackList: stackList[0]: 5  7  4
         *            stackList[1]: 5  7
         * ========================================
         * push 5
         * stackList: stackList[0]: 5  7  4
         *            stackList[1]: 5  7
         *            stackList[2]: 5
         * ========================================
         * pop (will pop the top element of stackList[2], which is 5)
         * stackList: stackList[0]: 5  7  4
         *            stackList[1]: 5  7
         * ========================================
         * pop (will pop the top element of stackList[1], which is 7)
         * stackList: stackList[0]: 5  7  4
         *            stackList[1]: 5
         * ========================================
         * pop (will pop the top element of stackList[1], which is 5)
         * stackList: stackList[0]: 5  7  4
         * ========================================
         * pop (will pop the top element of stackList[0], which is 4)
         * stackList: stackList[0]: 5  7
         */

        List<Deque<Integer>> buckets;//buckets[i]: stores elements with freq i + 1
        Map<Integer, Integer> map;//element and count

        public FreqStack() {
            buckets = new ArrayList<>();
            map = new HashMap<>();
        }

        public void push(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            int freq = map.get(val);
            if(freq > buckets.size()) {
                buckets.add(new LinkedList<>());
            }
            buckets.get(freq - 1).push(val);
        }

        public int pop() {
            int res = buckets.get(buckets.size() - 1).pop();
            map.put(res, map.get(res) - 1);
            if(buckets.get(buckets.size() - 1).isEmpty()) {
                buckets.remove(buckets.size() - 1);
            }
            return res;
        }
    }
}
