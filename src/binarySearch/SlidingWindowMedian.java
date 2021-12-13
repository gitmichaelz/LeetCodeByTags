package binarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle values.
 *
 *     For examples, if arr = [2,3,4], the median is 3.
 *     For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
 *
 * You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 *
 * Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
 */
public class SlidingWindowMedian {
    //用一个arraylist来存储window k范围内的元素，通过二叉搜索寻找被插入元素在arraylist里的位置，以保证arraylist里的元素是有序的，这样可以方便的计算median
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] res = new double[n - k + 1];//since k is always less than nums.length
        if(k <= 0) return res;
        List<Integer> sortedList = new ArrayList<>();//sorted list with at most k elements
        int p = 0;
        for(int i = 0; i < nums.length; i++){
            if(sortedList.size() == k){
                int index = findInsertionPosition(sortedList, nums[i - k]);
                sortedList.remove(index);
            }
            int index = findInsertionPosition(sortedList, nums[i]);
            sortedList.add(index, nums[i]);
            if(sortedList.size() == k){
                res[p] = sortedList.get(sortedList.size()/2);
                if(k % 2 == 0){
                    res[p] += sortedList.get(sortedList.size()/2 - 1);
                    res[p] /= 2.0;
                }
                p++;
                //注意，不要写成下面的形式，否则当 arr = [2147483647,2147483647], k = 2时，会出现median等于-1的情况，栈溢出
//                if(k % 2 == 1){
//                    res[p] = sortedList.get(sortedList.size()/2);
//                }else {
//                    res[p] = (sortedList.get(sortedList.size()/2) + sortedList.get(sortedList.size()/2 - 1))/2.0;
//                }
            }
        }
        return res;
    }
    private int findInsertionPosition(List<Integer> list, int target){
        int lo = 0, hi = list.size();//insertion position could be between[0, list.size()]
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(list.get(mid) < target){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
