package binarySearch;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    //关于binary search 看段公子的这篇 https://www.zybuluo.com/smilence/note/182
    public int[] searchRange(int[] nums, int target){
        int low = -1, hi = -1;
        if(nums == null || nums.length == 0) return new int[]{low, hi};
        int left = 0, right = nums.length - 1;
        //find low boundary
        while(left < right) {
            int mid = left + (right - left)/2;//closer to left
            if(target <= nums[mid]) {//search left half, mid included
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if(target == nums[left]) {
            low = left;
        } else {
            return new int[]{low, hi};
        }

        //find hi boundary
        right = nums.length - 1;
        while(left < right) {
            int mid = right - (right - left)/2;//closer to right
            if(target >= nums[mid]) {//search right half, mid included
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        hi = left;
        return new int[]{low, hi};
    }
}
