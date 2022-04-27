package Google;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * here are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * Example 1:
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 */
public class NumberOfProvinces {
    //dfs
    //TIME: O(N^2), The complete matrix of size N^2 is traversed.
    //SPACE: O(N) visited array of size n is used.
    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0 || M[0].length == 0) return 0;
        int N = M.length;
        boolean[] visited = new boolean[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        for (int j = 0; j < M.length; j++) {//check every neighbor of i
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }

    //BFS
    //TIME: O(N^2), The complete matrix of size N^2 is traversed.
    //SPACE: O(N) A queue and visited array of size n is used.
    public int findCircleNumBFS(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int count = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.poll();
                    visited[s] = true;
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && !visited[j])
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }
}
