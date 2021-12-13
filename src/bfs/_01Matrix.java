package bfs;

/**
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: [[0,0,0],[0,1,0],[0,0,0]]
 */
public class _01Matrix {
    //dp: O(N^2)
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        if(m == 0 || n == 0) return matrix;
        int range = m + n - 2;//the longest distance wont exceed m * n, actually its m + n - 2;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] != 0) {
                    int upCell = i > 0 ? matrix[i - 1][j] : range;
                    int leftCell = j > 0 ? matrix[i][j - 1] : range;
                    matrix[i][j] = Math.min(upCell, leftCell) + 1;
                }
            }
        }
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                if(matrix[i][j] != 0) {
                    int downCell = i < m - 1? matrix[i + 1][j] : range;
                    int rightCell = j < n - 1? matrix[i][j + 1] : range;
                    matrix[i][j] = Math.min(matrix[i][j], Math.min(downCell, rightCell) + 1);
                }
            }
        }
        return matrix;
    }


    //similar to 286 Walls and Gates
    // public int[][] updateMatrix(int[][] matrix) {
    //     Deque<int[]> queue = new LinkedList<>();
    //     int m = matrix.length, n = matrix[0].length;
    //     for(int i = 0; i < m; i++) {
    //         for(int j = 0; j < n; j++) {
    //             if(matrix[i][j] == 0) {
    //                 queue.offer(new int[]{i, j});
    //             } else {
    //                 matrix[i][j] = Integer.MAX_VALUE;
    //             }
    //         }
    //     }
    //     int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    //     while(!queue.isEmpty()) {
    //         int[] cur = queue.poll();
    //         for(int[] dir : dirs) {
    //             int x = cur[0] + dir[0];
    //             int y = cur[1] + dir[1];
    //             if(x < 0 || x >= m || y < 0 || y >= n || matrix[x][y] <= matrix[cur[0]][cur[1]] + 1) continue;;
    //             matrix[x][y] = matrix[cur[0]][cur[1]] + 1;
    //             queue.offer(new int[]{x, y});
    //         }
    //     }
    //     return matrix;
    // }
}
