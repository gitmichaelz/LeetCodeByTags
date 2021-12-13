package binarySearch;

/**
 * A peak element is an element that is strictly greater than its neighbors.
 *
 * Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * You must write an algorithm that runs in O(log n) time.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 */
public class FindPeakElement {
    //二分，找下界，
    //总是assume first part is sorted in ascending order, 因为nums[-1] 可视为负无穷大，
    //if nums[mid] > nums[mid + 1], we continue to search the front (first part), mid included.
    //otherwise we search the second half, mid excluded
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] > nums[mid + 1]){//descending, the peak must be in first part, mid included
                hi = mid;
            }else {//ascending, the peak must be in second part, mid excluded
                lo = mid + 1;
            }
        }
        return lo;//这个题是返回index
    }
}
