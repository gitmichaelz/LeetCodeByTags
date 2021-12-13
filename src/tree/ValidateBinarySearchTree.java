package tree;

/**
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    public boolean validBST(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return validBST(root.left, min, root.val) && validBST(root.right, root.val, max);
    }


    // public boolean isValidBST(TreeNode root) {
    //     TreeNode pre = null;
    //     Deque<TreeNode> stack = new LinkedList<>();
    //     while(root != null || !stack.isEmpty()) {
    //         if(root != null) {
    //             stack.push(root);
    //             root = root.left;
    //         } else {
    //             root = stack.pop();
    //             if(pre != null && pre.val >= root.val) return false;
    //             pre = root;
    //             root = root.right;
    //         }
    //     }
    //     return true;
    // }
}
