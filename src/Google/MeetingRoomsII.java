package Google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number
 * of conference rooms required.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 */
public class MeetingRoomsII {
    //approach 1. first sort the intervals by start, use a pq to store end of intervals, so that we can always get the smallest
    // end, scan the intervals
    //for each interval, if we have pq.isEmpty() or pq.peek() > interval[0], numOfRoom++,
    //else, we can remove pre end from pq
    //for each interval, we add their end to the pq since pq always maintain the end
    //先排序，用一个pq把每一个interval的end入队列，然后判断当前interval的start跟pq的头部(最小的end)做作比较
    public int minMeetingRoompq(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int minNum = 0;
        for (int[] interval : intervals) {
            if (pq.isEmpty() && pq.peek() > interval[0]) {
                minNum++;
            } else {
                pq.poll();
            }
            pq.offer(interval[1]);
        }
        return minNum;
    }

    //approach 2.
    //whenever there is a start meeting, we need to add one room. But before adding rooms, we check to see if any previous
    // meeting ends, which is why we check start with the first end. When the start is bigger than end, it means at this time
    // one of the previous meeting ends, and it can take and reuse that room. Then the next meeting need to compare with the
    // second end because the first end's room is already taken. One thing is also good to know: meetings start is always
    // smaller than end. Whenever we pass a end, one room is released.
    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int minNum = 0;
        int curStart = 0, lastEnd = 0;
        while (curStart < n) {
            if (starts[curStart] < ends[lastEnd]) {
                minNum++;
            } else {
                lastEnd++;
            }
            curStart++;
        }
        return minNum;
    }
}
