package dp;

/**
 * Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0', return the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.
 *
 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since it is too strong to be destroyed.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * Output: 3
 */
public class BombEnemy {
    //at start of each row and column, we count the number of hits, remember: if we get a 'w', we need to recalculate
    //time: O(MN),
    public int maxKilledEnemies(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[] cols = new int[n];
        int max = 0, rowHits = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(j == 0 || grid[i][j - 1] == 'W') {
                    rowHits = 0;
                    for(int k = j; k < n && grid[i][k] != 'W'; k++) {
                        rowHits += grid[i][k] == 'E'? 1 : 0;
                    }
                }
                if(i == 0 || grid[i - 1][j] == 'W') {
                    cols[j] = 0;//reset to 0, recalculate
                    for(int k = i; k < m && grid[k][j] != 'W'; k++) {
                        cols[j] += grid[k][j] == 'E'? 1 : 0;
                    }
                }
                if(grid[i][j] == '0') {
                    max = Math.max(max, rowHits + cols[j]);
                }
            }
        }
        return max;
    }
}
