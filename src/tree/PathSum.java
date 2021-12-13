package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 */
public class PathSum {
    //traverse tree, use two queue, one for treenode, another for sum, when hit leaf, compare sum with leaf's value
    public boolean hasPathSum(TreeNode root, int sum){
        if(root == null) return false;
        Deque<TreeNode> queue1 = new LinkedList<>();
        Deque<Integer> queue2 = new LinkedList<>();
        queue1.offer(root);
        queue2.offer(sum);
        while(!queue1.isEmpty()){
            root = queue1.poll();
            sum = queue2.poll();
            if(root.left == null && root.right == null && root.val == sum) return true;
            if(root.left != null){
                queue1.offer(root.left);
                queue2.offer(sum - root.val);
            }
            if(root.right != null) {
                queue1.offer(root.right);
                queue2.offer(sum - root.val);
            }

        }
        return false;
    }
    //子树路径和等于当前sum减去当前节点的值。结束条件是如果当前节点是空的，则返回false，如果是叶子，那么如果剩余的sum等于当前叶子的值，则找到满足条件的路径，返回true
    // public boolean hasPathSum(TreeNode root, int sum) {
    //     if(root == null) return false;//important, when we hit a root == null,we return false, not sum == 0, because we already check the leaf value
    //     //on last recursive steps root.val == sum, any further checks are wrong
    //     if(root.left == null && root.right == null) return root.val == sum;
    //     return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    // }
}
