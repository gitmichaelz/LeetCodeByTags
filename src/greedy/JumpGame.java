package greedy;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 *
 * Return true if you can reach the last index, or false otherwise.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * Constraints:
 *
 *     1 <= nums.length <= 104
 *     0 <= nums[i] <= 105
 */
public class JumpGame {
    //we mark the maxRange for each position can reach, and update the maxRange when we scanning all positions
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int maxRange = 0;
        for(int i = 0; i <= maxRange && i < nums.length; i++){//important!!! i can not be beyond the maxRange it can reach
            maxRange = Math.max(maxRange, nums[i] + i);
        }
        return maxRange >= nums.length - 1;
    }
}
