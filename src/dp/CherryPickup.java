package dp;

/**
 * You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.
 *
 *     0 means the cell is empty, so you can pass through,
 *     1 means the cell contains a cherry that you can pick up and pass through, or
 *     -1 means the cell contains a thorn that blocks your way.
 *
 * Return the maximum number of cherries you can collect by following the rules below:
 *
 *     Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
 *     After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
 *     When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
 *     If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
 * Output: 5
 * Explanation: The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 */
public class CherryPickup {
    //注意这题不能用greedy,即不能用先从头到尾走一遍取最优，再从尾到头再走一遍取最优，这两个局部最优加起来不一定是全局最优，例如这个例子
    //11100
    //00101
    //10100
    //00100
    //00111
    //这题的思路是：从(0,0)到(n - 1, n - 1)再返回到(0, 0)的最优解，实际上等同于两个人同时(俩人同时每次各走一步)从(0，0)走到(n - 1, n - 1)的最优解。只不过俩人在相同cell里时，只能有一人pick up cherry.
    //那这样在某一时刻，会有四个可能的状态(每个人有两个选择，向下或者向右)。又因为我们总是有 r1 + c1 == r2 + c2,所以实际上可以简化为三个状态，因为一个坐标总会有其他三个求出来。
    //所以我们有dp[r1][c1][c2]: 代表两个人分别从[r1, c1][r2, c2]同时到(n - 1, n - 1)能捡到的最多的cherries.或者理解为原题意。即一个人先从(0, 0)到(n - 1, n - 1)然后再返回到原点所摘做多的cherries数目。
    //最终dp[0][0][0]即使我们所要的结果。
    //dp[r1][c1][c2] = grid[r1][c1] + grid[r2][c2] + nextBest. (if two person not in same cell, if yes, we only collect the cherry once)
    //nextBest = Math.max(dp[r1 + 1][c1][c2], dp[r1][c1 + 1][c2], dp[r1 + 1][c1][c2 + 1], dp[r1][c1 + 1][c2 + 1])
    //O(N^3)
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][n];
        return Math.max(0, collect(grid, dp, 0, 0, 0));
    }

    private int collect(int[][] grid, int[][][] dp, int r1, int c1, int c2) {
        int r2 = r1 + c1 - c2;
        // check if current state is out of bounds or on thorns
        if(r1 >= grid.length || r2 >= grid.length || c1 >= grid.length || c2 >= grid.length ||
                grid[r1][c1] == -1 || grid[r2][c2] == -1) {//千万不要忘记判断当前cell是否是thorns
            return Integer.MIN_VALUE;//current state should not be included in the solution
        }

        if(dp[r1][c1][c2] != 0) return dp[r1][c1][c2];//already calculated the best result for this state
        //check if we reached the end state(actually the two person will be at the same end cell)
        if(r1 == grid.length - 1 && c1 == grid.length - 1) return grid[r1][c1];

        int cur = grid[r1][c1];
        if(r1 != r2) {//two person in different cells
            cur += grid[r2][c2];
        }

        int nextBest = Math.max(Math.max(collect(grid, dp, r1 + 1, c1, c2), collect(grid, dp, r1, c1 + 1, c2)),
                Math.max(collect(grid, dp, r1 + 1, c1, c2 + 1), collect(grid, dp, r1, c1 + 1, c2 + 1)));
        dp[r1][c1][c2] = cur + nextBest;
        return dp[r1][c1][c2];
    }


    //3-d dp, n^3
    // public int cherryPickup(int[][] grid) {
    //     int n = grid.length;
    //     int[][][] dp = new int[n][n][n];
    //     for(int i = 0; i < n; i++) {
    //         for(int j = 0; j < n; j++) {
    //             Arrays.fill(dp[i][j], -1);
    //         }
    //     }
    //     dp[0][0][0] = grid[0][0];
    //     for(int r1 = 0; r1 < n; r1++) {
    //         for(int c1 = 0; c1 < n; c1++) {
    //             for(int c2 = 0; c2 < n; c2++) {
    //                 int r2 = r1 + c1 - c2;
    //                 if(r2 < 0 || r2 >= n || grid[r1][c1] == -1 || grid[r2][c2] == -1) continue;
    //                 int max = Integer.MIN_VALUE;
    //                 if(r1 > 0) max = Math.max(dp[r1 - 1][c1][c2], max);
    //                 if(c1 > 0) max = Math.max(dp[r1][c1 - 1][c2], max);
    //                 if(r1 > 0 && c2 > 0) max = Math.max(dp[r1 - 1][c1][c2 - 1], max);
    //                 if(c1 > 0 && c2 > 0) max = Math.max(dp[r1][c1 - 1][c2 - 1], max);
    //                 if(max < 0) continue;
    //                 dp[r1][c1][c2] = grid[r1][c1] + max;
    //                 if(r1 != r2) {
    //                     dp[r1][c1][c2] += grid[r2][c2];
    //                 }
    //             }
    //         }
    //     }
    //     return Math.max(0, dp[n - 1][n - 1][n - 1]);
    // }
}
