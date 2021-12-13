package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 */
public class VerticalOrderTraversalOfABinaryTree {
    class Point{
        int x;
        int y;
        int val;
        Point(int x, int y, int val){
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    //idea, preorder traverse the tree nodes, put nodes in q queue
    //the compare order in the queue should be like this: 1> compare x first, if x is same, compare y, if y is same, compare val
    public List<List<Integer>> verticalTraversal(TreeNode root){
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> {
            if(a.x != b.x) return a.x - b.x;
            if(a.y != b.y) return b.y - a.y;
            return a.val - b.val;
        });
        preorderTraverse(root, 0, 0, pq);
        List<List<Integer>> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            Point p = pq.poll();
            list.add(p.val);
            while(!pq.isEmpty() && p.x == pq.peek().x) {//if they have same x position (they are in same y axis)
                list.add(pq.poll().val);
            }
            res.add(list);
        }
        return res;
    }
    private void preorderTraverse(TreeNode root, int x, int y, PriorityQueue<Point> pq) {
        if(root == null) return;
        pq.offer(new Point(x, y, root.val));
        preorderTraverse(root.left, x - 1, y - 1, pq);
        preorderTraverse(root.right, x + 1, y - 1, pq);
    }
}
