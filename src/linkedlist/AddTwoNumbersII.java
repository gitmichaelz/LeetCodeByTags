package linkedlist;

import java.util.Stack;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 * Input: l1 = [7,2,4,3], l2 = [5,6,4]
 * Output: [7,8,0,7]
 */
public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1 = new Stack<>();
        Stack<ListNode> stack2 = new Stack<>();
        while(l1 != null){
            stack1.push(l1);
            l1 = l1.next;
        }
        while(l2 != null){
            stack2.push(l2);
            l2 = l2.next;
        }
        ListNode dummy = null;
        int carry = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int a = stack1.isEmpty()? 0 : stack1.pop().val;
            int b = stack2.isEmpty()? 0 : stack2.pop().val;
            int val = carry + a + b;
            ListNode head = new ListNode(val % 10);
            head.next = dummy;
            dummy = head;
            carry = val / 10;
        }
        return dummy;
    }
//     public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//         ListNode dummy = new ListNode(0);
//         l1 = reverse(l1);
//         l2 = reverse(l2);
//         int carry = 0;
//         ListNode cur = dummy;
//         while(l1 != null || l2 != null){
//             int a = l1 == null? 0 : l1.val;
//             int b = l2 == null? 0 : l2.val;
//             int val = a + b + carry;
//             cur.next = new ListNode(val % 10);
//             cur = cur.next;
//             carry = val / 10;
//             if(l1 != null) l1 = l1.next;
//             if(l2 != null) l2 = l2.next;
//         }
//         if(carry != 0) {
//             cur.next = new ListNode(carry);
//         }
//         return reverse(dummy.next);
//     }

//     public static ListNode reverse(ListNode head) {
//         ListNode dummy = new ListNode(0);
//         dummy.next = head;
//         while(head != null && head.next != null){
//             ListNode tmp = dummy.next;
//             dummy.next = head.next;
//             head.next = head.next.next;
//             dummy.next.next = tmp;
//         }
//         return dummy.next;
//     }
}
