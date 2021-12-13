package array_matrix_sorting;

import java.util.HashMap;

/**
 * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 */
public class ContiguousArray {
    public int findMaxLength(int[] nums) {

        HashMap<Integer, Integer> diffToIndex = new HashMap<Integer, Integer>();
        diffToIndex.put(0,0);
        int result = 0;
        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            int currNum = nums[i];
            if (currNum == 0) {
                diff--;
            } else {
                diff++;
            }

            int tempIndex = diffToIndex.getOrDefault(diff, -1);
            if (tempIndex == -1) {
                diffToIndex.put(diff, i + 1);
            } else {
                result = Math.max(result, i + 1 - tempIndex);
            }
        }

        return result;
    }
}
