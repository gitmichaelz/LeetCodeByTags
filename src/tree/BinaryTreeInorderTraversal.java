package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 */
public class BinaryTreeInorderTraversal {
    //这个题，三种方法必须掌握，尤其是morris遍历，注意morris遍历只适用于inorder traversal

    // public List<Integer> inorderTraversal(TreeNode root){
    //     List<Integer> res = new ArrayList<>();
    //     Deque<TreeNode> stack = new ArrayDeque<>();
    //     while(root != null || !stack.isEmpty()){
    //         if(root != null) {
    //             stack.push(root);
    //             root = root.left;
    //         } else {
    //             root = stack.pop();
    //             res.add(root.val);
    //             root = root.right;
    //         }
    //     }
    //     return res;
    // }


    //morris traverse
    //the right null pointer in the left subtree points to cur root when we do inorder traversal,
    public List<Integer> inorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        while(root != null){
            if(root.left != null){
                TreeNode p = root.left;
                while(p.right != null && p.right != root){
                    p = p.right;
                }
                if(p.right == null){
                    p.right = root;
                    root = root.left;
                    continue;
                } else {//we've done with left subtree
                    p.right = null;
                }
            }
            res.add(root.val);
            root = root.right;
        }
        return res;
    }


    // public List<Integer> inorderTraversal(TreeNode root) {
    //     List<Integer> res = new ArrayList<>();
    //     inorderTraversalRecursive(root, res);
    //     return res;
    // }
    // private void inorderTraversalRecursive(TreeNode root, List<Integer> list) {
    //     if(root == null) return;
    //     inorderTraversalRecursive(root.left, list);
    //     list.add(root.val);
    //     inorderTraversalRecursive(root.right, list);
    // }
}
