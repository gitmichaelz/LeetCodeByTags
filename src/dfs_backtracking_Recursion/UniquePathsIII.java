package dfs_backtracking_Recursion;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 *     1 representing the starting square. There is exactly one starting square.
 *     2 representing the ending square. There is exactly one ending square.
 *     0 representing empty squares we can walk over.
 *     -1 representing obstacles that we cannot walk over.
 *
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 */
public class UniquePathsIII {
    public int uniquePathsIII(int[][] grid) {
        int empty_slots = 1;//starting point (1) also is an empty_slot, since we can go through it.
        int sx = 0, sy = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) empty_slots++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        return dfs(grid, sx, sy, empty_slots);
    }
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int dfs(int[][] grid, int x, int y, int empty_slots) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == -1) return 0;
        if (grid[x][y] == 2) {
            if (empty_slots == 0) {
                return 1;
            }
            return 0;
        }

        grid[x][y] = -1;
        int total = 0;
        for (int[] dir : dirs) {
            total += dfs(grid, x + dir[0], y + dir[1], empty_slots - 1);
        }
        grid[x][y] = 0;
        return total;
    }
}
