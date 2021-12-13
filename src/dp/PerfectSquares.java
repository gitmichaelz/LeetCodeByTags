package dp;

import java.util.Arrays;

public class PerfectSquares {
    //notice that dp[0] = 0
    // dp[1] = dp[0] + 1 = 1
    // dp[2] = dp[1] + 1 = 2
    // dp[3] = dp[2] + 1 = 3
    // dp[4] = min(dp[4 - 1 * 1] + 1, dp[4 - 2 * 2] + 1) = min(dp[3] + 1, dp[0] + 1)
    // dp[n] = min(dp[n - i * i] + 1), n - i * i >= 0
    public int numSquares(int n) {
        if(n == 0) return 0;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for(int i = 1; i * i <=n; i++) {
            for(int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);//here + 1 means i * i used 1 square, we need to compute remaining dp[j - i * i]
            }
        }
        return dp[n];
    }
}
