package array_matrix_sorting;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
 *
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [23,2,4,6,7], k = 6
 * Output: true
 * Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
 */
public class ContinuousSubarraySum {
    //https://leetcode.com/problems/continuous-subarray-sum/discuss/173165/Java-solution-beats-100
    public boolean checkSubarraySum(int[] nums, int k) {
        k = k == 0 ? Integer.MAX_VALUE : (k < 0 ? -k : k); // make sure k is positive; if k is zero, we won't do mod at all
        if ((nums.length + 2) / 2 > k) return true; // we have (length + 1 > k * 2) prefix sum but k remainder and k positions that the same remainders next to each other, then there is at least two prefix with the same remainder and the distance is larger than one

        Set<Integer> set = new HashSet<>();
        int last = 0; // the prefix sum one element earlier
        for (int num : nums) {
            int cur = (last + num) % k; // get newest prefix sum mod k
            if (set.contains(cur)) return true;
            set.add(last); // add old prefix sum into HashSet
            last = cur; // update old prefix sum
        }
        return false;
    }
}
