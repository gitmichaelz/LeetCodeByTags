package dfs_backtracking_Recursion;

/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 */
public class SwimInRisingWater {
    //binary search + DFS
    //we are guessing the best time from lo = grid[0][0] to hi = N * N - 1 using binary search;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0];//we need to start from [0][0], so the low boundary of time is grid[0][0]
        int hi = N * N - 1;//we may cover the maximum elevation in our route
        while(lo <= hi) {//since we are always finding a shorter time, so when lo == hi, and find a time, so hi--, then break while, so the lo is the result to return
            int guessTime = lo + (hi - lo)/2;
            boolean[][] visited = new boolean[N][N];//for each time, we mark which position are visited when do DFS
            if(reachToEnd(visited, grid, 0, 0, guessTime)){
                hi = guessTime - 1;// if we can reach to the end under guessTime, then we check if there is a faster path
            } else {
                lo = guessTime + 1;// if we cannot reach to the end under guessTime, then increase the time until we find one
            }
        }
        return lo;
    }
    //if position grid[nextI][nextJ] <= curTime, means we can swim through it. and try its four neighbours
    private boolean reachToEnd(boolean[][] visited, int[][] grid, int i, int j, int curTime){
        int len = visited.length;
        if(i == len - 1 && j == len - 1) return true;
        visited[i][j] = true;
        for(int k = 0; k < directions.length; k++) {
            int nextI = i + directions[k][0], nextJ = j + directions[k][1];
            if(nextI >= 0 && nextI < len && nextJ >= 0 && nextJ < len && !visited[nextI][nextJ] && grid[nextI][nextJ] <= curTime) {
                if(reachToEnd(visited, grid, nextI, nextJ, curTime)) return true;
            }
        }
        return false;
    }
}
