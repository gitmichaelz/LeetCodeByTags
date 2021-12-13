package bfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given an m x n grid where each cell can have one of three values:
 *
 *     0 representing an empty cell,
 *     1 representing a fresh orange, or
 *     2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 */
public class RottingOranges {
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new LinkedList<>();
        int fresh_oranges = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if(grid[i][j] == 1) {
                    fresh_oranges++;
                }
            }
        }
        if(fresh_oranges == 0) return 0;
        int minutes = 0;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!queue.isEmpty()) {
            minutes++;
            for(int size = queue.size(); size > 0; size--) {
                int[] cur = queue.poll();
                for(int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    //if cur cell is invalid or is 0 or rotten, we do nothing
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0 || grid[x][y] == 2) continue;//注意这里等于0, 2的情况也要排除
                    grid[x][y] = 2;//make the cur orange as rotten
                    queue.offer(new int[]{x, y});
                    fresh_oranges--;
                }
            }
        }
        return fresh_oranges > 0? -1 : minutes - 1;
    }
}
