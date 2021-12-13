package tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 */
public class BinaryTreePostorderTraversal {
    //another version for postoreder, much better
    //实际上是一种reverse添加preorder，root, right, left, 然后逆序添加到结果集
    public List<Integer> postorderTraversal(TreeNode root){
        LinkedList<Integer> res = new LinkedList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            if(root == null) continue;
            res.addFirst(root.val);
            stack.push(root.left);
            stack.push(root.right);
        }
        return res;
    }
    // public List<Integer> postorderTraversal(TreeNode root){
    //     List<Integer> res = new ArrayList<>();
    //     Deque<TreeNode> stack = new LinkedList<>();
    //     TreeNode visited = null;
    //     while(root != null || !stack.isEmpty()) {
    //         if(root != null) {
    //             stack.push(root);
    //             root = root.left;
    //         } else {
    //             TreeNode cur = stack.peek();
    //             //suppose we already done with a node's right leaf, when we go back to the leaf's parent, we actually visited the parent twice, so we need a visited variable to mark if right child is visited
    //             if(cur.right != null && cur.right != visited) {//since we go back to parent node twice, and check its right child twice, we need extra visited check
    //                 root = cur.right;
    //             } else {
    //                 stack.pop();
    //                 res.add(cur.val);
    //                 visited = cur;
    //             }
    //         }
    //     }
    //     return res;
    // }


    // public List<Integer> postorderTraversal(TreeNode root) {
    //     List<Integer> res = new ArrayList<>();
    //     helper(root, res);
    //     return res;
    // }
    // public void  helper(TreeNode root, List<Integer> res){
    //     if(root == null) return;
    //     helper(root.left, res);
    //     helper(root.right, res);
    //     res.add(root.val);
    // }
}
