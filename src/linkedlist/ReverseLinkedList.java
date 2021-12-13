package linkedlist;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLinkedList {
    //four steps.
    // 1. tmp = pre.next
    // 2. pre.next = head.next
    // 3. head.next = head.next.next
    // 4. pre.next.next = tmp
    public ListNode reverseList(ListNode head) {
        if(head == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        while(head.next != null) {
            ListNode tmp = pre.next;
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = tmp;
        }
        return pre.next;
    }
}
