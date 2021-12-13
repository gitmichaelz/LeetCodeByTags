package dp;

/**
 * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
 *
 * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
 *
 * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-2,3,-2]
 * Output: 3
 * Explanation: Subarray [3] has maximum sum 3
 */
public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] A) {
        int maxSum = A[0], minSum = A[0], curMax = 0, curMin = 0;
        int total = 0;
        for(int a : A){
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(curMax, maxSum);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(curMin, minSum);
            total += a;
        }
        //注意，如果maxSum为负数，需要单独拿出来判断，因为这时候一定有total == minSum
        if(maxSum < 0) return maxSum;
        return Math.max(maxSum, total - minSum);
    }
}
