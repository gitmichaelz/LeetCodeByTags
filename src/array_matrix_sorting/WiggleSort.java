package array_matrix_sorting;

/**
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 */
public class WiggleSort {
    //要保证偶数位(index)小于等于前一位，奇数位大于等于前一位, 如果不满足则要swap一下。swap的结果仍能保证之前的满足条件。可以随便举个例子。
    public void wiggleSort(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            if(i % 2 == 0 && nums[i] > nums[i - 1] || i % 2 == 1 && nums[i] < nums[i - 1]) {
                swap(nums, i, i - 1);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
