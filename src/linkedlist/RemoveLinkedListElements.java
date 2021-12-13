package linkedlist;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 * Example 1:
 *
 * Input: head = [1,2,6,3,4,5,6], val = 6
 * Output: [1,2,3,4,5]
 */
public class RemoveLinkedListElements {
    //use a pre node, we only make pre node point to next node when next node's value != val;
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(head != null) {
            if(head.val != val) {
                pre.next = head;
                pre = pre.next;
            }
            head = head.next;
        }
        pre.next = null;//need make pre.next = null, in case we have 1-2-3-7-7-7 where target val is 7
        return dummy.next;
    }
}
