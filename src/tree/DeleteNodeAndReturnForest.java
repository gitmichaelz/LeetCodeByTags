package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 *
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 *
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 */
public class DeleteNodeAndReturnForest {
    //any tree problem, first thing is using recursion
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for(int val : to_delete) {
            set.add(val);
        }
        List<TreeNode> res = new ArrayList<>();
        root = process(root, set, res);
        if(root != null) res.add(root);
        return res;
    }
    private TreeNode process(TreeNode root, Set<Integer> set, List<TreeNode> res) {
        if(root == null) return null;
        root.left = process(root.left, set, res);
        root.right = process(root.right, set, res);
        if(set.contains(root.val)) {
            if(root.left != null) res.add(root.left);
            if(root.right != null) res.add(root.right);
            return null;
        } else {
            return root;
        }
    }
}
