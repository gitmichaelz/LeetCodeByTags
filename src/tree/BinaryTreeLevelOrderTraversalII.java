package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left\
 * to right, level by level from leaf to root).
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 */
public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if(root == null) return res;
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, LinkedList<List<Integer>> res, int level) {
        if(root == null) return;
        if(res.size() <= level) {
            res.addFirst(new ArrayList<>());//注意，这里是从addFirst
        }
        res.get(res.size() - level - 1).add(root.val);
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }

    // public List<List<Integer>> levelOrderBottom(TreeNode root) {
    //     LinkedList<List<Integer>> res = new LinkedList<>();
    //     if(root == null) return res;
    //     Deque<TreeNode> queue = new LinkedList<>();
    //     queue.offer(root);
    //     while(!queue.isEmpty()) {
    //         List<Integer> level = new LinkedList<>();
    //         for(int size = queue.size(); size > 0; size--) {
    //             root = queue.poll();
    //             level.add(root.val);
    //             if(root.left != null) queue.offer(root.left);
    //             if(root.right != null) queue.offer(root.right);
    //         }
    //         res.addFirst(level);
    //     }
    //     return res;
    // }
}
