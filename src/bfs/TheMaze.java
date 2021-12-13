package bfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 */
public class TheMaze {
    //这个题DFS快点，但是BFS也要掌握
    //BFS 版本
    public boolean hasPath(int[][] maze, int[] start, int[] destination){
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(start);
        maze[start[0]][start[1]] = -1;//visited position marked as -1
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for(int[] d: dirs){
                int x = cur[0], y = cur[1];
                while(isValid(maze, x + d[0], y + d[1])){
                    x += d[0];
                    y += d[1];
                }
                if(x == destination[0] && y == destination[1]) return true;
                if(maze[x][y] != -1){//skip visited position
                    maze[x][y] = -1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return false;
    }
    private boolean isValid(int[][] maze, int row, int col){
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != 1;
    }
    //注意，这个题最大的坑是不要理解错题意。题目要求必须在destination处能停住，而不是仅仅能到达。如果到达了目的地
    //但还能继续走，这样的是不符合题目要求的。比如你从左边冲过来，到达了end，虽然end点的上下两头都有墙，但右边没有墙，这种就不符合定义。
    //所以这里对于stop的判定条件是：球从一个方向朝着destination卷过来，能否在end点的下一个位置碰到墙。
    // int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    // public boolean hasPath(int[][] maze, int[] start, int[] destination){
    //     boolean[][] visited = new boolean[maze.length][maze[0].length];
    //     return dfs(maze, start, destination, visited);
    // }
    // private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited){
    //     if(visited[start[0]][start[1]]) return false;//这一步判断非常重要，必须先判断当前位置有没有走过，如果走过，返回false
    //     if(start[0] == destination[0] && start[1] == destination[1]) return true;
    //     visited[start[0]][start[1]] = true;//并不是路径上的每一个点都记visited，只有拐点需要记visited。
    //     for(int[] dir : dirs){
    //         int newX = start[0];
    //         int newY = start[1];
    //         while(isValid(maze, newX + dir[0], newY + dir[1])){//需要让球一滚到底，知道撞上墙
    //             newX += dir[0];
    //             newY += dir[1];
    //         }
    //         if(dfs(maze, new int[]{newX, newY}, destination, visited)){//新起点
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    // private boolean isValid(int[][] maze, int row, int col){
    //     return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0;
    // }
}
