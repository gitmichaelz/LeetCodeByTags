package array_matrix_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 */
public class PacificAtlanticWaterFlow {
    //dfs
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacificVisited = new boolean[m][n];
        boolean[][] atlanticVisited = new boolean[m][n];
        for(int i = 0; i < m; i++){//vertical
            dfs(matrix, pacificVisited, i, 0, Integer.MIN_VALUE);
            dfs(matrix, atlanticVisited, i, n - 1, Integer.MIN_VALUE);
        }
        for(int i = 0; i < n; i++){//horizontal
            dfs(matrix, pacificVisited, 0, i, Integer.MIN_VALUE);
            dfs(matrix, atlanticVisited, m - 1, i, Integer.MIN_VALUE);
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pacificVisited[i][j] && atlanticVisited[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    private void dfs(int[][] matrix, boolean[][] visited, int x, int y, int height){
        int m = matrix.length, n = matrix[0].length;
        if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < height){//保证水往低处流
            return;
        }
        visited[x][y] = true;
        for(int[] dir : dirs){
            dfs(matrix, visited, x + dir[0], y + dir[1], matrix[x][y]);//height updated as matrix[x][y]
        }
    }

    //bfs
    /*
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacificVisited = new boolean[m][n];
        boolean[][] atlanticVisited = new boolean[m][n];
        Deque<int[]> pacificQueue = new LinkedList<>();
        Deque<int[]> atlanticQueue = new LinkedList<>();
        for(int i = 0; i < m; i++){//vertical
            pacificVisited[i][0] = true;
            atlanticVisited[i][n - 1] = true;
            pacificQueue.offer(new int[]{i, 0});
            atlanticQueue.offer(new int[]{i, n - 1});
        }
        for(int i = 0; i < n; i++){//horizontal
            pacificVisited[0][i] = true;
            atlanticVisited[m - 1][i] = true;
            pacificQueue.offer(new int[]{0, i});
            atlanticQueue.offer(new int[]{m - 1, i});
        }
        bfs(matrix, pacificQueue, pacificVisited);
        bfs(matrix, atlanticQueue, atlanticVisited);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pacificVisited[i][j] && atlanticVisited[i][j]){
                    res.add(Arrays.asList(i, j));
                }
            }
        }
        return res;
    }
    private void bfs(int[][] matrix, Deque<int[]> queue, boolean[][] visited){
        int m = matrix.length, n = matrix[0].length;
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] dir : dirs){
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) {//to make sure water can flow from higher cell to lower cell or with equal height
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
    */
}
