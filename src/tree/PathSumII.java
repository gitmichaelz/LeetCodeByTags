package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values
 * in the path equals targetSum. Each path should be returned as a list of the node values, not node references.
 *
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        collectNode(root, targetSum, res, new ArrayList<>());
        return res;
    }

    private void collectNode(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
        if(root == null) return;
        list.add(root.val);
        if(root.left == null && root.right == null && root.val == sum) {//一定要注意写成if else,
            res.add(new ArrayList<>(list));
        } else {
            collectNode(root.left, sum - root.val, res, list);
            collectNode(root.right, sum - root.val, res, list);
        }

        list.remove(list.size() - 1);//剪枝。if else里面无论走完哪一个，都需要这个步骤。
    }
}
