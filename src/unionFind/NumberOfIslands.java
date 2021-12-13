package unionFind;

/**
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 */
public class NumberOfIslands {
    //dfs
    //once we find '1', we set all it's neighbors to '0' by dfs, count++,
    int rows, cols;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int count = 0;
        rows = grid.length;
        cols = grid[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if(i >= 0 && i < rows && j >= 0 && j < cols && grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
        }
    }

    //bfs, without visited[][],
//     public int numIslands(char[][] grid) {
//         if (grid.length == 0) {
//             return 0;
//         }

//         int m = grid.length, n = grid[0].length;
//         Queue<int[]> queue = new LinkedList<>();
//         int count = 0;
//         for(int i=0; i<m; i++) {
//             for(int j=0; j<n; j++) {
//                 if(grid[i][j] == '1') {
//                     grid[i][j] = '0';
//                     queue.offer(new int[]{i, j});
//                     bfs(grid, queue);
//                     count++;
//                 }
//             }
//         }

//         return count;
//     }

//     int[][] dirs = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
//     private void bfs(char[][] grid, Queue<int[]> queue) {
//         int m = grid.length;
//         int n = grid[0].length;

//         while(!queue.isEmpty()) {
//             int[] curr = queue.poll();
//             for (int[] dir : dirs) {
//                 int x = curr[0] + dir[0];
//                 int y = curr[1] + dir[1];

//                 if (x < 0 || x >= m || y < 0 || y >=n || grid[x][y] == '0')
//                     continue;
//                 grid[x][y] = '0';
//                 queue.offer(new int[]{x, y});
//             }
//         }
//     }


    //bfs, use a visited[][] can avoid modifying the input grid
//     public int numIslands(char[][] grid) {
//         if (grid.length == 0) {
//             return 0;
//         }

//         int m = grid.length, n = grid[0].length;

//         boolean[][] visited = new boolean[m][n];
//         Queue<int[]> queue = new LinkedList<>();
//         int count = 0;
//         for(int i=0; i<m; i++) {
//             for(int j=0; j<n; j++) {
//                 if(grid[i][j] == '1' && !visited[i][j]) {
//                     queue.offer(new int[]{i, j});
//                     visited[i][j] = true;
//                     bfs(grid, queue, visited);
//                     count++;
//                 }
//             }
//         }

//         return count;
//     }

//     int[][] dirs = {{0,1}, {1,0}, {0, -1}, {-1, 0}};
//     private void bfs(char[][] grid, Queue<int[]> queue, boolean[][] visited) {
//         int m = grid.length;
//         int n = grid[0].length;

//         while(!queue.isEmpty()) {
//             int[] curr = queue.poll();
//             for (int[] dir : dirs) {
//                 int x = curr[0] + dir[0];
//                 int y = curr[1] + dir[1];

//                 if (x < 0 || x >= m || y < 0 || y >=n || visited[x][y] || grid[x][y] == '0')
//                     continue;

//                 visited[x][y] = true;
//                 queue.offer(new int[]{x, y});
//             }
//         }
//     }




    //union find is not efficient as dfs
//     class UnionFind {
//         private int num;//the number of disjoint sets
//         private int[] father;
//         private int[] rank;
//         public UnionFind(int n) {
//             num = n;
//             father = new int[num];
//             rank = new int[num];
//             for(int i = 0; i < num; i++) {
//                 father[i] = i;
//             }
//         }

//         public int find(int i) {
//             while(i != father[i]) {
//                 father[i] = father[father[i]];//path compression
//                 i = father[i];
//             }
//             return i;
//         }

//         public void union(int i, int j) {
//             int root1 = find(i);
//             int root2 = find(j);
//             if(root1 == root2) return;//no need to union i and j

//             if(rank[root1] < rank[root2]) {
//                 father[root1] = root2;
//                 rank[root2]++;
//             }else {
//                 father[root2] = root1;
//                 rank[root1]++;
//             }
//             num--;//the number of disjoint set decreasing by 1
//         }
//         public int size() {
//             return num;
//         }
//     }
//     public int numIslands(char[][] grid) {
//         if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
//         int row = grid.length, col = grid[0].length;

//         UnionFind uf = new UnionFind(row * col);
//         int zeros = 0;
//         int[][] directions = {{0, 1}, {1, 0}};//only check forward and downward directions
//         for(int i = 0; i < row; i++) {
//             for(int j = 0; j < col; j++) {
//                 if(grid[i][j] == '0') {
//                     zeros++;
//                     continue;
//                 }
//                 for(int[] d : directions) {
//                     int x = i + d[0];
//                     int y = j + d[1];
//                     if(x >= 0 && x < row && y >= 0 && y < col && grid[x][y] == '1') {
//                         uf.union(i * col + j, x * col + y);
//                     }
//                 }
//             }
//         }
//         return uf.size() - zeros;//total number of disjoint sets - number of zeros; 相连的岛看做一个set，同时原来uf.size()是指不相交集的数目，而每个0都是一个不相交集，需要剔除0的个数
//     }
}
