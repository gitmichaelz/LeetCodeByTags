package dp;

/**
 * Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,1]
 * Explanation:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 */
public class CountingBits {
    //dp[i] represent the current number of digit 1's of num[i]
    //dp[i] = dp[i >> 1] + i&1  dp[i >> 1] is a value which is already calculated i & 1 to check the last digit
    public int[] countBits(int num) {
        int[] dp = new int[num+ 1];
        for(int i = 1; i <= num; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }
}
