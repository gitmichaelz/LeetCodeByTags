package tree;

/**
 * Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [4,2,6,1,3]
 * Output: 1
 */
public class MinimumDistanceBetweenBSTNodes {
    int min = Integer.MAX_VALUE;
    TreeNode pre = null;
    public int minDiffInBST(TreeNode root) {
        if(root == null) return 0;
        minDiffInBST(root.left);
        if(pre != null) {
            min = Math.min(min, root.val - pre.val);
        }
        pre = root;
        minDiffInBST(root.right);
        return min;
    }
    // public int getMinimumDifference(TreeNode root) {
    //     int min = Integer.MAX_VALUE;
    //     Deque<TreeNode> stack = new LinkedList<>();
    //     TreeNode pre = null;
    //     while(root != null || !stack.isEmpty()){
    //         if(root != null){
    //             stack.push(root);
    //             root = root.left;
    //         } else {
    //             root = stack.pop();
    //             if(pre != null){
    //                 min = Math.min(min, root.val - pre.val);
    //             }
    //             pre = root;
    //             root = root.right;
    //         }
    //     }
    //     return min;
    // }
}
