package array_matrix_sorting;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given an m x n grid grid of values 0, 1, or 2, where:
 *
 *     each 0 marks an empty land that you can pass by freely,
 *     each 1 marks a building that you cannot pass through, and
 *     each 2 marks an obstacle that you cannot pass through.
 *
 * You want to build a house on an empty land that reaches all buildings in the shortest total travel distance. You can only move up, down, left, and right.
 *
 * Return the shortest travel distance for such a house. If it is not possible to build such a house according to the above rules, return -1.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 7
 * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
 * The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal.
 * So return 7.
 */
public class ShortestDistanceFromAllBuildings {
//这个题原始方法和优化方法都要会。

    //思路，对于每一个房子，我们计算从这个房子到每一个空地(0)的距离。然后这些距离累加，得出每一个空地到所有房子的距离。
    //对于每一个空地(i, j), distances[i][j]就是该空地到所有房子的距离之和。
    //然后从所有空地中，找出距离最小的那个.  Min(distances[i][j]) for all grid[i][j] == 0
    //同时注意，有的房子可能无法与空地联通，所以我们还应该判断，对于每个空地，它能联通的房子的数目。用reach[i][j]来表示。
    //只有reach[i][j] == num_buildings 才能进行min(distances[i][j])计算
    //time: O(num_building * all cells) = O(B * M * N),   B: numBuildings
    //space: O(m * n)
    int[][] distances;
    int[][] reach;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public int shortestDistance1(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        distances = new int[m][n];
        reach = new int[m][n];
        int numBuildings = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    numBuildings++;
                    bfs1(grid, i, j);
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0 && reach[i][j] == numBuildings) {
                    minDistance = Math.min(minDistance, distances[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE? -1 : minDistance;
    }
    //compute the distance from cur building [i][j] to all empty lands(0)
    //also compute the number of buildings each empty land can reach
    private void bfs1(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        boolean[][] visited = new boolean[m][n];//mark visited nodes, to avoid repetitive visiting
        int level = 0;
        while(!queue.isEmpty()) {
            level++;
            for(int size = queue.size(); size > 0; size--) {
                int[] cur = queue.poll();
                for(int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if(x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 0 || visited[x][y]) continue;
                    visited[x][y] = true;
                    distances[x][y] += level;//注意distance[x][y] 和 reach[x][y]的计算方法
                    reach[x][y]++;
                    queue.offer(new int[] {x, y});
                }
            }
        }
    }


    //优化方法，其实题目要求里有个隐含条件。即所有的building应该能通过empty land联通，
    //我们可以先求出总的building的数目，然后在BFS的时候，如果发现从某个building不能connect(必须通过空地connect)所有的building则返回false.没必要进行下面的计算了。terminate early
    //还要注意：该条件不能代替"某个特定空地必须能联通所有的building"，所以，还要保留reach[i][j]的判断！！！
    public int shortestDistance(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length, n = grid[0].length;
        distances = new int[m][n];
        reach = new int[m][n];
        int numBuildings = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    numBuildings++;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    if(!bfs(grid, i, j, numBuildings)) return -1;
                }
            }
        }
        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0 && reach[i][j] == numBuildings) {
                    minDistance = Math.min(minDistance, distances[i][j]);
                }
            }
        }
        return minDistance == Integer.MAX_VALUE? -1 : minDistance;
    }

    //Return true if the current building can connect to all the other buildings, false other wid=se
    private boolean bfs(int[][] grid, int i, int j, int numBuildings) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{i, j});
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int level = 0;
        int count = 0;//number of buildings found in bfs
        while(!queue.isEmpty()) {
            level++;
            for(int size = queue.size(); size > 0; size--) {
                int[] cur = queue.poll();
                for(int[] dir : dirs) {
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if(x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) continue;//注意这里和上面的区别
                    if(grid[x][y] == 0) {
                        visited[x][y] = true;
                        distances[x][y] += level;
                        reach[x][y]++;
                        queue.offer(new int[] {x, y});
                    } else if(grid[x][y] == 1) {//building
                        count++;//注意，这里最终会包括初始进来的building grid[i][j]
                        visited[x][y] = true;
                    }
                }
            }
        }
        return count == numBuildings;
    }
}
