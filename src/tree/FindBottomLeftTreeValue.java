package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary tree, return the leftmost value in the last row of the tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [2,1,3]
 * Output: 1
 */
public class FindBottomLeftTreeValue {
    //Doing BFS right-to-left means we can simply return the last node's value and don't have to keep track of the first node in the current row or even care about rows at all.
    public int findBottomLeftValue(TreeNode root) {
        if(root == null) return -1;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                root = queue.poll();
                if(root.right != null) queue.offer(root.right);
                if(root.left != null) queue.offer(root.left);
            }
        }
        return root.val;
    }
}
