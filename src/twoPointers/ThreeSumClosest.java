package twoPointers;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 *
 * Return the sum of the three integers.
 *
 * You may assume that each input would have exactly one solution.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Example 2:
 *
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 *     3 <= nums.length <= 1000
 *     -1000 <= nums[i] <= 1000
 *     -104 <= target <= 104
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return Integer.MAX_VALUE;
        Arrays.sort(nums);//先排序
        int closest = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;//skip duplicates
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == target){
                    return sum;
                } else if(sum < target){
                    left++;
                    while(left < right && nums[left] == nums[left - 1]) left++;
                } else {
                    right--;
                    while(left < right && nums[right] == nums[right + 1]) right--;
                }
                closest = Math.abs(sum - target) < Math.abs(closest - target)? sum : closest;
            }
        }
        return closest;
    }
}
