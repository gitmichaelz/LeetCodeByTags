package tree;

/**
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.
 *
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 *
 *     The left subtree values are less than the value of their parent (root) node's value.
 *     The right subtree values are greater than the value of their parent (root) node's value.
 *
 * Note: A subtree must include all of its descendants.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
 */
public class LargestBSTSubtree {
    int max = 0;
    public int largestBSTSubtree(TreeNode root) {
        validateBST(root);;//res[0]: size, res[1]: low bound, res[2]: upper bound
        return max;
    }
    private int[] validateBST(TreeNode root){
        if(root == null) {
            return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] left = validateBST(root.left);
        int[] right = validateBST(root.right);
        if(left[0] == -1 || right[0] == -1 || root.val <= left[2] || root.val >= right[1]) {
            return new int[]{-1, 0, 0};
        }
        int size = left[0] + 1 + right[0];
        max = Math.max(max, size);
        return new int[]{size, Math.min(root.val, left[1]), Math.max(root.val, right[2])};
    }
}
