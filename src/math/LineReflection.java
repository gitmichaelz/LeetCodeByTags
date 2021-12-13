package math;

import java.util.HashSet;

/**
 * Given n points on a 2D plane, find if there is such a line parallel to the y-axis that reflects the given points symmetrically.
 *
 * In other words, answer whether or not if there exists a line that after reflecting all points over the given line, the original points' set is the same as the reflected ones.
 *
 * Note that there can be repeated points.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,1],[-1,1]]
 * Output: true
 * Explanation: We can choose the line x = 0.
 */
public class LineReflection {
    public boolean isReflected(int[][] points) {
        if(points.length == 0) return true;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        HashSet<Point> set = new HashSet<>();
        for(int[] p : points) {
            max = Math.max(max, p[0]);
            min = Math.min(min, p[0]);
            set.add(new Point(p[0], p[1]));
        }

        int sum = min + max;

        for(int [] p : points) {
            Point ref = new Point(sum - p[0], p[1]);
            if(!set.contains(ref)) {
                return false;
            }
        }

        return true;
    }

    private class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return (this.x == p.x && this.y == p.y);
        }
        @Override
        public int hashCode(){
            return x * 31 + y * 17;
        }
    }
}
