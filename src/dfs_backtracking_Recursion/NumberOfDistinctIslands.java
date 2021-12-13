package dfs_backtracking_Recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Return the number of distinct islands.
 *
 *
 */
public class NumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        Set<String> shapes = new HashSet<>();
        for (int i=0; i<grid.length; ++i) {
            for (int j=0; j<grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb, 's');
                    shapes.add(sb.toString());
                }
            }
        }

        return shapes.size();
    }

    private void dfs(int[][] grid, int i, int j, StringBuilder sb, char dir) {
        if (i<0 || i>= grid.length || j<0 || j>= grid[0].length || grid[i][j] == 0) {
            return;
        }

        sb.append(dir);
        grid[i][j] = 0;
        dfs(grid, i-1, j, sb, 'u');
        dfs(grid, i+1, j, sb, 'd');
        dfs(grid, i, j-1, sb, 'l');
        dfs(grid, i, j+1, sb, 'r');
        sb.append('e');
    }
}
