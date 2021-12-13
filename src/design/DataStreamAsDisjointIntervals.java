package design;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
 *
 * Implement the SummaryRanges class:
 *
 *     SummaryRanges() Initializes the object with an empty stream.
 *     void addNum(int val) Adds the integer val to the stream.
 *     int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi].
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * Output
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 */
public class DataStreamAsDisjointIntervals {
    //for a new number n, we do binary search its inserting position from existing intervals (找下界问题), if n is already within
    // a certain interval, return -1, then we can do nothing
    //if index >= 0 . we have the following cases: （注意，我们返回的index,一定是起始位置大于val的interval的index）
    //  1>  n can be merged into intervals[index].  n == intervals[index].start - 1; Modify intervals[index].start to n
    //  2>  n can be merge into intervals[index - 1] n == intervals[index - 1].end + 1, Modify intervals[index - 1].end to val
    //  3>  n cannot be merged into either interval. insert interval[n, n]
    //Finally, after inserting n, we need to check whether intervals[index] and intervals[index+1] can be merged.
    class SummaryRanges {
        List<int[]> intervals;
        /** Initialize your data structure here. */
        public SummaryRanges() {
            intervals = new ArrayList<>();
        }

        public void addNum(int val) {
            int index = binarrySearch(intervals, val);// get idx to insert val, -1 if already exist in some interval
            if(index == -1) return; //already exist, do nothing
            boolean isMerged = false;
            if(index == 0 && !intervals.isEmpty()){//这个单独拿出来，不和下面一块处理，因为没有left
                if(val + 1 == intervals.get(index)[0]) {
                    intervals.get(index)[0] = val;
                    isMerged = true;
                }
            } else if(index > 0 && index < intervals.size()){
                int[] left = intervals.get(index - 1);
                int[] right = intervals.get(index);
                if(val - 1 == left[1]){
                    left[1] = val;
                    isMerged = true;
                }
                if(val + 1 == right[0]){
                    right[0] = val;
                    isMerged = true;
                }
                if(left[1] == right[0]){
                    left[1] = right[1];
                    intervals.remove(right);
                }
            } else if(index == intervals.size() && !intervals.isEmpty()){//坑，不要忘记判断是否为空
                if(val - 1 == intervals.get(index - 1)[1]) {
                    intervals.get(index - 1)[1] = val;
                    isMerged = true;
                }
            }
            if(!isMerged){
                intervals.add(index, new int[]{val, val});//如果不能合并，则在插入位置把val作为新的interval插入在index位置
            }
        }
        //找下界问题，参考search inserting position, 返回的结果一定是起始位置大于val的那个interval的index(如果interval不包含val的话)
        private int binarrySearch(List<int[]> intervals, int val){
            int start = 0, end = intervals.size();//坑。这里end要取到intervals.size()，因为有可能是添加到最后一个。
            while(start < end){
                int mid = start + (end - start)/2;
                int[] interval = intervals.get(mid);
                if(interval[0] <= val && val <= interval[1]) {
                    return -1;//if already in interval, we return -1 so we can skip the case in method add(Num)
                }
                if(val > interval[1]) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }

        public int[][] getIntervals() {
            return intervals.toArray(new int[][]{});//注意这种写法,参数必须是一个array实例
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
}
