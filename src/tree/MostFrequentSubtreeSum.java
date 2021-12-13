package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values with the highest frequency in any order.
 *
 * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself).
 *
 *
 *
 * Example 1:
 *
 * Input: root = [5,2,-3]
 * Output: [2,-3,4]
 */
public class MostFrequentSubtreeSum {
    int maxCount = 0;
    int count = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        subTreeSum(root);
        int[] res = new int[count];
        int idx = 0;
        for(int key : map.keySet()){
            if(map.get(key) == maxCount){
                res[idx++] = key;
            }
        }
        return res;
    }

    private int subTreeSum(TreeNode root){
        if(root == null) return 0;
        int sum = root.val + subTreeSum(root.left) + subTreeSum(root.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        if(map.get(sum) > maxCount) {
            maxCount = map.get(sum);
            count = 1;
        } else if(map.get(sum) == maxCount){
            count++;
        }
        return sum;
    }
}
