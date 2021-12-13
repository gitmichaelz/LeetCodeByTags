package linkedlist;

/**
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 * Constraints:
 *
 *     The number of nodes in the list is sz.
 *     1 <= sz <= 30
 *     0 <= Node.val <= 100
 *     1 <= n <= sz
 */
public class RemoveNthNodeFromEndofList {
    //use fast and slow pointer, first let fast pointer move n steps, so we have a gap between fast and slow pointer
    //and then let fast and slow move at the same time with same steps until fast pointer to the end
    //let slow.next = slow.next.next
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        for(int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while(fast.next != null) {//dont worry about if fast == null or not, since n is always valid
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
