package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> res = new ArrayList<>();
        rightView(root, res, 0);
        return res;
    }
    //travese the tree from root to right children, left children.
    //collect the node when res.size() == depth, because each level we are collecting one node, and we traverse the right child first, which can make sure we collect the right side view nodes.
    private void rightView(TreeNode root, List<Integer> res, int depth){
        if(root == null) return;
        if(depth == res.size()){
            res.add(root.val);
        }
        rightView(root.right, res, depth + 1);
        rightView(root.left, res, depth + 1);
    }

    // public List<Integer> rightSideView(TreeNode root) {
    //     List<Integer> res = new ArrayList<>();
    //     if(root == null) return res;
    //     Deque<TreeNode> queue = new LinkedList<>();
    //     queue.offer(root);
    //     while(!queue.isEmpty()) {
    //         int size = queue.size();
    //         for(int i = 0; i < size; i++){
    //             root = queue.poll();
    //             if(i == size - 1){
    //                 res.add(root.val);
    //             }
    //             if(root.left != null){
    //                 queue.offer(root.left);
    //             }
    //             if(root.right != null) {
    //                 queue.offer(root.right);
    //             }
    //         }
    //     }
    //     return res;
    // }
}
