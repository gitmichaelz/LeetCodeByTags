package tree;

/**
 * Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
 *
 * You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 *
 * We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.
 *
 *
 */
public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    //Divide and Conquer
    //    Step 1: Divide:
    //    We divide tree into three parts: left subtree, root node, right subtree.
    //    Convert left subtree into a circular doubly linked list as well as the right subtree.
    //    Be careful. You have to make the root node become a circular doubly linked list.
    //
    //    Step 2: Conquer:
    //    Firstly, connect left circular doubly linked list with the root circular doubly linked list.
    //            Secondly, connect them with the right circular doubly linked list. Here we go!
    //in-place
    //note: the order of join makes sure it is a sorted list, because we are joining left to right, that is left, root, and then right
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        Node left = treeToDoublyList(root.left);
        Node right = treeToDoublyList(root.right);
        root.left = root;//root 本身也要转换成doubly linked list
        root.right = root;
        return connect(connect(left, root), right);
    }
    //return the head of the connected doubly linked list, we need 4 steps/4 connections
    private Node connect(Node left, Node right) {
        if(left == null) return right;
        if(right == null) return left;
        Node leftTail = left.left;
        Node rightTail = right.left;
        leftTail.right = right;
        right.left = leftTail;
        rightTail.right = left;
        left.left = rightTail;
        return left;
    }
}
