package dp;

/**
 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
 *
 * The cost of painting each house with a certain color is represented by an n x k cost matrix costs.
 *
 *     For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on...
 *
 * Return the minimum cost to paint all houses.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [[1,5,3],[2,9,4]]
 * Output: 5
 * Explanation:
 * Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
 * Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
 */
public class PaintHouseII {
    //dp[i][j] is min cost ending with house i and color j
    //dp[i][j] = Math.min(any k!= j| dp[i-1][k]) + costs[i][j]
    //Take a closer look at the formula, we don't need an array to represent dp[i][j], we only need to know the min cost to
    //the previous house of any color and if the color j is used on previous house to get prev min cost, use the second min
    //cost that are not using color j on the previous house. So I have three variable to record: prevMinCost, preColor, prevSecMinCost.
    //and the above formula will be translated into: dp[currentHouse][currentColor] = (currentColor == preColor? prevSecMinCost: preMinCost) + costs[currentHouse][currentColor].
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        if(k == 1) return n == 1? costs[0][0] : -1;
        int preMinCost = 0, preSecMinCost = 0, preColor = -1;
        for(int i = 0; i < n; i++) {
            //for each row, we need to find out min, secMin, color
            int min = Integer.MAX_VALUE, color = -1, secMin = Integer.MAX_VALUE;
            for(int j = 0; j < k; j++) {
                int cost = costs[i][j] + (j == preColor? preSecMinCost : preMinCost);//dont forget ()
                if(cost < min) {
                    secMin = min;
                    min = cost;
                    color = j;
                } else if(cost < secMin) {
                    secMin = cost;
                }
            }
            preMinCost = min;
            preSecMinCost = secMin;
            preColor = color;
        }
        return preMinCost;
    }
}
