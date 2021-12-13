package greedy;

/**
 * Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5]
 * Output: true
 * Explanation: Any triplet where i < j < k is valid.
 */
public class IncreasingTripletSubsequence {
    // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
    public boolean increasingTriplet(int[] nums){
        if(nums == null || nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for(int n : nums){
            if(n <= first) {
                first = n;
            } else if(n <= second){
                second = n;
            } else {//n is larger than both smaller
                return true;
            }
        }
        return false;//we didnot find such number that larger than both smaller ones
    }
}
