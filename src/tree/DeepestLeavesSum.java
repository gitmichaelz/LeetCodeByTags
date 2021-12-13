package tree;

/**
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 */
public class DeepestLeavesSum {
    int sum = 0;
    int max = 0;//max level
    public int deepestLeavesSum(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
    //traverse down the tree, once we hit a new level, we make maxlevel = level, and sum = root.val
    //or if maxLevel == level, we do sum += root.val
    private void dfs(TreeNode root, int level) {
        if(root == null) return;
        if(max == level) sum += root.val;
        else if(max < level) {
            sum = root.val;
            max = level;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}
