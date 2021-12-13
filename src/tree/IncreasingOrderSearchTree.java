package tree;

/**
 * Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 */
public class IncreasingOrderSearchTree {
    //inorder traversal, use a pre node to connect pre node and cur visited node, dont forget make cur node's left = null
    private TreeNode dummy = new TreeNode(-1);
    private TreeNode pre = dummy;
    public TreeNode increasingBST(TreeNode root) {
        inorderTraversal(root);
        return dummy.right;
    }

    private void inorderTraversal(TreeNode root) {
        if(root == null) return;
        inorderTraversal(root.left);
        pre.right = root;
        root.left = null;
        pre = root;
        inorderTraversal(root.right);
    }
}
