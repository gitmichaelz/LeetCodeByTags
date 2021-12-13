package dp;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 */
public class HouseRobberII {
    //we already know how to rob in a array house robberI, in house robber ii, houses are arranged in a circle, so we just need to calculate [0, n - 2] and [1, n - 1], and find the bigger one of them
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int startsWith_0 = rob(nums, 0, nums.length - 2);
        int startsWith_1 = rob(nums, 1, nums.length - 1);
        return Math.max(startsWith_0, startsWith_1);
    }

    private int rob(int[]nums, int start, int end) {
        int a = 0, b = nums[start++];
        while(start <= end) {
            int cur = Math.max(a + nums[start++], b);
            a = b;
            b = cur;
        }
        return b;
    }
}
