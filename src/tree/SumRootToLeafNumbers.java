package tree;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 *
 * Each root-to-leaf path in the tree represents a number.
 *
 *     For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 *
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 *
 * A leaf node is a node with no children.
 *
 *Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0, 0);
    }
    private int helper(TreeNode root, int val, int sum) {
        if(root == null) return sum;
        val = val * 10 + root.val;
        if(root.left == null && root.right == null) {
            sum += val;
            return sum;
        }
        return helper(root.left, val, sum) + helper(root.right, val, sum);
    }
}
