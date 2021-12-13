package array_matrix_sorting;

import java.util.Arrays;

/**
 * Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * Example 1:
 *
 * Input: nums = [-2,0,1,3], target = 2
 * Output: 2
 * Explanation: Because there are two triplets which sums are less than 2:
 * [-2,0,1]
 * [-2,0,3]
 */
public class ThreeSumSmaller {
    //这题的思路先排好序，然后对每一个元素nums[i], 令start = i + 1, end = nums.length - 1,这样一旦找出符合的triplet, res += end - start (即从start到end之间的肯定都符合)
    public int threeSumSmaller(int[] nums, int target) {
        int result = 0;
        Arrays.sort(nums);
        for(int i = 0; i < nums.length-2; i++){
            int start = i+1, end = nums.length - 1;
            if(nums[i] + nums[i+1] + nums[i+2] >= target)
                break;
            while(start < end){
                if(nums[i] + nums[start] + nums[end] < target){
                    result+= end - start;
                    start++;
                }
                else{
                    end--;
                }
            }
        }
        return result;
    }
}
