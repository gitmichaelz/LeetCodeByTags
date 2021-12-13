package linkedlist;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers
 * from the original list. Return the linked list sorted as well.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 */
public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while(head != null && head.next != null) {
            if(head.val != head.next.val) {
                pre.next = head;
                pre = pre.next;
                head = head.next;
            } else {
                while(head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                head = head.next;//one more step to skip the last duplicate one
            }
        }
        pre.next = head;//in case of head.next = null, we need to connect pre.next = head;1, 1, 1, 2, 3, 4, 4, 5
        return dummy.next;
    }
}
