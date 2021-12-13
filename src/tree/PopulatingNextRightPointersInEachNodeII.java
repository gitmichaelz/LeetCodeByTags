package tree;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 */
public class PopulatingNextRightPointersInEachNodeII {
    //O(N) time, O(1) space
    public Node connect(Node root){
        if(root == null) return null;
        Node cur = root;
        while(cur != null) {
            Node dummy = new Node(0);//head of next level
            Node curChild = dummy;//traverse next level
            while(cur != null){
                if(cur.left  != null) {
                    curChild.next = cur.left;
                    curChild = curChild.next;
                }
                if(cur.right != null) {
                    curChild.next = cur.right;
                    curChild = curChild.next;
                }
                cur = cur.next;
            }
            cur = dummy.next;//update cur to next level's head
        }
        return root;
    }
}
