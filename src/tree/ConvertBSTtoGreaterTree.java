package tree;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 */
public class ConvertBSTtoGreaterTree {
    int preSum = 0;
    public TreeNode convertBST(TreeNode root){
        dfs(root);
        return root;
    }
    private void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.right);
        root.val += preSum;
        preSum = root.val;
        dfs(root.left);
    }
    // public TreeNode convertBST(TreeNode root) {
    //     if(root == null) return null;
    //     TreeNode cur = root;
    //     Deque<TreeNode> stack = new LinkedList<>();
    //     TreeNode pre = null;
    //     while(cur != null || !stack.isEmpty()) {
    //         if(cur != null) {
    //             stack.push(cur);
    //             cur = cur.right;
    //         } else {
    //             cur = stack.pop();
    //             if(pre != null){
    //                 cur.val += pre.val;
    //             }
    //             pre = cur;
    //             cur = cur.left;
    //         }
    //     }
    //     return root;
    // }
}
