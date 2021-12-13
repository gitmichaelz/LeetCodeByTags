package tree;

/**
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:
 *
 *     Create a root node whose value is the maximum value in nums.
 *     Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 *     Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 *
 * Return the maximum binary tree built from nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 *     - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 *         - Empty array, so no child.
 *         - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 *             - Empty array, so no child.
 *             - Only one element, so child is a node with value 1.
 *     - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 *         - Only one element, so child is a node with value 0.
 *         - Empty array, so no child.
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    private static TreeNode build(int[] nums, int lo, int hi){
        if(lo > hi) return null;
        int idx = findMaxidx(nums, lo, hi);
        TreeNode root = new TreeNode(nums[idx]);
        root.left = build(nums, lo, idx - 1);
        root.right = build(nums, idx + 1, hi);
        return root;
    }
    //return the max value and its index
    private static int findMaxidx(int[] nums, int lo, int hi){
        int idx = lo++;
        while(lo <= hi) {
            if(nums[idx] < nums[lo]){
                idx = lo;
            }
            lo++;
        }
        return idx;
    }
}
