package tree;

/**
 * Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.
 *
 * A subtree of a tree is any node of that tree plus all its descendants.
 *
 * The average value of a tree is the sum of its values, divided by the number of nodes.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [5,6,1]
 * Output: 6.00000
 * Explanation:
 * For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
 * For the node with value = 6 we have an average of 6 / 1 = 6.
 * For the node with value = 1 we have an average of 1 / 1 = 1.
 * So the answer is 6 which is the maximum.
 */
public class MaximumAverageSubtree {
    //to get every average of a node, we need the number of its subtree nodes and their sum
    //we need to compute root's subtree number and sum in post order way, and then process the current root
    double res = 0;
    public double maximumAverageSubtree(TreeNode root) {
        getAverage(root);
        return res;
    }
    //return {number of node, sum of node's value}
    private double[] getAverage(TreeNode root) {
        if(root == null) return new double[]{0, 0};
        double[] left = getAverage(root.left);
        double[] right = getAverage(root.right);
        double[] ans = new double[2];
        ans[0] = 1 + left[0] + right[0];
        ans[1] = root.val + left[1] + right[1];
        res = Math.max(res, ans[1]/ans[0]);
        return ans;
    }
}
