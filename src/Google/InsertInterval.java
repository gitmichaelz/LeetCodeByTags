package Google;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    //    similar to merge interval
    //    O(N)
    public int[][] insert(int[][] intervals, int[] newInterval){
        if(newInterval == null || newInterval.length == 0) return intervals;
        if(intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[][]{newInterval};

        List<int[]> res = new ArrayList<>();
        for(int i = 0; i < intervals.length; i++){
            if(intervals[i][1] < newInterval[0]){//newInterval totally after cur interval
                res.add(intervals[i]);
            } else if(intervals[i][0] > newInterval[1]){//newInterval totally before cur interval
                res.add(newInterval);
                newInterval = intervals[i];
            } else{//overlap
                newInterval = new int[]{Math.min(newInterval[0], intervals[i][0]), Math.max(newInterval[1], intervals[i][1])};
            }
        }
        res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }

    /*
    //这个方法只有部分是log(n),但整体来看还是O(N)
    public int[][] insert(int[][] intervals, int[] newInterval){
        if(newInterval == null || newInterval.length == 0) return intervals;
        if(intervals == null || intervals.length == 0 || intervals[0].length == 0) return new int[][]{newInterval};
        List<int[]> res = new ArrayList<>();
        int start = searchLowerBound(intervals, newInterval[0]);
        int end = searchUpperBound(intervals, newInterval[1]);
        if(start <= end && start >= 0 && start < intervals.length && end >= 0 && end < intervals.length){
            newInterval[0] = Math.min(newInterval[0], intervals[start][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[end][1]);
        }
        int idx = 0;
        while(idx < start){
            res.add(intervals[idx++]);
        }
        res.add(newInterval);
        idx = end + 1;
        while(idx < intervals.length){
            res.add(intervals[idx++]);
        }
        return res.toArray(new int[res.size()][]);
    }

    //lower bound, find a target such that target <= intervals[i][0]
    //注意，当求出一个left时，我们还要多判断两步，是否可以合并到前面或后面的一个interval中
    private int searchLowerBound(int[][]intervals, int target){
        int left = 0, right = intervals.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(target > intervals[mid][0]){
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if(intervals[left][1] < target) {//target超出最大范围了
            left++;
        } else if(left > 0 && intervals[left - 1][1] >= target) {//
            left--;
        }
        return left;
    }
    //upper bound, find a target such that target >= intervals[i][1]
    //注意，当求出一个left时，我们还要多判断两步，是否可以合并到前面或后面的一个interval中
    private int searchUpperBound(int[][] intervals, int target){
        int left = 0, right = intervals.length - 1;
        while(left < right){
            int mid = right - (right - left)/2;
            if(target < intervals[mid][1]){
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if(intervals[left][0] > target){//
            left--;
        } else if(left < intervals.length - 1 && intervals[left + 1][0] <= target){
            left++;
        }
        return left;
    }
    /
     */

    //2> O(n) solution
    // public int[][] insert(int[][] intervals, int[] newInterval) {
    //     if(intervals == null) return new int[][]{newInterval};
    //     if(newInterval == null) return intervals;
    //     List<int[]> res = new ArrayList<>();
    //     int i = 0;
    //     while(i < intervals.length && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);
    //     while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
    //         newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
    //         newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
    //         i++;
    //     }
    //     res.add(newInterval);
    //     while(i < intervals.length) res.add(intervals[i++]);
    //     return res.toArray(new int[res.size()][]);
    // }




    //since intervals are sorted, we can use binary search to find the start and end inserted position
//     public int[][] insert(int[][] intervals, int[] newInterval) {

//         List<int[]> res = new ArrayList<>();
//         int iStart = findStart(intervals, newInterval[0]);//start >= 0
//         int iEnd = findEnd(intervals, newInterval[1]);
//         if(iStart > 0 && intervals[iStart - 1][1] >= newInterval[0]) iStart--;
//         if(iEnd == intervals.length || intervals[iEnd][0] > newInterval[1]) iEnd--;

//         if(iStart <= iEnd) {
//             newInterval[0] = Math.min(newInterval[0], intervals[iStart][0]);
//             newInterval[1] = Math.max(newInterval[1], intervals[iEnd][1]);
//         }
//         int i = 0;
//         while(i < iStart) res.add(intervals[i++]);
//         res.add(newInterval);
//         i = iEnd + 1;
//         while(i < intervals.length) res.add(intervals[i++]);
//         return res.toArray(new int[res.size()][]);
//     }

//     //this method returns the start position, the actual insert position is either start or the one before/after start
//     private int findStart(int[][] intervals, int val) {
//         int start = 0, end = intervals.length - 1;
//         while(start <= end) {
//             int mid = (start + end)/2;
//             if(intervals[mid][0] == val) return mid;
//             else if(intervals[mid][0] > val) {
//                 end = mid - 1;
//             } else {
//                 start = mid + 1;
//             }
//         }
//         return start;
//     }

//     private int findEnd(int[][] intervals, int val) {
//         int start = 0, end = intervals.length - 1;
//         while(start <= end) {
//             int mid = (start + end)/2;
//             if(intervals[mid][1] ==  val) {
//                 return mid;
//             } else if(intervals[mid][1] > val) {
//                 end = mid - 1;
//             } else {
//                 start = mid + 1;
//             }
//         }
//         return start;
//     }
}
