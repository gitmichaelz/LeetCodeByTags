package dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 *
 * Example 1:
 *
 * Input: m = 3, n = 7
 * Output: 28
 */
public class UniquePaths {
    //dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    //dp[0][0] = 1;
    //2d - 1d, dp[j] += dp[j - 1];
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;//第一个位置初始化为1， 只有一种情况.//实际上，放在2d里来看的话，我们把所有的dp[i][0] = 1.即第一列初始化为1
        for(int i = 0; i < m; i++) {
            for(int j = 1; j < n; j++) {//为什么j = 1开始，因为j = 0的时候也是只有一种情况。dp[0] = 1已经初始化过了。
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }
    /**
     Now, you may wonder whether we can further reduce the memory usage to just O(1) space since the above code seems to use only two variables (cur[j] and cur[j - 1]). However, since the whole row cur needs to be updated for m - 1 times (the outer loop) based on old values, all of its values need to be saved and thus O(1)-space is impossible. However, if you are having a DP problem without the outer loop and just the inner one, then it will be possible.
     */
}
