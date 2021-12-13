package tree;

/**
 * Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
 *
 * A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [8,3,10,1,6,null,14,null,null,4,7,13]
 * Output: 7
 */
public class MaximumDifferenceBetweenNodeAndAncestor {
    //top down along path from root to leaf, we pass max and min value to children, and return the maxDiff from
    //left/right subtree by doing
    public int maxAncestorDiff(TreeNode root){
        return maxDiff(root, root.val, root.val);
    }
    private int maxDiff(TreeNode root, int min, int max) {
        if(root == null) return max - min;//base case, leaf node, return max - min. (the max/min is the max/min along the path from root to leaf)
        min = Math.min(root.val, min);
        max = Math.max(root.val, max);
        return Math.max(maxDiff(root.left, min, max), maxDiff(root.right, min, max));
    }
}
