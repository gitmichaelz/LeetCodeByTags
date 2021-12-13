package TopK;

import java.util.Random;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 *
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 *
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
 */
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        int[][] res = new int[K][2];
        kClosest(points, res, 0, points.length - 1, K);
        return res;
    }

    private void kClosest(int[][] points, int[][] res, int left, int right, int K) {
        int random = new Random().nextInt(right - left + 1) + left;
        swap(points, random, right);
        double pivot = getDistance(points[right][0], points[right][1]);
        int start = left, end = right - 1;
        while(start <= end) {
            if(getDistance(points[start][0], points[start][1]) <= pivot) {
                start++;
            } else {
                swap(points, start, end);
                end--;
            }
        }
        //after the above steps, all the element before index "start" are less than or equal to pivot
        swap(points, start, right);//swap pivot and the "start" one
        int m = start - left + 1;
        if (m == K) {
            for(int i = 0; i <= start; i++) {//important!!! here i = 0, not left!!!
                res[i][0] = points[i][0];
                res[i][1] = points[i][1];
            }
        } else if (m > K) {
            kClosest(points, res, left, start - 1, K);
        } else {
            kClosest(points, res, start + 1, right, K - m);
        }
    }

    private double getDistance(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }

    private void swap(int[][] points, int left, int right) {
        int[] tmp = points[left];
        points[left] = points[right];
        points[right] = tmp;
    }
}
