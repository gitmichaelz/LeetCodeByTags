package linkedlist;

public class ListNode {
    int val;
    ListNode next;
    ListNode(){};
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

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
