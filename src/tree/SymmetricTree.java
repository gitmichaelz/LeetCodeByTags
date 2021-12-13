package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        //注意两种stack的创建方式的区别。Deque<TreeNode> stackl = new ArrayDeque<>(); 不可以push null element, 否则会NPE
        // Deque<TreeNode> stackl = new LinkedList<>();则可以push null element, 并且stack不为空，stack.isEmpty() 为false
        Deque<TreeNode> stackl = new LinkedList<>();
        Deque<TreeNode> stackr = new LinkedList<>();
        stackl.push(root.left);
        stackr.push(root.right);
        while(!stackl.isEmpty() && !stackr.isEmpty()){
            TreeNode node1 = stackl.pop();
            TreeNode node2 = stackr.pop();
            if(node1 == null && node2 == null) continue;
            if(node1 == null || node2 == null || node1.val != node2.val) return false;
            stackl.push(node1.left);
            stackl.push(node1.right);
            stackr.push(node2.right);
            stackr.push(node2.left);
        }
        return true;
    }
    // public boolean isSymmetric(TreeNode root) {
    //     if(root == null) return true;
    //     return isSymmetric(root.left, root.right);
    // }
    // private static boolean isSymmetric(TreeNode left, TreeNode right){
    //     if(left == null && right == null) return true;
    //     if(left == null || right == null || left.val != right.val) return false;
    //     return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    // }
}
