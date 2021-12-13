package tree;

/**
 * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 * Output: 4
 *
 * Example 2:
 *
 * Input: root = [1], target = 4.428571
 * Output: 1
 */
public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while(root != null){
            if(Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if(root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return closest;
    }
}
