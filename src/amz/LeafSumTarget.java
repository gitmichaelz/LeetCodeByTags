package amz;

import java.util.ArrayDeque;
import java.util.Deque;

public class LeafSumTarget {
    public boolean findLeafSumEqualToTarget(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int sum = 0;
            for (int size = queue.size(); size > 0; size--) {
                root = queue.poll();
                if (root.left == null && root.right == null) {
                    sum += root.val;
                } else {
                    if (root.left != null) {
                        queue.offer(root.left);
                    }
                    if (root.right != null) {
                        queue.offer(root.right);
                    }
                }
            }
            if (sum == target) {
                return true;
            }
        }
        return false;
    }
}
