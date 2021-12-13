package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 */
public class SumOfLeftLeaves {
    //for each node in the tree we check whether its left child is a leaf. If it is true, we add its value to answer, otherwise add left child to the stack to process it later. For right child we add it to stack only if it is not a leaf.
    public int sumOfLeftLeaves(TreeNode root){
        if(root == null) return 0;
        int res = 0;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            if(root.left != null) {
                if(root.left.left == null && root.left.right == null) {
                    res += root.left.val;
                } else {
                    stack.push(root.left);
                }
            }
            if(root.right != null) {
                if(root.right.left != null || root.right.right != null) {
                    stack.push(root.right);//we only push root.right when root.right has non empty child
                }
            }
        }
        return res;
    }
    // //For given node we check whether its left child is a leaf. If it is the case, we add its value to answer, otherwise recursively
    // // call method on left child. For right child we call method only if it has at least one nonnull child.
    // public int sumOfLeftLeaves(TreeNode root){
    //     if(root == null) return 0;
    //     int res = 0;
    //     if(root.left != null){
    //         if(root.left.left == null && root.left.right == null) {
    //             res += root.left.val;
    //         } else {
    //             res += sumOfLeftLeaves(root.left);
    //         }
    //     }
    //     res += sumOfLeftLeaves(root.right);
    //     return res;
    // }
}
