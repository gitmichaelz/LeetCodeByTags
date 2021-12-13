package array_matrix_sorting;

/**
 * Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.
 *
 * The test cases are generated so that the answer can fit in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], target = 4
 * Output: 7
 * Explanation:
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * Note that different sequences are counted as different combinations.
 */
public class CombinationSumIV {
    //dp[i] represent for target i, the total number of combination
    //for each num in nums, we can either exclude or include it(if target i >= num)
    //dp[i] = dp[i] (exclude current nums[j]) dp[i] += dp[i - nums[j]] (include current nums[j])
    public int combinationSum4(int[] nums, int target) {
        if(target == 0) return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;//initialize dp[0] = 1 because we need to handle the base case like when i == nums[j]
        for(int i = 1; i <= target; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(i >= nums[j]) {//we can include it
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
