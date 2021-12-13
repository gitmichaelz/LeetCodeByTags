package array_matrix_sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 *
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 *
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 */
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        Map<Integer, Integer> m = new HashMap();
        int[] sort = new int[intervals.length], res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            sort[i] = intervals[i][0];
            m.put(sort[i], i);//save start point as key, index as value
        }
        Arrays.sort(sort);
        for (int i = 0; i < intervals.length; i++) {
            int end = intervals[i][1];
            int id = Arrays.binarySearch(sort, end);
            if (id < 0) id = - (id + 1);
            if (id >= res.length) {
                res[i] = -1;//nothing behind it
            } else {
                res[i] = m.get(sort[id]);
            }
        }
        return res;
    }
}
