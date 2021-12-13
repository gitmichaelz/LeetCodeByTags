package linkedlist;

/**
 * Given a non-negative integer represented as a linked list of digits, plus one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3]
 * Output: [1,2,4]
 *
 * Example 2:
 *
 * Input: head = [0]
 * Output: [1]
 */
public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNonNine = dummy, cur = head;
        while(cur != null) {
            if(cur.val != 9) {
                lastNonNine = cur;
            }
            cur = cur.next;
        }
        lastNonNine.val ++;
        lastNonNine = lastNonNine.next;
        while(lastNonNine != null) {
            lastNonNine.val = 0;
            lastNonNine = lastNonNine.next;
        }
        return dummy.val == 1? dummy : dummy.next;
    }
}
