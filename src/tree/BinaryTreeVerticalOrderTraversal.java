package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 */
public class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        int[] range = {0, 0};
        getRange(range, 0, root);
        for(int i = range[0]; i <= range[1]; i++){
            res.add(new ArrayList<>());
        }
        Deque<TreeNode> nodeQueue = new LinkedList<>();
        Deque<Integer> colQueue = new LinkedList<>();
        nodeQueue.offer(root);
        colQueue.offer(-range[0]);//root所在列的位置，在0 index的列表中，其位置是-range[0]
        while(!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            int col = colQueue.poll();
            res.get(col).add(node.val);
            if(node.left != null){
                nodeQueue.offer(node.left);
                colQueue.offer(col - 1);
            }
            if(node.right != null){
                nodeQueue.offer(node.right);
                colQueue.offer(col + 1);
            }
        }
        return res;
    }

    private void getRange(int[] range, int col, TreeNode root){
        if(root == null) return;
        range[0] = Math.min(range[0], col);
        range[1] = Math.max(range[1], col);
        getRange(range, col - 1, root.left);
        getRange(range, col + 1, root.right);
    }


    /*
    class Point{
        int x, y, val;
        Point(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    public List<List<Integer>> verticalOrder(TreeNode root){
        LinkedList<Point> queue = new LinkedList<>();
        traverse(root, 0, 0, queue);
        Collections.sort(queue, (a, b) -> a.x == b.x? b.y - a.y : a.x - b.x);
        List<List<Integer>> res = new ArrayList<>();
        while(!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            Point p = queue.poll();
            list.add(p.val);
            while(!queue.isEmpty() && queue.peek().x == p.x){
                list.add(queue.poll().val);
            }
            res.add(list);
        }
        return res;
    }

    private void traverse(TreeNode root,int x, int y, LinkedList<Point> queue){
        if(root == null) return;
        queue.offer(new Point(x, y, root.val));
        traverse(root.left, x - 1, y -1, queue);
        traverse(root.right, x + 1, y - 1, queue);
    }
    */
}
