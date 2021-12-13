package dp;

/**
 * You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 *
 *
 * Example 1:
 *
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 */
public class MinCostClimbingStairs {
    //dp[i] is the minCost on stair i
    //dp[i] = Min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1])
    //dp[0] = cost[0], dp[1] = Min(cost[0], cost[1])
    //since we are only use the pre two number for dp, so we will be using two variables to save space
    public int minCostClimbingStairs(int[] cost) {
        if(cost == null || cost.length == 0 || cost.length == 1) return 0;
        if(cost.length == 2) return Math.min(cost[0], cost[1]);
        int a = cost[0], b = cost[1];
        int c = 0;
        for(int i = 2; i < cost.length; i++) {
            c = Math.min(a , b) + cost[i];
            a = b;
            b = c;
        }
        return Math.min(a, b);
    }
}
