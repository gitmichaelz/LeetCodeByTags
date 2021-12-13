package math;

/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 */
public class SingleNumber {
    //一个数异或自己一次为0，然后一个数异或0这个数不变。
    public int singleNumber(int[] nums){
        if(nums == null || nums.length == 0) return -1;
        int res = 0;
        for(int num : nums){
            res ^= num;
        }
        return res;
    }
}
