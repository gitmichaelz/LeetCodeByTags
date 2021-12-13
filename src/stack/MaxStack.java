package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.
 *
 * Implement the MaxStack class:
 *
 *     MaxStack() Initializes the stack object.
 *     void push(int x) Pushes element x onto the stack.
 *     int pop() Removes the element on top of the stack and returns it.
 *     int top() Gets the element on the top of the stack without removing it.
 *     int peekMax() Retrieves the maximum element in the stack without removing it.
 *     int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.
 */
public class MaxStack {
    private Deque<Integer> max;
    private Deque<Integer> stack;

    /** initialize your data structure here. */
    public MaxStack() {
        max = new ArrayDeque<>();
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (max.isEmpty() || x >= max.peek()) {
            max.push(x);
        }
        stack.push(x);
    }

    public int pop() {
        int x = stack.pop();
        if (x == max.peek()) {
            max.pop();
        }
        return x;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max.peek();
    }

    public int popMax() {
        int maxNum = max.peek();
        Deque<Integer> temp = new ArrayDeque<>();
        while (stack.peek() != maxNum) {
            temp.push(stack.pop());
        }
        stack.pop();
        max.pop();
        while (!temp.isEmpty()) {
            push(temp.pop());
        }
        return maxNum;
    }

    /*
         Node head;
    Node tail;
    TreeMap<Integer, List<Node>> map;

    public MaxStack() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.pre = head;
        map = new TreeMap<>();
    }

    public void push(int x) {
        Node newNode = new Node(x);
        newNode.pre = tail.pre;
        newNode.next = tail;
        tail.pre.next = newNode;
        tail.pre = newNode;
        if(!map.containsKey(x))    map.put(x, new ArrayList<Node>());
        map.get(x).add(newNode);
    }

    public int pop() {
        int value = tail.pre.val;
        removeNode(tail.pre);
        int listSize = map.get(value).size();
        map.get(value).remove(listSize - 1);
        if(listSize == 1)    map.remove(value);
        return value;
    }

    public int top() {
        return tail.pre.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int maxVal = map.lastKey();
        int listSize = map.get(maxVal).size();
        Node node = map.get(maxVal).remove(listSize - 1);
        removeNode(node);
        if(listSize == 1)    map.remove(maxVal);
        return maxVal;
    }

    private void removeNode(Node n){
        Node preNode = n.pre;
        Node nextNode = n.next;
        preNode.next = nextNode;
        nextNode.pre = preNode;
    }

    class Node{
        Node pre;
        Node next;
        int val;
        public Node(int x){
            this.val = x;
            this.pre = null;
            this.next = null;
        }
    }
     */
}
