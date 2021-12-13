package tree;

/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 *
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 */
public class DiameterOfBinaryTree {
    //Time Complexity: O(n) since we must visit each node.
    //Space Complexity: O(log n) if balanced tree, O(n) otherwise. Space complexity is due to the recursion.
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return diameter;
    }
    //calculate maxDepth for a given root
    //and during the process, we can also calculate each diameter passing the given node root, which is leftD + rightD,
    //and what we need to do is update diameter once we have leftD + rightD is greater than max diameter so far
    private int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int leftD = maxDepth(root.left);
        int rightD = maxDepth(root.right);
        diameter = Math.max(diameter, leftD + rightD);
        return 1 + Math.max(leftD, rightD);
    }
}
