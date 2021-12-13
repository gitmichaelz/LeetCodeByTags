package linkedlist;

/**
 * You are given a doubly linked list, which contains nodes that have a next pointer, a previous pointer, and an additional child pointer. This child pointer may or may not point to a separate doubly linked list, also containing these special nodes. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure as shown in the example below.
 *
 * Given the head of the first level of the list, flatten the list so that all the nodes appear in a single-level, doubly linked list. Let curr be a node with a child list. The nodes in the child list should appear after curr and before curr.next in the flattened list.
 *
 * Return the head of the flattened list. The nodes in the list must have all of their child pointers set to null.
 */
public class FlattenAMultilevelDoublyLinkedList {
    // Definition for a Node.
    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }


//Start form the head , move one step each time to the next node
    //When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next, by doing this we merged the child chain back to the main thread
    //Return to p and proceed until find next node with child.
    //Repeat until reach null

    public Node flatten(Node head) {
        if(head == null) return null;
        Node cur = head;
        while(cur != null){
            if(cur.child == null) {
                cur = cur.next;
            } else {
                Node child = cur.child;
                while(child.next != null){//here we use child.next != null, not child != null
                    child = child.next;
                }
                child.next = cur.next;//connect child's tails to cur.next
                if(cur.next != null) {
                    cur.next.prev = child;
                }
                // Connect cur with cur.child, and remove cur.child
                cur.next = cur.child;
                cur.child.prev = cur;
                cur.child = null;
            }
        }
        return head;
    }
}
