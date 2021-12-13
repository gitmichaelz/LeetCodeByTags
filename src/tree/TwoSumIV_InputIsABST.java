package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 */
public class TwoSumIV_InputIsABST {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> inOrder = new ArrayList<Integer>();
        getInOrder(root, inOrder);

        int i = 0;
        int j = inOrder.size()-1;
        while (i < j){
            int sum = inOrder.get(i) + inOrder.get(j);
            if (sum == k){
                return true;
            } else if (sum < k){
                i++;
            } else{
                j--;
            }
        }
        return false;
    }
    public void getInOrder(TreeNode root, List<Integer> list){
        if (root == null){
            return;
        }
        getInOrder(root.left, list);
        list.add(root.val);
        getInOrder(root.right, list);
    }
//     public boolean findTarget(TreeNode root, int k){
//         Set<Integer> set = new HashSet<>();
//         return dfs(root, set, k);
//     }
//     private boolean dfs(TreeNode root, Set<Integer> set, int k){
//         if(root == null) return false;
//         if(set.contains(k - root.val)) return true;
//         set.add(root.val);
//         return dfs(root.left, set, k) || dfs(root.right, set, k);
//     }
}
