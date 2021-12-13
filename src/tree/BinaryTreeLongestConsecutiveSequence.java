package tree;

/**
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path needs to be from parent to child (cannot be the reverse).
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 */
public class BinaryTreeLongestConsecutiveSequence {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        lcs(root);
        return max;
    }

    private int lcs(TreeNode root){
        if(root == null) return 0;
        int leftDepth = 1 + lcs(root.left);//default the sequence is expanding
        int rightDepth = 1 + lcs(root.right);
        if(root.left != null && root.val + 1 != root.left.val) {
            leftDepth = 1;
        }
        if(root.right != null && root.val + 1 != root.right.val) {
            rightDepth = 1;
        }
        int curLCS = Math.max(leftDepth, rightDepth);
        max = Math.max(max, curLCS);
        return curLCS;
    }
}
