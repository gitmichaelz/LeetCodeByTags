package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * On a campus represented on the X-Y plane, there are n workers and m bikes, with n <= m.
 *
 * You are given an array workers of length n where workers[i] = [xi, yi] is the position of the ith worker. You are also given an array bikes of length m where bikes[j] = [xj, yj] is the position of the jth bike. All the given positions are unique.
 *
 * Assign a bike to each worker. Among the available bikes and workers, we choose the (workeri, bikej) pair with the shortest Manhattan distance between each other and assign the bike to that worker.
 *
 * If there are multiple (workeri, bikej) pairs with the same shortest Manhattan distance, we choose the pair with the smallest worker index. If there are multiple ways to do that, we choose the pair with the smallest bike index. Repeat this process until there are no available workers.
 *
 * Return an array answer of length n, where answer[i] is the index (0-indexed) of the bike that the ith worker is assigned to.
 *
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
 *
 *
 *
 * Example 1:
 *
 * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * Output: [1,0]
 * Explanation: Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
 */
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        int m = bikes.length;
        List<int[]>[] list = new List[2001];//since 0 <= workers[i][j], bikes[i][j] < 1000; so max distance would be 2000
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int dist = dist(workers[i], bikes[j]);
                if(list[dist] == null) {
                    list[dist] = new ArrayList<>();
                }
                list[dist].add(new int[] {i, j});
            }
        }
        boolean[] assigned = new boolean[n];
        boolean[] occupied = new boolean[m];
        int[] res = new int[n];
        for(int i = 0; i < list.length; i++) {
            if(list[i] != null) {
                for(int j = 0; j < list[i].size(); j++) {
                    int w = list[i].get(j)[0];
                    int b = list[i].get(j)[1];
                    if(!assigned[w] && !occupied[b]) {
                        res[w] = b;
                        assigned[w] = true;
                        occupied[b] = true;
                    }
                }
            }
        }
        return res;
    }

    private int dist(int[] worker, int[] bike){
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}
