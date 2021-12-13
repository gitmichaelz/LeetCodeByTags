package math;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 *
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 */
public class MissingNumber {
    //xor ^= i ^ nums[i],最后会找出那个missing number
    //每个位置i理应对应nums[i]应该是i，比如【0， 1， 2， 3】
    //因此我们让他们xor一遍会找出missing number
    public int missingNumber(int[] nums) {
        int missing = 0;
        for(int i = 0; i < nums.length; i++){
            missing ^= i ^ nums[i];
        }
        return missing ^ nums.length;//因为i只取到num.length - 1，我们有n + 1个数，所以把位置n（第n + 1个）也放进去
    }
}
