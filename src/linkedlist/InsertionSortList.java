package linkedlist;

public class InsertionSortList {
    //improvement for the below approach:inside while, we only set pre = dummy if pre.val > head.val
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);//which next pointing to the head of the sorted List
        ListNode pre = dummy;//the one used to scan the sorted list to find the insert position
        while(head != null) {
            ListNode tmp = head.next;
            if(pre.val > head.val) pre = dummy;
            while(pre.next != null && pre.next.val < head.val) {
                pre = pre.next;
            }
            head.next = pre.next;
            pre.next = head;
            head = tmp;
        }

        return dummy.next;
    }
    /*
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);//which next pointing to the head of the sorted List
        ListNode pre = dummy;//the one used to scan the sorted list to find the insert position
        while(head != null) {
            ListNode tmp = head.next;
            while(pre.next != null && pre.next.val < head.val) {
                pre = pre.next;
            }
            head.next = pre.next;
            pre.next = head;
            head = tmp;
            pre = dummy;//pre is always pointing to the first one before the sorted list
        }

        return dummy.next;
    }
    */
}
