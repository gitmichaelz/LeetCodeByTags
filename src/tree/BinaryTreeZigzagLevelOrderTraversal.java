package tree;

import java.util.*;

/**
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right,
 * then right to left for the next level and alternate between).
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    //两种方式都掌握
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        int level = 0;
        queue.offer(root);
        while(!queue.isEmpty()) {
            level++;
            LinkedList<Integer> list = new LinkedList<>();
            for(int size = queue.size(); size > 0; size--) {
                root = queue.poll();
                if(level % 2 == 0) {
                    list.addFirst(root.val);
                } else {
                    list.add(root.val);
                }
                if(root.left != null) queue.offer(root.left);//入队列的时候还是按正常顺序入队列
                if(root.right != null) queue.offer(root.right);
            }
            res.add(list);
        }
        return res;
    }


    public List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if(root == null) return;
        if(res.size() <= level) {
            res.add(new LinkedList<>());
        }
        if(level % 2 == 0) {
            res.get(level).add(root.val);
        } else {
            res.get(level).add(0, root.val);//会调用linkedList的addFirst方法，O(1)
        }
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }
}
