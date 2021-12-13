package dp;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 * Output: 7
 * Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 */
public class MinimumPathSum {
    //dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1])
    //这个题用一维数组时，注意初始化第一个和第一行；
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] dp = new int[grid[0].length];
        dp[0] = grid[0][0];//初始化第一个。
        for(int j = 1; j < dp.length; j++) {//初始化第一行。
            dp[j] = grid[0][j] + dp[j - 1];
        }
        for(int i = 1; i < grid.length; i++) {
            dp[0] += grid[i][0];//需要更新每一行的dp[0]，即第一个元素，grid[i][0]
            for(int j = 1; j < grid[0].length; j++) {
                dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
            }
        }
        return dp[dp.length - 1];
    }
}
