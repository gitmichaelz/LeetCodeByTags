package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 */
public class ConstructBinaryTreeFromPreporderAndInorderTraversal {
    //first one in preorder array is root, then we can find left subtree from inorder array
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder == null || inorder == null || preorder.length != inorder.length) return null;
        Map<Integer, Integer> map = new HashMap<>();//mapping from inorder's value to its index
        for(int i = 0; i < inorder.length; i++){
            map.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, map);
    }

    public static TreeNode build(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight, Map<Integer, Integer> map) {
        if(preLeft > preRight || inLeft > inRight) return null;
        TreeNode root = new TreeNode(preorder[preLeft]);
        int index = map.get(root.val);
        root.left = build(preorder, inorder, preLeft + 1, preLeft + index - inLeft, inLeft, index - 1, map);
        root.right = build(preorder, inorder, preLeft + index - inLeft + 1, preRight, index + 1, preRight, map);
        return root;
    }
}
