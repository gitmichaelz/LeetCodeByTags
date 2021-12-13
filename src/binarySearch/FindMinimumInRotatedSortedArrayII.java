package binarySearch;

/**
 * Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,4,4,5,6,7] might become:
 *
 *     [4,5,6,7,0,1,4] if it was rotated 4 times.
 *     [0,1,4,4,5,6,7] if it was rotated 7 times.
 *
 * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
 *
 * Given the sorted rotated array nums that may contain duplicates, return the minimum element of this array.
 *
 * You must decrease the overall operation steps as much as possible.
 *
 *
 */
public class FindMinimumInRotatedSortedArrayII {
    //worst case : O(N)
    public static int findMin(int[] nums){
        if(nums == null || nums.length == 0) return -1;
        int low = 0, hi = nums.length - 1;
        while(low < hi){
            int mid = low + (hi - low)/2;
            if(nums[mid] < nums[hi]){//min must be in left part, mid included
                hi = mid;
            } else if(nums[mid] > nums[hi]){// nums[mid] > nums[hi] min must be in right part, mid excluded
                low = mid + 1;
            } else {//if  nums[mid] == nums[hi], let hi--;
                hi--;
            }
        }
        return nums[low];//after break while loop, low == hi
    }
}
