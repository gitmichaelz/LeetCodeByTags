package dp;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 */
public class BurstBalloons {
    /*
        https://leetcode.com/problems/burst-balloons/discuss/76245/Easiest-Java-Solution
        The subproblems are overlapped. So we can use divide and conquer + cache.

        Balloons 0, 1, ..., n - 1
        What is the max value if we burst all of them [0, n - 1]?
        Let's first consider bursting [start, end]
        Imagine we burst index i at the end
        [start, ... i - 1, (i), i + 1 ... end]
        Before the end, we already bursted [start, i - 1] and [i + 1, end]
        Before the end, boundaries start - 1, i, end + 1 are always there
        This helps us calculate coins without worrying about details inside [start, i - 1] and [i + 1, end]
        So the range can be divided into
        start - 1, maxCoin(start, i - 1), i, maxCoins(i + 1, end), end + 1
        */
    public int maxCoins(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[][] dp = new int[nums.length][nums.length];
        return getMaxCoins(nums, 0, nums.length -1, dp);
    }

    public int getMaxCoins(int[] nums, int start, int end, int[][] dp) {
        //empty
        if(start > end) return 0;
        // if the result is ready, return it directly.
        if(dp[start][end] != 0) return dp[start][end];

        int max = 0;
        for(int i = start; i <= end; i++) {
            int val = getMaxCoins(nums, start, i - 1, dp) + getMaxCoins(nums, i + 1, end, dp)
                    + getNum(nums, start - 1) * nums[i] * getNum(nums, end + 1);
            max = Math.max(max, val);
        }
        dp[start][end] = max;
        return max;
    }

    public int getNum(int[]nums, int i) {
        if(i == -1 || i == nums.length) {
            return 1;
        }
        return nums[i];
    }
}
