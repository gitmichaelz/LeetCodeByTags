package dp;

/**
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {
    //dp[j] = dp[j - 1] + dp[j - 2];
    //so we only need two preceding numbers
    public int climbStairs(int n) {
        if(n <= 2) return n;
        int step1 = 1;
        int step2 = 2;
        int cur = 0;
        for(int i = 3; i <= n; i++) {
            cur = step1 + step2;
            step1 = step2;
            step2 = cur;
        }
        return step2;
    }
}
