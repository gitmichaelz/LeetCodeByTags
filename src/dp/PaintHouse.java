package dp;

/**
 * There is a row of n houses, where each house can be painted one of three colors: red, blue, or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x 3 cost matrix costs.
 *
 *     For example, costs[0][0] is the cost of painting house 0 with the color red; costs[1][2] is the cost of painting house 1 with color green, and so on...
 *
 * Return the minimum cost to paint all houses.
 *
 * Example 1:
 *
 * Input: costs = [[17,2,17],[16,16,5],[14,3,19]]
 * Output: 10
 * Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 * Minimum cost: 2 + 5 + 3 = 10.
 */
public class PaintHouse {
    //dp[i][j] is the min cost for first i houses ended with house i and color j
    //dp[i][j] = Math.min(dp[i - 1][!j] + costs[i][j])
    //注意：只需要用到之前房子的颜色，所以只需要两行3列即可。
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int m = costs.length;
        int[][] dp = new int[2][3];
        for(int i = 1; i <= m; i++) {
            dp[i % 2][0] = Math.min(dp[(i - 1) % 2][1], dp[(i - 1) % 2][2]) + costs[i - 1][0];
            dp[i % 2][1] = Math.min(dp[(i - 1) % 2][0], dp[(i - 1) % 2][2]) + costs[i - 1][1];
            dp[i % 2][2] = Math.min(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1]) + costs[i - 1][2];

        }
        return Math.min(Math.min(dp[m % 2][0], dp[m % 2][1]), dp[m % 2][2]);
    }
}
