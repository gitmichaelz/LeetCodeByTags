package sorting;

/**
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 *
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 *
 * You must solve this problem without using the library's sort function.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class SortColors {
    //two pointer
    public void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int r = 0, w = nums.length - 1, b = nums.length - 1;
        while(r <= w) {//dont't forget "=", because the input may have [1, 2] not having 0
            if(nums[r] == 0) {
                r++;
            } else if (nums[r] == 1) {
                swap(nums, r, w);
                w--;
            } else {
                swap(nums, r, b);
                b--;
                w = b;
            }
        }
    }

    private void swap(int[] nums, int left, int right){
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
