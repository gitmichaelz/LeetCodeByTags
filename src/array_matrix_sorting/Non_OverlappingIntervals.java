package array_matrix_sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 */
public class Non_OverlappingIntervals {
    //Pretty straightforward. Logic is that we sort by the first element in the intervals, then when we encounter an overlap,
    // we increase the count, and decide to remove the interval with the larger end date
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));//can also in this way (a, b) -> a[0] - b[0]
        int count = 0;
        int preEnd = intervals[0][1];
        for(int i = 1; i < intervals.length; i++){
            if(preEnd > intervals[i][0]){
                count++;
                preEnd = Math.min(preEnd, intervals[i][1]);
            } else {
                preEnd = intervals[i][1];
            }
        }
        return count;
    }
}
