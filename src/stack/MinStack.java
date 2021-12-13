package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * Implement the MinStack class:
 *
 *     MinStack() initializes the stack object.
 *     void push(int val) pushes the element val onto the stack.
 *     void pop() removes the element on the top of the stack.
 *     int top() gets the top element of the stack.
 *     int getMin() retrieves the minimum element in the stack.
 * Example 1:
 *
 * Input
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * Output
 * [null,null,null,null,-3,null,0,-2]
 */
public class MinStack {
    Deque<Integer> stack1, stack2;//stack2存储当前最小值，如果新来的元素比当前最小值小或者相等，则放入stack2中
    public MinStack(){
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void push(int x){
        stack1.push(x);
        if(stack2.isEmpty() || x <= stack2.peek()) stack2.push(x);//注意这里是x <= getMin();这里有个小trick, 因为stack里存的是Integer,如果int 和Integer比较，256以内比较没问题，超过就出问题
    }

    public void pop(){
        if(stack1.peek() == getMin()) stack2.pop();
        stack1.pop();
    }

    public int top(){
        return stack1.peek();
    }

    public int getMin(){
        return stack2.peek();
    }


    //approach 2: use 1 stack
//    int min = Integer.MAX_VALUE;
//    Deque<Integer> stack = new ArrayDeque<>();
//    public void push(int x) {
//        // only push the old minimum value when the current
//        // minimum value changes after pushing the new value x
//        if(x <= min){
//            stack.push(min);
//            min=x;
//        }
//        stack.push(x);
//    }
//
//    public void pop() {
//        // if pop operation could result in the changing of the current minimum value,
//        // pop twice and change the current minimum value to the last minimum value.
//        if(stack.pop() == min) min=stack.pop();
//    }
//
//    public int top() {
//        return stack.peek();
//    }
//
//    public int getMin() {
//        return min;
//    }
}
