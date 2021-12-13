package array_matrix_sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        for(int i = 0; i < intervals.length - 1; i++) {
            if(intervals[i + 1][0] < intervals[i][1]) return false;
        }
        return true;
    }
}
