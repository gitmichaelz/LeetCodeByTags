package binarySearch;

/**
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,7,9,10], k = 1
 * Output: 5
 * Explanation: The first missing number is 5.
 */
public class MissingElementInSortedArray {
    public int missingElement(int[] nums, int k) {
        int len = nums.length;
        int lo = 0, hi = len - 1;
        int countMissing = nums[len - 1] - nums[0] + 1 - len;

        if (countMissing < k) {
            return nums[len - 1] + k - countMissing;
        }

        while (lo < hi - 1) {
            int mid = lo + (hi - lo) / 2;
            int newCountMissing = nums[mid] - nums[lo] - (mid - lo);

            if (newCountMissing >= k) {
                hi = mid;
            } else {
                k -= newCountMissing;
                lo = mid;
            }
        }

        return nums[lo] + k;
    }
}
