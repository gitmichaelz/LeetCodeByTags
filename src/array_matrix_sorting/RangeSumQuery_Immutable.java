package array_matrix_sorting;

/**
 * Given an integer array nums, handle multiple queries of the following type:
 *
 *     Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 *
 * Implement the NumArray class:
 *
 *     NumArray(int[] nums) Initializes the object with the integer array nums.
 *     int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 */
public class RangeSumQuery_Immutable {
    class NumArray {
        int[] preSum;
        public NumArray(int[] nums){
            preSum = nums;
            for(int i = 1; i < preSum.length; i++){
                preSum[i] += preSum[i - 1];
            }
        }

        public int sumRange(int i, int j){
            return i == 0? preSum[j] : preSum[j] - preSum[i - 1];
        }
    }
}
