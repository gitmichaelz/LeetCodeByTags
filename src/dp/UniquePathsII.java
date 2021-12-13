package dp;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 */
public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;

        if(obstacleGrid[0][0] == 1) return 0;

        int[] dp = new int[obstacleGrid[0].length];
        dp[0] = 1;
        for(int i = 0; i < obstacleGrid.length; i++) {
            for(int j = 0; j < obstacleGrid[0].length; j++) {//跟I不同的是，这里每个点都要check, j = 0开始
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if(j > 0){//不要忘记判断j > 0
                    dp[j] += dp[j - 1];
                }//else if j == 0; dp[j] = dp[j]
            }
        }
        return dp[dp.length - 1];
    }
}
