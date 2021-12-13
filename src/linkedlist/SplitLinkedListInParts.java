package linkedlist;

/**
 * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
 *
 * The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 *
 * Return an array of the k parts.
 *
 *
 *
 * Example 1:
 *
 * Input: head = [1,2,3], k = 5
 * Output: [[1],[2],[3],[],[]]
 */
public class SplitLinkedListInParts {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        int len = 0;
        for(ListNode node = root; node != null; node = node.next) {
            len++;
        }
        int n = len/k, remainder = len % k;//n is the minimal length of nodes in each res[i], remainder is the extra nodes spread in first remainder cells
        ListNode pre = null;
        for(int i = 0; i < k && root != null; i++, remainder--) {
            res[i] = root;
            for(int j = 0; j < n + (remainder > 0? 1 : 0); j++) {
                pre = root;
                root = root.next;
            }
            pre.next = null;
        }
        return res;
    }
}
