package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the answer must have Node.val == 0.
 *
 * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any order.
 *
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 7
 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 */
public class AllPossibleFullBinaryTrees {
    //https://leetcode.com/problems/all-possible-full-binary-trees/discuss/216853/Java%3A-Easy-with-Examples
    Map<Integer, List<TreeNode>> map = new HashMap<>();//because there are some repeating computation, we need a map to store result
    public List<TreeNode>  allPossibleFBT(int N) {
        if(N < 0 || N % 2 == 0) return new ArrayList<>();
        List<TreeNode> res = new ArrayList<>();

        if(map.containsKey(N)) return map.get(N);

        if(N == 1) {
            res.add(new TreeNode(0));
        }
        //number of left subtree nodes could from 1 to N - 2, since we must ensure the number of left/right subtree is even number
        //not considering root, the total number of subtree is N - 1, so the number from each side must be (1, 3, 5,... N - 2)
        //左子树数目最少为1，最多为总数 - 1(root) - 1(right)
        for(int i = 1; i <= N - 1; i += 2){//since its a fbt, so we need to increase 2 each time
            List<TreeNode> leftSubtree = allPossibleFBT(i);
            List<TreeNode> rightSubtree = allPossibleFBT(N - 1 - i);
            for(TreeNode left : leftSubtree) {
                for(TreeNode right : rightSubtree) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        map.put(N, res);
        return res;
    }
}
