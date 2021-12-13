package array_matrix_sorting;

import java.util.Random;

/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 *
 * You may assume the input array always has a valid answer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 */
public class WiggleSortII {
    //https://leetcode.com/problems/wiggle-sort-ii/discuss/77682/Step-by-step-explanation-of-index-mapping-in-Java
    public void wiggleSort(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int n = nums.length;
        int median = findKthElement(nums, 0, n - 1, (n + 1)/2);
        int i = 0, left = 0, right = n - 1;
        while(i <= right) {
            if(nums[mapedIdx(n, i)] > median) {
                swap(nums, mapedIdx(n, i++), mapedIdx(n, left++));
            } else if(nums[mapedIdx(n, i)] < median) {
                swap(nums, mapedIdx(n, i), mapedIdx(n, right--));
            } else {
                i++;
            }
        }
    }

    private int mapedIdx(int length, int idx) {
        return (2 * idx + 1) % (length | 1);
    }

    private int findKthElement(int[] nums, int left, int right, int k) {
        int random = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, random);
        int pivot = nums[left];
        int start = left + 1, end = right;
        while(start <= end) {
            if(nums[end] < pivot) {
                swap(nums, start, end);
                start++;
            } else {
                end--;
            }
        }
        swap(nums, end, left);
        int m = right - end + 1;
        if(m == k) {
            return nums[end];
        } else if(m > k) {
            return findKthElement(nums, end + 1, right, k);
        } else {
            return findKthElement(nums, left, end - 1, k - m);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
