package linkedlist;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 *
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 *
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 * Constraints:
 *
 *     k == lists.length
 *     0 <= k <= 10^4
 *     0 <= lists[i].length <= 500
 *     -10^4 <= lists[i][j] <= 10^4
 *     lists[i] is sorted in ascending order.
 *     The sum of lists[i].length won't exceed 10^4
 */
public class MergeKSortedLists {
    /*
     * important: there are nk node, not n node, so runtime : nklogn, n is array length
     * time complexity: assume array length is n, each list has k nodes; so there are nk nodes totally,
     * divide step needs logn time, merge needs nk time because we need to compare every single node, so totally nklogn time.
     * draw a recursion tree will help you understand better
     * on each level of the recursion tree, we need nk compares, there are totally logn levels
     *
     * Space: logn, because need logn space to make recursion calls
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0) return null;

        return divideLists(lists, 0, lists.length - 1);
    }

    private ListNode divideLists(ListNode[] lists, int left, int right) {
        if(left == right) return lists[left];
        int mid = (left + right)/2;
        ListNode leftList = divideLists(lists, left, mid);
        ListNode rightList = divideLists(lists, mid + 1, right);
        return mergeTwoLists(leftList, rightList);
    }

    private ListNode mergeTwoLists(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while(node1 != null && node2 != null) {
            if(node1.val < node2.val) {
                current.next = node1;
                node1 = node1.next;
            } else {
                current.next = node2;
                node2 = node2.next;
            }
            current = current.next;
        }
        current.next = node1 == null? node2 : node1;
        return dummy.next;
    }
}
