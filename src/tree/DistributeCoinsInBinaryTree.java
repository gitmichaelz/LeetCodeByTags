package tree;

/**
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.
 *
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.
 *
 * Return the minimum number of moves required to make every node have exactly one coin.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,0,0]
 * Output: 2
 */
public class DistributeCoinsInBinaryTree {
    //https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C%2B%2B-with-picture-post-order-traversal
    //postorder traverse, calculate child's balance by child.val - 1, so at root node, the total balance is root.val - 1 + left balance + right balance
    //and the total moves needs to be abs(left balance) + abs(right balance)
    int res = 0;
    public int distributeCoins(TreeNode root) {
        postorder(root);
        return res;
    }

    private int postorder(TreeNode root){
        if(root == null) return 0;
        int leftBalance = postorder(root.left);
        int rightBalance = postorder(root.right);
        res += Math.abs(leftBalance) + Math.abs(rightBalance);
        return root.val - 1 + leftBalance + rightBalance;
    }
}
