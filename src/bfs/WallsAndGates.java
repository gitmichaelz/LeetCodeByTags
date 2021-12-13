package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 *     -1 A wall or an obstacle.
 *     0 A gate.
 *     INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 *
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 *
 *
 * Example 1:
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 */
public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = m == 0 ? 0 : rooms[0].length;
        int[][] dirs = {{-1,0}, {0,1}, {0,-1}, {1,0}};
        Queue<int[]> queue = new LinkedList<>();
        // add all gates to the queue
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[] {i,j});
                }
            }
        }
        // update distance from gates
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] dir: dirs) {
                int X = curPos[0] + dir[0];
                int Y = curPos[1] + dir[1];
                if (X<0 || Y <0 || X >= m || Y >= n || rooms[X][Y] != Integer.MAX_VALUE) continue;
                rooms[X][Y] = rooms[curPos[0]][curPos[1]]+1;
                queue.offer(new int[] {X, Y});
            }
        }
    }
//     public void wallsAndGates(int[][] rooms) {
//         int m = rooms.length, n = rooms[0].length;
//         for(int i = 0; i < m; i++) {
//             for(int j = 0; j < n; j++) {
//                 if(rooms[i][j] == 0) {
//                     dfs(rooms, i, j, 0);
//                 }
//             }
//         }
//     }

//     int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//     private void dfs(int[][] rooms, int i, int j, int distance) {
//         if(i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < distance) return;
//         rooms[i][j] = distance;
//         for(int[] dir : dirs) {
//             dfs(rooms, dir[0] + i, dir[1] + j, distance + 1);
//         }
//     }
}
