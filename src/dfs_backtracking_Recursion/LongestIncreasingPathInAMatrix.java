package dfs_backtracking_Recursion;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 */
public class LongestIncreasingPathInAMatrix {
    //dfs + memoization
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];//cache[i][j] stores the maxLen starting from position [i][j];
        int maxLen = 1;//坑，这里是1， 不是0(错了两遍了！！！)
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int len = dfs(matrix, i, j, m, n, cache);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    //longest path starting from i, j; for example, 原题中例一，matrix[1][0] = 6
    //cache[1][0] = 2, (6, 9, 总长是2)，当我们从最后一行中的2找到6的位置(1， 0)，可以直接用cache[1][0] = 2的值
    private int dfs(int[][] matrix, int i, int j, int m, int n, int[][] cache) {
        if(cache[i][j] != 0) return cache[i][j];
        int maxLen = 1;//坑, 这里是1， 不是0
        for(int[] dir : dirs) {
            int x = i + dir[0];
            int y = j  + dir[1];
            if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[i][j]) continue;// skip invalid cells
            int len = 1 + dfs(matrix, x, y, m, n, cache);//len 分别是从i. j出发的四个方向的长度。所以我们要用一个max来记录最大的
            maxLen = Math.max(maxLen, len);
        }
        return cache[i][j] = maxLen;
    }
}
