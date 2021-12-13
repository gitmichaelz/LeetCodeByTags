package linkedlist;

/**
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 */
public class IntersectionOfTwoLinkedLists {
    //after each head hits to end, we let it to another head, and do another scan, so the two lists will scan twice and each
    //length will be lenA + lenB, at last we return headA, which either would be intersection point or null
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while(nodeA != nodeB) {//因为是最多每个点访问两次,nodeA和nodeB最多同时走lenA + lenB,最后一定有nodeA nodeB要么都等于null,要么在某点相交
            nodeA = nodeA == null? headB : nodeA.next;
            nodeB = nodeB == null? headA : nodeB.next;
        }
        return nodeA;
    }
}
