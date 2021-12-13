package dfs_backtracking_Recursion;

/**
 * In a gold mine grid of size m x n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 *     Every time you are located in a cell you will collect all the gold in that cell.
 *     From your position, you can walk one step to the left, right, up, or down.
 *     You can't visit the same cell more than once.
 *     Never visit a cell with 0 gold.
 *     You can start and stop collecting gold from any position in the grid that has some gold.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 */
public class PathWithMaximumGold {
    int max;
    public int getMaximumGold(int[][] grid) {
        max = 0;
        if(grid.length == 0)
            return 0;
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[0].length;j++) {
                if(grid[i][j] > 0)
                    calculateMaxPath(grid, i, j, 0);
            }
        }
        return max;
    }

    public void calculateMaxPath(int[][] grid, int i, int j, int sum) {
        if(i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == 0) {
            if(sum > max)
                max = sum;
            return;
        }
        int val = grid[i][j];
        grid[i][j] = 0;
        calculateMaxPath(grid, i+1, j, sum+val);
        calculateMaxPath(grid, i, j+1, sum+val);
        calculateMaxPath(grid, i-1, j, sum+val);
        calculateMaxPath(grid, i, j-1, sum+val);
        grid[i][j] = val;
    }
}
