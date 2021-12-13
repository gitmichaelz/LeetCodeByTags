package twoPointers;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 */
public class TrappingRainWaterII {
    //9ms  98%
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length == 0) return 0;

        int m = heightMap.length;
        int n = heightMap[0].length;
        int[] water = new int[]{0};
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(1, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });

        // up / down
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{ i, 0, heightMap[i][0] });
            pq.offer(new int[]{ i, n - 1, heightMap[i][n - 1] });

            heightMap[i][0] = -1;
            heightMap[i][n - 1] = -1;
        }

        // left / right
        for (int i = 1; i < n - 1; i++) {
            pq.offer(new int[]{ 0, i, heightMap[0][i] });
            pq.offer(new int[]{ m - 1, i, heightMap[m - 1][i] });

            heightMap[0][i] = -1;
            heightMap[m - 1][i] = -1;
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            fill(pq, heightMap, m, n, curr[0] + 1, curr[1], curr[2], water);
            fill(pq, heightMap, m, n, curr[0] - 1, curr[1], curr[2], water);
            fill(pq, heightMap, m, n, curr[0], curr[1] + 1, curr[2], water);
            fill(pq, heightMap, m, n, curr[0], curr[1] - 1, curr[2], water);
        }

        return water[0];
    }

    private void fill(PriorityQueue<int[]> pq, int[][] height, int m, int n, int i, int j, int limit, int[] water) {
        if (i < 0 || i >= m || j < 0 || j >= n || height[i][j] == -1) return;

        if (height[i][j] > limit) {
            pq.add(new int[]{ i, j, height[i][j] });
            height[i][j] = -1;
            return;
        } else {
            water[0] += limit - height[i][j];
            height[i][j] = -1;

            fill(pq, height, m, n, i + 1, j, limit, water);
            fill(pq, height, m, n, i - 1, j, limit, water);
            fill(pq, height, m, n, i, j + 1, limit, water);
            fill(pq, height, m, n, i, j - 1, limit, water);
        }
    }

}
