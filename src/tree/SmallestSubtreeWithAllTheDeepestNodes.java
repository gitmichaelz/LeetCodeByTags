package tree;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 *
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 *
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 *
 * The subtree of a node is a tree consisting of that node, plus the set of all descendants of that node.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
//local view: if left subtree depth == right subtree depth, return current root
    //if left subtree depth > right subtree depth, return left subtree root to upper level
    //if right subtree depth > left subtree depth, return right subtree root to upper level
    //so we need to include tow variables in our return, the depth and node

    class Pair{
        int depth;
        TreeNode node;
        Pair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return getDepth(root).node;
    }

    private Pair getDepth(TreeNode root) {
        if(root == null) return new Pair(-1, null);
        Pair left = getDepth(root.left);
        Pair right = getDepth(root.right);
        int leftD = left.depth;
        int rightD = right.depth;
        return new Pair(Math.max(leftD, rightD) + 1, leftD == rightD? root : leftD > rightD? left.node : right.node);
    }


    //same as lc1123  https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
    // int deepest = -1;
    // TreeNode res = null;
    // public TreeNode subtreeWithAllDeepest(TreeNode root) {
    //     getDepth(root, 0);
    //     return res;
    // }
    // private int getDepth(TreeNode root, int depth){
    //     deepest = Math.max(deepest, depth);
    //     if(root == null) return depth;
    //     int left = getDepth(root.left, depth + 1);
    //     int right = getDepth(root.right, depth + 1);
    //     if(left == deepest && right == deepest) {
    //         res = root;
    //     }
    //     return Math.max(left, right);//no need + 1, since when we do recursion left = findDepth(root.left, depth + 1), we already pass the cur depth + 1 to next recursion
    // }
}
