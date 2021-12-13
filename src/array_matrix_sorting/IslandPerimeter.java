package array_matrix_sorting;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 */
public class IslandPerimeter {
    //对于每一个cell，如果是陆地，为了防止重复计算，则只检查它的左邻居和上邻居
    public int islandPerimeter(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int perimeter = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    perimeter += 4;
                    if(j > 0 && grid[i][j - 1] == 1) perimeter -= 2;//坑，第一遍写的时候把这两个if放到外面if的下面了，应该写在里面
                    if(i > 0 && grid[i - 1][j] == 1) perimeter -= 2;
                }

            }
        }
        return perimeter;
    }
}
