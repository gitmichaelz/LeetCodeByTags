package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder == null || postorder == null || inorder.length != postorder.length) return null;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    public static TreeNode build(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight, Map<Integer, Integer> map) {
        if(inLeft > inRight || postLeft > postRight) return null;
        TreeNode root = new TreeNode(postorder[postRight]);
        int index = map.get(root.val);//to find the root position in inorder array
        root.left = build(inorder, inLeft, index - 1, postorder, postLeft, postLeft + index - inLeft - 1, map);
        root.right = build(inorder, index + 1, inRight, postorder, postLeft + index - inLeft, postRight - 1, map);
        return root;
    }
}
