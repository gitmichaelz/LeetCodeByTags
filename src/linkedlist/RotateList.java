package linkedlist;
/**
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 */
public class RotateList {
    //since k could be larger than the list length, so we can iterate the list to find the len, and connect tail to head
    //then from tail we (move len - k % len) steps
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0) return head;
        ListNode p = head;
        int len = 1;
        while(p.next != null) {
            len++;
            p = p.next;
        }
        p.next = head;//connect tail to head
        k = len - k % len;
        while(k > 0) {
            p = p.next;
            k--;
        }
        head = p.next;
        p.next = null;
        return head;
    }
}
