package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i <  nums.length; i++) {
            Integer preIdx = map.put(nums[i], i);
            if(preIdx != null && i - preIdx <= k) {
                return true;
            }
        }

        return false;
    }

    // public boolean containsNearbyDuplicate(int[] nums, int k) {
    //     Set<Integer> set = new HashSet<>();//set winddow with size k
    //     for(int i = 0; i < nums.length; i++){
    //         if(i > k) set.remove(nums[i - k - 1]);
    //         if(!set.add(nums[i])) return true;
    //     }
    //     return false;
    // }
}