package linkedlist;

public class AddTwoNumbers {
/**
 You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order,
 and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Input: l1 = [2,4,3], l2 = [5,6,4]
 Output: [7,0,8]
 Explanation: 342 + 465 = 807.
 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while(l1 != null || l2 != null || carry != 0) {
            int x = l1 == null? 0 : l1.val;
            int y = l2 == null? 0 : l2.val;
            int val = x + y + carry;
            carry = val / 10;
            node.next = new ListNode(val % 10);
            node = node.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }

}
