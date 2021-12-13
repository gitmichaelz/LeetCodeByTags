package tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 */
public class LeafSimilarTrees {
    //idea: use stack to dfs traverse tree and keep the path, once we get a leaf, we return it
    //so we can compare the two nodes leaf one by one at same time
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();
        stack1.push(root1);
        stack2.push(root2);
        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            if(getNextLeafValue(stack1) != getNextLeafValue(stack2)) return false;
        }
        return stack1.isEmpty() && stack2.isEmpty();
    }
    //preorder dfs
    private int getNextLeafValue(Deque<TreeNode> stack) {
        while(!stack.isEmpty()) {
            TreeNode root = stack.pop();
            if(root.right != null) stack.push(root.right);
            if(root.left != null) stack.push(root.left);
            if(root.left == null && root.right == null) return root.val;//leaf found, return val
        }
        return -1;
    }
}
