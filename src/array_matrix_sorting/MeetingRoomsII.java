package array_matrix_sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of
 * conference rooms required.
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: 1
 */
public class MeetingRoomsII {
    //先排序，用一个pq把每一个interval的end入队列，然后判断当前interval的start跟pq的头部(最小的end)做作比较
    public int minMeetingRooms(int[][] intervals){
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> pq = new PriorityQueue();
        int minNum = 0;
        for(int[] interval : intervals){
            if(pq.isEmpty() || pq.peek() > interval[0]){
                minNum++;
            }else {
                pq.poll();
            }
            pq.offer(interval[1]);
        }
        return minNum;
    }


    public int minMeetingRooms1(int[][] intervals){
        int n = intervals.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for(int i = 0; i < n; i++){
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int minNum = 0;
        int curStart = 0, lastEnd = 0;
        while(curStart < n){
            if(starts[curStart] < ends[lastEnd]){
                minNum++;
            }else {
                lastEnd++;
            }
            curStart++;
        }
        return minNum;
    }
}
