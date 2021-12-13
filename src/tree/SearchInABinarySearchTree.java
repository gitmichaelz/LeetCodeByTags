package tree;

/**
 * You are given the root of a binary search tree (BST) and an integer val.
 *
 * Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.
 */
public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        while(root != null) {
            if(root.val > val) {
                root = root.left;
            } else if(root.val < val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
