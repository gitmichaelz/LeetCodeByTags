package array_matrix_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 *
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 *
 * Constraints:
 *
 *     1 <= intervals.length <= 104
 *     intervals[i].length == 2
 *     0 <= starti <= endi <= 104
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if(intervals == null || intervals.length == 0 || intervals[0].length == 0) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int[] pre = intervals[0];//pre as an contanier, to store the max end
        List<int[]> res = new ArrayList<>();
        res.add(pre);
        for(int[] cur : intervals){
            if(pre[1] >= cur[0]) {//dont forget '='
                pre[1] = Math.max(pre[1], cur[1]);
            } else {
                res.add(cur);
                pre = cur;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
