package dp;

/**
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 4
 */
public class MaximalSquare {
    //dp[i][j] is the edge of the maximal square ending at position i, j
    //if(matrix[i][j] == '1') dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;//这个一定要自己画图解释
    //if use 2d array dp[m + 1][n + 1], the dummy row and column are 0, which is fine.
    //optimization, if we are using only 2 row of the array, one thing need to mention is that when matrix[i][j] == '0', we need to set dp[i][j] = 0
    public int maximalSquare(char[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int maxEdge = 0;
        int[][] dp = new int[2][n + 1];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(matrix[i - 1][j - 1] == '1') {
                    dp[i % 2][j] = Math.min(Math.min(dp[(i - 1) % 2][j], dp[i % 2][j - 1]), dp[(i - 1) % 2][j - 1]) + 1;
                    maxEdge = Math.max(maxEdge, dp[i % 2][j]);
                } else {
                    dp[i % 2][j] = 0;
                }
            }
        }
        return maxEdge * maxEdge;
    }

    // public int maximalSquare(char[][] matrix) {
    //     if(matrix == null || matrix.length == 0) return 0;
    //     int m = matrix.length;
    //     int n= matrix[0].length;
    //     int maxEdge = 0;
    //     int[][] dp = new int[m + 1][n + 1];
    //     for(int i = 1; i <= m; i++) {
    //         for(int j = 1; j <= n; j++) {
    //             if(matrix[i - 1][j - 1] == '1') {
    //                 dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
    //                 maxEdge = Math.max(maxEdge, dp[i][j]);
    //             }
    //         }
    //     }
    //     return maxEdge * maxEdge;
    // }
}
