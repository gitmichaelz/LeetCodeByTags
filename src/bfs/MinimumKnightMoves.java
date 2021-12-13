package bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * n an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
 *
 * A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
 *
 * Return the minimum number of steps needed to move the knight to the square [x, y]. It is guaranteed the answer exists.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 2, y = 1
 * Output: 1
 * Explanation: [0, 0] → [2, 1]
 */
public class MinimumKnightMoves {
    //DFS, consider the first quadrant, we start from target, go either left-down or down-left to get to (0, 0)
    //the basic is if (x + y ==  0), return 0, if (x + y == 2) return 2; for example (1, 1), we need 2 moves to get to (0, 0)
    //time: O(|X * Y|), space: O(|X * Y|),即第一象限内的所有cell
    int[][] visited = new int[301][301];
    public int minKnightMoves(int x, int y) {
        return move(Math.abs(x), Math.abs(y));
    }

    private int move(int x, int y) {
        if(x + y == 0) return 0;
        if(x + y == 2) return 2;
        if(visited[x][y] > 0) return visited[x][y];
        int minMoves = 1 + Math.min(move(Math.abs(x - 1), Math.abs(y - 2)), move(Math.abs(x - 2), Math.abs(y - 1)));//dont forget + 1!!
        return visited[x][y] = minMoves;
    }



    /*
    "why newX >= -1 && newY >= -1) instead of newX >= 0 && newY >= 0)? could you please explain?"

    The key thing to note here is
    x = Math.abs(x);
    y = Math.abs(y);
    Here we are forcing the original co-ordinates to be in 1st Quadrant only. ( since we can use symmetry )
    you cannot reach from 0,0 to 1,1 using only 1st quadrant. hence we allow x >=-1 y>=-1 instead of x>=0, y>=0 limit

    "why newX >= -1 && newY >= -1) instead of newX >= -2 && newY >= -2)? could you please explain?"
    Because,let's say, you are currently at (-2, -2), then you will make neither its next step's index be at least one positive value
     */
    //Everything is in First Quadrant!!!
    public int minKnightMovesBFS(int x, int y) {
        int[][] dirs = new int[][]{{-1, -2}, {1, -2}, {2, 1}, {2, -1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};
        Set<Integer> visited = new HashSet<>();
        //To store next cell for the BFS and visited cells we can use encoding - just multiply x by something > 600 (from -300 to 300) and add y.
        // Multiplication can be replaced by bit shift - it's faster. 10 bits are enough - it gives 1024.
        Deque<Integer> queue = new ArrayDeque<>();//to store the encrypted position of cell, a = (x << 10) + y;
        x = Math.abs(x);//since the location is symmetric vertically, horizontally and diagonally, we can make x,y non-negative
        y = Math.abs(y);//that is original co-ordinates to be in 1st Quadrant only. ( since we can use symmetry )when we make a move, we can do some kind of pruning to reducing the searching steps.
        queue.offer(0);
        visited.add(0);
        int level = 0;
        while(!queue.isEmpty()) {
            for(int size = queue.size(); size > 0; size--) {
                int cur = queue.poll();
                int i = (cur >> 10), j = cur - (i << 10);//不要忘记括号。
                if(i == x && j == y) return level;
                for(int[] dir : dirs) {
                    int newX = dir[0] + i;
                    int newY = dir[1] + j;
                    int curEnc = (newX << 10) + newY;//不要忘记括号！！
                    if(newX >= -1 && newY >= -1 && visited.add(curEnc)) {//pruning, any newX < -1 and newY < -1 will be discarded as we know x,y are non-negative
                        queue.offer(curEnc);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
