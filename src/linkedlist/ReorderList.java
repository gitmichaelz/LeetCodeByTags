package linkedlist;

/**
 * You are given the head of a singly linked-list. The list can be represented as:
 *
 * L0 → L1 → … → Ln - 1 → Ln
 *
 * Reorder the list to be on the following form:
 *
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 *
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 */
public class ReorderList {
    //1. find mid pointer 2.reverse second half 3. cut in half 4.reconect nodes from two list
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = reverse(slow.next);
        slow.next = null;
        slow = head;
        while(slow != null && fast != null) {
            ListNode tmp = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = fast.next;
            fast = tmp;
        }
    }

    private  ListNode reverse(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        while(head != null && head.next != null) {
            ListNode tmp = pre.next;
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = tmp;
        }
        return pre.next;
    }
}
