package dp;

/**
 * You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
 *
 *     Every post must be painted exactly one color.
 *     There cannot be three or more consecutive posts with the same color.
 *
 * Given the two integers n and k, return the number of ways you can paint the fence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: All the possibilities are shown.
 * Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
 */
public class PaintFence {
    //dp[i] represents the num of ways ending at post i,
    //at each post i, we have two options: 1. make it different color from i - 1th post  2. make it same color as i - 1th post
    //dp[i] = num_ways_diff(i) + num_ways_same(i) = dp[i - 2] * (k - 1) + dp[i - 1] * (k - 1)
    //                                            = (dp[i - 1] + dp[i - 2]) * (k - 1);
    public int numWays(int n, int k) {
        if(n == 0) return 0;
        if(n == 1) return k;
        if(n == 2) return k * k;
        int a = k;
        int b = k * k;
        int cur;
        for(int i = 3; i <= n; i++) {
            cur = (a + b) * (k - 1);
            a = b;
            b = cur;
        }
        return b;
    }
}
