package dp;

/**
 * ou are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 *
 *     For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 *
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 */
public class TargetSum {
    //s1 - s2 = s;  s1 + s2 = sum;   => 2 * s1 = s + sum; => s1 = (s + sum)/2;
    // so this problem converts to CountOfSubsetSum problem
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for(int i : nums) {
            sum += i;
        }
        if(sum < S || ((sum + S) & 1) == 1) return 0;
        sum = (S + sum)/2;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for(int i = 0; i < nums.length; i++) {
            for(int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[sum];
    }
}
