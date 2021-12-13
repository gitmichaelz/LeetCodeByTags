package tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 */
public class BinaryTreePreorderTraversal {
    //another way do preorder
    //time: O(N), space: O(lgN)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            if(root == null) continue;
            res.add(root.val);
            stack.push(root.right);
            stack.push(root.left);

        }
        return res;
    }
    // public List<Integer> preorderTraversal(TreeNode root) {
    //     List<Integer> res = new ArrayList<>();
    //     Deque<TreeNode> stack = new LinkedList<>();
    //     while(root != null || !stack.isEmpty()) {
    //         if(root != null){
    //             res.add(root.val);
    //             stack.push(root);
    //             root = root.left;
    //         } else {
    //             root = stack.pop();
    //             root = root.right;
    //         }
    //     }
    //     return res;
    // }

    //recursive
    // public List<Integer> preOrderTraversal(TreeNode root){
    //     List<Integer> res = new ArrayList<>();
    //     helper(root, res);
    //     return res;
    // }
    // private void helper(TreeNode root, List<Integer> res){
    //     if(root == null) return;
    //     res.add(root.val);
    //     helper(root.left, res);
    //     helper(root.right, res);
    // }
}
