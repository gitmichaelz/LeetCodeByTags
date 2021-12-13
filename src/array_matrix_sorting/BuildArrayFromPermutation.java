package array_matrix_sorting;

/**
 * Given a zero-based permutation nums (0-indexed), build an array ans of the same length where ans[i] = nums[nums[i]] for each 0 <= i < nums.length and return it.
 *
 * A zero-based permutation nums is an array of distinct integers from 0 to nums.length - 1 (inclusive).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,2,1,5,3,4]
 * Output: [0,1,2,4,5,3]
 * Explanation: The array ans is built as follows:
 * ans = [nums[nums[0]], nums[nums[1]], nums[nums[2]], nums[nums[3]], nums[nums[4]], nums[nums[5]]]
 *     = [nums[0], nums[2], nums[1], nums[5], nums[3], nums[4]]
 *     = [0,1,2,4,5,3]
 */
public class BuildArrayFromPermutation {
    //O(1) place, means we need to use the given array to let nums[i] = nums[nums[i]]
    //there might be cases that when we use nums[i] and its value get replaced/overwrite.
    //so we need to figure out a way to store both the original value of nums[i] and the calculated value nums[nums[i - 1]]
    //we can use x = a + b * n to store two values, to get a, let a = x % n, to get b, let b = x / n
    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] + n * (nums[nums[i]] % n);//b = nums[nums[i]] % n, to get previosu value of nums[i].
        }

        for (int i = 0; i < n; i++) {
            nums[i] /= n;
        }
        return nums;
    }
}
