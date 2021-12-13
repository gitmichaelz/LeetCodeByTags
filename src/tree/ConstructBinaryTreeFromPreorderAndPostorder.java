package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
 *
 * If there exist multiple answers, you can return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 */
public class ConstructBinaryTreeFromPreorderAndPostorder {
    //use a map to store the index of each number in postorder array
    //so that when we build tree from pre array, we know the range of its left/right subtree
    public TreeNode constructFromPrePost(int[] pre, int[] post){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return construct(pre, 0, pre.length - 1, post, 0, post.length - 1, map);
    }

    private TreeNode construct(int[] pre, int preLeft, int preRight, int[] post, int postLeft, int postRight, Map<Integer, Integer> map) {
        if(preLeft > preRight || postLeft > postRight) return null;
        TreeNode root = new TreeNode(pre[preLeft++]);
        if(preLeft <= preRight) {//dont forget this check!!!! or we will get EXCEPTION while do map.get(pre[preLeft])
            int idx = map.get(pre[preLeft]);//find the left subtree's range according to its postion in post array
            int deltaIdx = idx - postLeft;
            root.left = construct(pre, preLeft, preLeft + deltaIdx, post, postLeft, postLeft + deltaIdx, map);
            root.right = construct(pre, preLeft + deltaIdx + 1, preRight, post, postLeft + deltaIdx + 1, postRight - 1, map);
        }
        return root;
    }
}
