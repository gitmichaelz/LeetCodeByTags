package Google;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down,
 * left, or right from and to an empty cell in one step.
 *
 * Return the minimum number of steps to walk fro m the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
 * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * Example 1:
 *
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 */
public class ShortestPathInAGridWithObstaclesElimination {
    //BFS, but we need to store the number of eliminitated
    int[][] dir = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        if(m == 0 || n == 0) return 0;

        int[][] obstacle = new int[m][n]; // min Number of obstacles encountered.
        boolean[][] visited = new boolean[m][n];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0});// x,y,obstacleCount
        obstacle[0][0] = 0; // Initially we eliminated 0 obstacles.
        int step = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] p = queue.poll();
                if(p[0] == m-1 && p[1] == n-1) {
                    return step - 1;
                }
                int currObstacleCount = p[2];
                for(int[] d : dir) {
                    int x = p[0]+d[0];
                    int y = p[1]+d[1];
                    if(!(x >= 0 && y >= 0 && x < m && y < n)) continue;

                    int oldObstacleCount = obstacle[x][y];
                    int newObstacleCount = currObstacleCount + (grid[x][y]== 1 ? 1 : 0);
				/*Add the next element to the queue if it has not been visited yet or it has
                been visited but the number of obstacles encountered are greater than the new path,
                hence we can replace it with the current path.*/
                    if ((oldObstacleCount > newObstacleCount || !visited[x][y]) && newObstacleCount <= k ) {
                        queue.add(new int[] {x, y, newObstacleCount});
                        obstacle[x][y] = newObstacleCount;
                        visited[x][y] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
