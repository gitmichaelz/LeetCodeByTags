package array_matrix_sorting;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
 *
 * A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:
 *
 *     0 <= i < j < nums.length
 *     |nums[i] - nums[j]| == k
 *
 * Notice that |val| denotes the absolute value of val.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,4,1,5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 */
public class K_DiffPairsInAnArray {
    //O(N), similar to 2 sum, use a map, but need be very careful
    public static int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for(Integer key : map.keySet()) {
            if(k == 0 && map.get(key) > 1 || k > 0 && map.containsKey(key + k)) {
                res++;
            }
        }
        return res;
    }

    //O(logN)
    // public int findPairs(int[] nums, int k) {
    //     Arrays.sort(nums);
    //     int res = 0;
    //     for(int left = 0, right = 1; left < nums.length - 1 && right < nums.length; ){
    //         if(left == right || nums[right] - nums[left] < k) {
    //             right++;
    //         } else if(nums[right] - nums[left] > k) {
    //             left++;
    //         } else {
    //             left++;
    //             res++;
    //             while(left < nums.length - 1 && nums[left] == nums[left - 1]) left++;
    //         }
    //     }
    //     return res;
    // }


    //brute force, O(N^2)
    // public int findPairs(int[] nums, int k) {
    //     if(k < 0) return 0;
    //     Arrays.sort(nums);
    //     int res = 0;
    //     for(int i = 0; i < nums.length - 1; i++) {
    //         if(i > 0 && nums[i] == nums[i - 1]) continue;
    //         for(int j = i + 1; j < nums.length; j++) {
    //             if(j > i + 1 && nums[j] == nums[j - 1]) continue;
    //             if(nums[j] - nums[i] == k) {
    //                 res++;
    //             }
    //         }
    //     }
    //     return res;
    // }
}
