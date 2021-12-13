package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 */
public class MinimumDepthOfBinaryTree {
    //level order, once we hit first leaf, we return the level;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int minDepth = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            minDepth++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                root = queue.poll();
                if(root.left == null && root.right == null) return minDepth;
                if(root.left != null) {
                    queue.offer(root.left);
                }
                if(root.right != null) {
                    queue.offer(root.right);
                }
            }
        }
        return minDepth;
    }
    //因为是判断最小深度，所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）
    // public int minDepth(TreeNode root) {
    //     if(root == null) return 0;
    //     if(root.left == null) return minDepth(root.right) + 1;
    //     if(root.right == null) return minDepth(root.left) + 1;
    //     return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    // }
}
