package dp;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber {
    //类似fibnacci
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int a = 0, b = nums[0];
        int cur = 0;//cur is the Total value when we get to ith house,NOTE at ith house, we can either rob it or not
        for(int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i] + a, b);//nums[i + a], rob ith house, b, not rob ith hosue
            a = b;
            b = cur;
        }
        return b;
    }
}
