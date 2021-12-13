package binarySearch;

/**
 *There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 5000
 *     -104 <= nums[i] <= 104
 *     All values of nums are unique.
 *     nums is an ascending array that is possibly rotated.
 *     -104 <= target <= 104
 */
public class SearchInRotatedSortedArray {
    /*
        这道题是二分查找Search Insert Position的变体，看似有点麻烦，其实理清一下还是比较简单的。因为rotate的缘故，当我们切取一半的时候可能会出现误区，所以我们要做进一步的判断。具体来说，假设数组是A，每次左边缘为l，右边缘为r，还有中间位置是m。在每次迭代中，分三种情况：
    （1）如果target==A[m]，那么m就是我们要的结果，直接返回；
    （2）如果A[m]< A[r]，那么说明从m到r一定是有序的（没有受到rotate的影响），那么我们只需要判断target是不是在m到r之间，如果是则把左边缘移到m+1，否则就target在另一半，即把右边缘移到m-1。
    （3）如果A[m]>=A[r]，那么说明从l到m一定是有序的，同样只需要判断target是否在这个范围内，相应的移动边缘即可。
    根据以上方法，每次我们都可以切掉一半的数据，所以算法的时间复杂度是O(logn)，空间复杂度是O(1)。代码如下：
         */
    public int search(int[] nums, int target){
        if(nums == null|| nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(nums[mid] == target) {
                return mid;
            }
            //接下来就是判断哪一边是有序的
            if(nums[mid] < nums[right]){//right half is sorted
                if(target > nums[mid] && target <= nums[right]) {//然后判断是否落在有序的一边
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {//left half is sorted
                if(target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
