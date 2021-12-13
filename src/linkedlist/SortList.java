package linkedlist;

/**
 * Given the head of a linked list, return the list after sorting it in ascending order.
 *
 * Example 1:
 *
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 */
public class SortList {
    //the problem requirs constant space, so we dont use the recursion to do the merge sort, instead, we use bottom up to do the merge sort.
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int count = 1;
        while(head.next != null) {
            head = head.next;
            count++;
        }

        for(int i = 1; i < count; i *= 2) {
            ListNode pre = dummy;
            ListNode cur = pre.next;
            while(cur != null) {
                ListNode left = cur;
                ListNode right = split(cur, i);
                cur = split(right, i);//这个很重要，cur更新为right half之后的一个节点，即下一轮merge的左half的起点
                pre = merge(left, right, pre);
            }
        }
        return dummy.next;
    }

    //return the head of second half after split operation
    private ListNode split(ListNode head, int steps) {
        if(head == null) return head;
        for(int i = 1; i < steps && head.next != null; i++) {
            head = head.next;
        }
        ListNode right = head.next;
        head.next = null;
        return right;
    }

    //merge left and right, also connect pre node to the merged left right so that every node can connect to each other finally
    //return the last node of the connected listnodes
    private ListNode merge(ListNode left, ListNode right, ListNode pre) {
        while(left != null && right != null) {
            if(left.val < right.val) {
                pre.next = left;
                left = left.next;
            } else {
                pre.next = right;
                right = right.next;
            }
            pre = pre.next;
        }

        pre.next = left == null? right : left;
        while(pre.next != null) pre = pre.next;
        return pre;
    }

//     public ListNode sortList(ListNode head) {
//         if(head == null || head.next == null) return head;
//         ListNode slow = head, fast = head;
//         while(fast.next != null && fast.next.next != null) {
//             fast = fast.next.next;
//             slow = slow.next;
//         }
//         fast = slow.next;
//         slow.next = null;
//         return merge(sortList(head), sortList(fast));
//     }

//     private ListNode merge(ListNode l1, ListNode l2) {
//         ListNode dummy = new ListNode(0);
//         ListNode cur = dummy;
//         while(l1 != null && l2!= null) {
//             if(l1.val < l2.val) {
//                 cur.next = l1;
//                 l1 = l1.next;
//             } else {
//                 cur.next = l2;
//                 l2 = l2.next;
//             }
//             cur = cur.next;
//         }
//         cur.next = l1 == null? l2 : l1;
//         return dummy.next;
//     }
}
