package dfs_backtracking_Recursion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return the shortest distance for the ball to stop at the destination. If the ball cannot stop at destination, return -1.
 *
 * The distance is the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included).
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */
public class TheMazeII {
    //这个题算法上Dijkstra最优，但是由于lamda表达式问题，运行时间上比普通BFS慢1ms. 为7ms
    //关于该dijkstra的介绍：https://blog.csdn.net/m0_38139979/article/details/108396752
    //dijkstra的核心就是，每次从离start点最近的点开始bfs(用pq代替queue),对该点的邻居，update他们离原点的最近距离，然后继续bfs.直到遍历完所有点。
    //我们在bfs时，用一个priorityqueue(按照离原点的距离排序)而不是普通的queue,这样每次pop出的节点都是离初始点最短的点，所以一旦pop出的点为destination时此时即最短路径。
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int[][] distances = new int[maze.length][maze[0].length];
        for(int[] d : distances) {
            Arrays.fill(d, -1);
        }
        distances[start[0]][start[1]] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((Comparator.comparingInt(a -> a[2])));
        pq.offer(new int[]{start[0], start[1], 0});
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(maze[cur[0]][cur[1]] == 2) continue;//already visited, since we may visited a nodes more than once, in dijkstra, such node wont be the optimal one, so we need to skip it
            maze[cur[0]][cur[1]] = 2;

            if(cur[0] == destination[0] && cur[1] == destination[1]) return cur[2];//cur[2] stores the shortest distance from start to cur
            for(int[] dir : dirs) {
                int x = cur[0];
                int y = cur[1];
                int len = cur[2];
                while(isValid(maze, x + dir[0], y + dir[1])) {
                    len++;
                    x += dir[0];
                    y += dir[1];
                }
                if(distances[x][y] == -1 || len < distances[x][y]) {
                    distances[x][y] = len;//不要忘记更新distances
                    pq.offer(new int[]{x, y, len});
                }
            }
        }
        return -1;
    }
    private boolean isValid(int[][] maze, int row, int col){
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != 1;
    }


    //这个题dfs时间非常慢，以下是普通bfs 6ms
//     public int shortestDistance(int[][] maze, int[] start, int[] destination) {
//         int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
//         int m = maze.length, n = maze[0].length;
//         int[][] distance = new int[m][n];
//         for(int[] d : distance){
//             Arrays.fill(d, -1);
//         }
//         Deque<int[]> queue = new LinkedList<>();
//         distance[start[0]][start[1]] = 0;//原点距离初始化为0
//         queue.offer(start);
//         //注意，每一层相当于从一个出发点到撞到墙为止
//         while(!queue.isEmpty()){
//             int[] cur = queue.poll();
//             for(int[] dir : dirs){
//                 int x = cur[0], y = cur[1];
//                 int len = distance[x][y];
//                 while(isValid(maze, x + dir[0], y + dir[1])){
//                     len++;
//                     x += dir[0];
//                     y += dir[1];
//                 }
//                 //退出while循环，撞到墙了。此时只有在满足条件下才更新distance,并且从该点继续进行bfs,效率比dfs高
//                 if(distance[x][y] == -1 || len < distance[x][y]){//if not visited or len is shorter than distance[x][y]
//                     distance[x][y] = len;
//                     queue.offer(new int[]{x, y});
//                 }
//             }
//         }
//         return distance[destination[0]][destination[1]];//如果为访问到该点，则起一定会有个最短距离，否则，没访问到(在此处撞墙，)，则返回-1；
//     }

    // private boolean isValid(int[][] maze, int row, int col){
    //     return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != 1;
    // }




    // int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    //     int m = maze.length, n = maze[0].length;
    //     int[][] distance = new int[m][n];
    //     for(int[] d : distance) {
    //         Arrays.fill(d, -1);
    //     }
    //     distance[start[0]][start[1]] = 0;//start point's distance is initialized to 1, so the result just distance[destination[0]][destination[1]] just need minus -1
    //     dfs(maze, start, destination, distance);
    //     return distance[destination[0]][destination[1]];
    // }
    // //calculate the shortest distance[x][y], which calculated by starting with len = distance[start[0]][start[1]]
    // private void dfs(int[][] maze, int[] start, int[] destination, int[][] distance){
    //     if(start[0] == destination[0] && start[1] == destination[1]) return;
    //     for(int [] dir : dirs){
    //         int len = distance[start[0]][start[1]];//note, len is starting with distance[start[0]][start[1]], not 0
    //         int newX = start[0];
    //         int newY = start[1];
    //         while(isValid(maze, newX + dir[0], newY + dir[1])) {
    //             len++;
    //             newX += dir[0];
    //             newY += dir[1];
    //         }
    //         if(distance[newX][newY] == -1 || len < distance[newX][newY]){//if not visited or len is shorter than distance[newX][newY]
    //             distance[newX][newY] = len;
    //             dfs(maze, new int[]{newX, newY}, destination, distance);//new start for next recursion
    //         }
    //     }
    // }
    // private boolean isValid(int[][] maze, int row, int col){
    //     return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != 1;
    // }
}
