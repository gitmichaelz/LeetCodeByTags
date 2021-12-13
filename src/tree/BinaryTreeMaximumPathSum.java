package tree;
/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge
 * connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 * Example 1:
        *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
public class BinaryTreeMaximumPathSum {
    //path sum 的总结：https://www.1point3acres.com/bbs/thread-545654-1-1.html
    //for this problem, we can use a global variable or arr[] to track the max path sum, at any node
    //if we have max < left.val + right.val + root.val, we update max,
    public int maxPathSum(TreeNode root) {
        int[] sum = new int[1];
        sum[0] = Integer.MIN_VALUE;
        pathSum(root, sum);
        return sum[0];
    }

    //calculate max path sum for cur root all the way down to any leaf (root -> leaf)
    //also update sum[0] = Math.max(sum[0], left + right + root.val). why? because this problem is about any node to node, dont have to
    //include the root
    private int pathSum(TreeNode root, int[] sum) {
        if (root == null) return 0;
        int left = pathSum(root.left, sum);
        int right = pathSum(root.right, sum);
        left = left > 0 ? left : 0;
        right = right > 0 ? right : 0;
        sum[0] = Math.max(left + right + root.val, sum[0]);//if left < 0, right < 0, we can just take root, so we make left, right = 0 at above step
        return Math.max(left, right) + root.val;
    }
}