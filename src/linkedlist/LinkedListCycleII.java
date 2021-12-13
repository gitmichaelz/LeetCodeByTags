package linkedlist;

/**
 * Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
 *
 * Do not modify the linked list.
 */
public class LinkedListCycleII {
    //https://leetcode.com/problems/linked-list-cycle-ii/discuss/44781/Concise-O(n)-solution-by-using-C%2B%2B-with-Detailed-Alogrithm-Description
    /*2.1) L1 is defined as the distance between the head point and entry point

    2.2) L2 is defined as the distance between the entry point and the meeting point

    2.3) C is defined as the length of the cycle

    2.4) n is defined as the travel times of the fast pointer around the cycle When the first encounter of the slow pointer and the fast pointer

    According to the definition of L1, L2 and C, we can obtain:

    the total distance of the slow pointer traveled when encounter is L1 + L2

    the total distance of the fast pointer traveled when encounter is L1 + L2 + n * C

    Because the total distance the fast pointer traveled is twice as the slow pointer, Thus:

    2 * (L1+L2) = L1 + L2 + n * C => L1 + L2 = n * C => L1 = (n - 1) C + (C - L2)*
    */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow) {
                slow = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
