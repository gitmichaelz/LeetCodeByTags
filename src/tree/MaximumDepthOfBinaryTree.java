package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary tree, return its maximum depth.
 *
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int level = 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            level++;
            for(int size = queue.size(); size > 0; size--) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return level;
    }

    // public int maxDepth(TreeNode root) {
    //     if(root == null) return 0;
    //     return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    // }
}
