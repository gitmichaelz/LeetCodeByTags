package linkedlist;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Example 2:
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 * Example 3:
 *
 * Input: head = [1,2,3,4,5], k = 1
 * Output: [1,2,3,4,5]
 *
 */
public class ReverseNodesInK_Goupe {
    //take 1,2,3,4,5 and k = 3 as an example
    //we must make sure that after the reverse, 3, 2, 1 -> 4, 5 (e.g 1 points to 4)
    //so when we do reverse, we have to do this job (1-> 4) in our reverse method
    //also, we need to return the node before head, we need a pre node to do the reversal just like in a normal reversal problem
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int count = 0;
        while(head != null) {
            head = head.next;
            count++;
            if(count == k){
                pre = reverseList(pre, head);//return the node before head, reverseList(0, 4)
                count = 0;
            }
        }
        return dummy.next;
    }
    //正如我们做一般的翻转列表时，需要一个pre节点。这个method也需要类似的一个pre节点。来翻转节点之后的列表。而end节点则相当于普通列表中的null节点。
    //we need to make sure after reversal, the reversed list connects to the remaining list nodes
    //return the node before end
    //这个method实际上是翻转pre和end之间的节点。并不包括pre 和end这两个。即 pre->node1->node2->node3->end,
    //翻转后变为pre->node3->node2->node1->end
    //返回值为end之前节点。
    private ListNode reverseList(ListNode pre, ListNode end) {
        ListNode head = pre.next;
        while(head.next != end) {//我们的目标是让head.next = end,可以作为while退出条件
            ListNode tmp = pre.next;
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = tmp;
        }
        return head;
    }
}
