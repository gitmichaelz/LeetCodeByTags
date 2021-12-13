package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 *     All the visited cells of the path are 0.
 *     All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 *
 * The length of a clear path is the number of visited cells of this path.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 */
public class ShortestPathInABinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] != 0) return -1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        grid[0][0] = 1;
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            for (int size = q.size(); size > 0; size--) {
                int[] top = q.poll();
                int r = top[0], c = top[1];
                if (r == m - 1 && c == n - 1) return step;
                for (int nr = Math.max(0, r - 1); nr <= Math.min(m - 1, r + 1); nr++) {
                    for (int nc = Math.max(0, c - 1); nc <= Math.min(n - 1, c + 1); nc++) {
                        if (grid[nr][nc] == 0) {
                            grid[nr][nc] = 1;
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
