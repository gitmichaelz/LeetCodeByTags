package array_matrix_sorting;

/**
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 */
public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes1(int[] nums) {
        int maxLen = 0, curLen = 0;
        for(int num : nums) {
            curLen = num == 0? 0 : curLen + 1;
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int maxLen = 0, curLen = 0;
        for(int num : nums) {
            if(num == 0) {
                curLen = 0;
            } else {
                curLen++;
                maxLen = Math.max(maxLen, curLen);
            }
        }
        return maxLen;
    }
}
