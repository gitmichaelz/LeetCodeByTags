package dp;

/**
 * An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 *     For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
 *
 * Given an integer array nums, return the number of arithmetic subarrays of nums.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: 3
 * Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
 */
public class ArithmeticSlices {
    //dp[i] is the number of Arithmetic slices ending with A[i],
    //dp[i] = (A[i] - A[i - 1] == A[i - 1] - A[i - 2])? 1 + dp[i - 1] : 0
    //since we are only using either previous value or 0, so we can only use 1 variable to calculate dp[i], and use another
    //variable sum to calculate the total number
    public int numberOfArithmeticSlices(int[] A){
        if(A == null || A.length < 3) return 0;
        int cur = 0;
        int sum = 0;
        for(int i = 2; i < A.length; i++) {
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cur += 1;
                sum += cur;
            } else {
                cur = 0;
            }
        }
        return sum;
    }
}
