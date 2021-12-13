package linkedlist;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode preF = new ListNode(0);
        ListNode first = preF;
        ListNode preS = new ListNode(0);
        ListNode second = preS;
        while(head != null) {
            if(head.val < x) {
                first.next = head;
                first = first.next;
            } else {
                second.next = head;
                second = second.next;
            }
            head = head.next;
        }
        second.next = null;//dont forget make second.next = null
        first.next = preS.next;
        return preF.next;
    }
}
