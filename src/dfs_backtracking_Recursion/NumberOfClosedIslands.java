package dfs_backtracking_Recursion;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 *
 * Return the number of closed islands.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 */
public class NumberOfClosedIslands {
    //one pass
    //scan the grid, and once we meet a 0, we set to 1, and check its neighbors, any of its neighbors connected to boundary, is not a closed islands, we can return 0, else return 1
    public int closedIsland(int[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 0) {
                    count += fill(grid, i, j);
                }
            }
        }
        return count;
    }
    //the function will do two things:
    //1. to fill the cell 0 to 1
    //2, return if cur island is closed or not by checking its neighbors if connected to boundary, if yes return 1, if not return 0
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int fill(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;//connected to boundary
        if(grid[i][j] == 1) return 1;//already filled
        grid[i][j] = 1;
        int res = 1;
        for(int[] dir : dirs) {
            res *= fill(grid, i + dir[0], j + dir[1]);//any neighbors connected to boundary make cur island not closed
        }
        return res;
    }



    //first fill all island that connected to the boundary of grid,
    //then count the remaining islands(which must be closed islands)
//     public int closedIsland(int[][] grid) {
//         int count = 0;
//         for(int i = 0; i < grid.length; i++) {
//             for(int j = 0; j < grid[0].length; j++) {
//                 if(i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1) {
//                     fill(grid, i, j);
//                 }
//             }
//         }
//         for(int i = 0; i < grid.length; i++) {
//             for(int j = 0; j < grid[0].length; j++) {
//                 if(grid[i][j] == 0) {
//                     count++;
//                     fill(grid, i, j);
//                 }
//             }
//         }
//         return count;
//     }

//     int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//     private void fill(int[][] grid, int i, int j) {
//         if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 1) return;
//         grid[i][j] = 1;
//         for(int[] dir : dirs) {
//             fill(grid, i + dir[0], j + dir[1]);
//         }
//     }
}
