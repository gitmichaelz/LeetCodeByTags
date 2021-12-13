package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, collect a tree's nodes as if you were doing this:
 *
 *     Collect all the leaf nodes.
 *     Remove all the leaf nodes.
 *     Repeat until the tree is empty.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5]
 * Output: [[4,5,3],[2],[1]]
 * Explanation:
 * [[3,5,4],[2],[1]] and [[3,4,5],[2],[1]] are also considered correct answers since per each level it does not matter the order on which elements are returned.
 */
public class FindLeavesOfBinaryTree {
    //For this question we need to take bottom-up approach. The key is to find the height of each node. Here the definition of height is:
    //The height of a node is the number of edges from the node to the deepest leaf. --CMU 15-121 Binary Trees
    //
    //I used a helper function to return the height of current node. According to the definition, the height of leaf is 0. h(node) = 1 + max(h(node.left), h(node.right)).
    //The height of a node is also the its index in the result list (res). For example, leaves, whose heights are 0, are stored in res[0]. Once we find the height of a node, we can put it directly into the result.
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(root, res);
        return res;
    }
    private int getHeight(TreeNode root, List<List<Integer>> res) {
        if(root == null) return -1;
        int height = 1 + Math.max(getHeight(root.left, res), getHeight(root.right, res));
        if(res.size() < height + 1) res.add(new ArrayList<>());
        res.get(height).add(root.val);
        return height;
    }
}
