package linkedlist;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 *
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 */
public class ReverseLinkedListII {
    //move the pre node till m position, and do reverse until nth node,
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int count = 1;
        while(head.next != null && count < m){
            head = head.next;
            pre = pre.next;
            count++;
        }
        while(head.next != null && count < n){
            ListNode tmp = pre.next;
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = tmp;
            count++;
        }
        return dummy.next;
    }
}
