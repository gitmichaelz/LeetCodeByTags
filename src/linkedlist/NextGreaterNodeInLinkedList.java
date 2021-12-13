package linkedlist;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * ou are given the head of a linked list with n nodes.
 *
 * For each node in the list, find the value of the next greater node. That is, for each node, find the value of the first node that is next to it and has a strictly larger value than it.
 *
 * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed). If the ith node does not have a next greater node, set answer[i] = 0.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [2,1,5]
 * Output: [5,5,0]
 */
public class NextGreaterNodeInLinkedList {
    //use monotonic stack, idea from https://www.youtube.com/watch?v=e4BtnOVy0Gs
    //use stack, scan list from right to left; pop out any element less than the cur element, then the next greater one is stack.peek()
    //then push cur element into stack
    public int[] nextLargerNodes(ListNode head) {
        List<Integer> list = new ArrayList<>();
        for(ListNode cur = head; cur != null; cur = cur.next){
            list.add(cur.val);
        }
        int[] res = new int[list.size()];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = list.size() - 1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= list.get(i)){
                stack.pop();
            }
            res[i] = stack.isEmpty()? 0 : stack.peek();
            stack.push(list.get(i));
        }
        return res;
    }
}
