package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.
 *
 */
public class MaxPointsOnALine {
    public int maxPoints(int[][] points) {
        if (points.length == 1) return 1;

        int ans = 0;
        for (int i = 0; i < points.length - 1; ++i) {
            // 每次新的循环都重置map,这就防止了斜率相同但是b值不同的情况。
            Map<Double, Integer> map = new HashMap<>();
            for (int j = i + 1; j < points.length; ++j) {
                double k = getSlope(points[i], points[j]);
                map.put(k, map.getOrDefault(k, 1) + 1);
                ans = Math.max(ans, map.get(k));
            }
        }

        return ans;
    }

    private double getSlope(int[] a, int[] b) {
        if (a[0] == b[0]) return Double.MAX_VALUE;
        if (a[1] == b[1]) return 0;
        return ((double) a[1] - b[1]) / ((double) a[0] - b[0]);
    }
}
