package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,-1,5,-2,3], k = 3
 * Output: 4
 * Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
 */
public class MaximumSizeSubarraySumEqualsK {
    public int maxSubArrayLen(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();//store preSum and current index
        int preSum = 0, maxLen = 0;
        map.put(0, -1);//坑，don't forget this initialization, this is the base case, when we have preSum = k, we will use it
        for(int i = 0; i < nums.length; i++){
            preSum += nums[i];
            if(map.containsKey(preSum - k)){
                maxLen = Math.max(maxLen, i - map.get(preSum - k));
            }
            map.putIfAbsent(preSum, i);// keep only 1st duplicate as we want first index as left as possible
        }
        return maxLen;
    }
}
