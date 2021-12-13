package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return true if you can partition the tree into two trees with equal sums of values after removing exactly one edge on the original tree.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [5,10,10,null,null,2,3]
 * Output: true
 */
public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        int sum = getSum(root, list);
        if(sum % 2 != 0) return false;
        list.remove(list.size() - 1);//in case of 0, -1, 1 wihch is false, we need to remove the sum of whole tree to make sure the result is correct
        return list.contains(sum / 2);
    }
    private int getSum(TreeNode root, List<Integer> list){
        if(root == null) return 0;
        int sum = root.val + getSum(root.left, list) + getSum(root.right, list);
        list.add(sum);
        return sum;
    }
}