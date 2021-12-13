package linkedlist;

/**
 * Given the head of a singly linked list, return true if it is a palindrome.
 *
 * Example 1:
 *
 * Input: head = [1,2,2,1]
 * Output: true
 *
 * Example 2:
 *
 * Input: head = [1,2]
 * Output: false
 *
 * Constraints:
 *
 *     The number of nodes in the list is in the range [1, 105].
 *     0 <= Node.val <= 9
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head){
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHead = reverseList(slow.next);
        while(secondHead != null){
            if(secondHead.val != head.val) return false;
            secondHead = secondHead.next;
            head = head.next;
        }
        return true;
    }

    private ListNode reverseList(ListNode head){
        if(head == null || head.next == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
        while(head.next != null){
            ListNode tmp = pre.next;
            pre.next = head.next;
            head.next = head.next.next;
            pre.next.next = tmp;
        }
        return pre.next;
    }
}
