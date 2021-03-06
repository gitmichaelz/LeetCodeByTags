package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called root.
 *
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that all houses in this place form a binary tree. It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Given the root of the binary tree, return the maximum amount of money the thief can rob without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [3,2,3,null,3,null,1]
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 */
public class HouseRobberIII {
    //at any node, we have two options, either we include the cur root, or exclude the cur root,
    //since there are many repetitive calculation, so we use a map to cache the calculated nodes
    Map<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root){
        if(root == null) return 0;
        if(!map.containsKey(root)) {
            int excludeRoot = rob(root.left) + rob(root.right);
            int includeRoot = root.val;
            if(root.left != null){
                includeRoot +=  (rob(root.left.left) + rob(root.left.right));
            }
            if(root.right != null){
                includeRoot +=  (rob(root.right.left) + rob(root.right.right));
            }
            map.put(root, Math.max(excludeRoot, includeRoot));
        }
        return map.get(root);
    }


    //=======================================

    public int rob1(TreeNode root) {
        int[] res = helper(root);
        return Math.max(res[0], res[1]);
    }
    public int[] helper(TreeNode root) {
        if (root == null)
            return new int[2];
        int[] left  = helper(root.left);
        int[] right = helper(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]); // not rob the current house
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
