package math;

/**
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,3,2]
 * Output: 3
 */
public class SingleNumberII {
    // public int singleNumber(int[] nums){
    //     int res = 0;
    //     for(int i = 0; i < 32; i++){
    //         int sum = 0;
    //         for(int num : nums) {
    //             sum += (num >> i) & 1;//这一步是按位取数字，然后求和
    //         }
    //         sum %= 3;
    //         res |= (sum << i);//把bits数组转化为结果，注意不要写错。是bits[i]<<i;
    //     }
    //     return res;
    // }

    //参考这个题的solution中的approach 3
    //我们需要表示三个状态，让一个bit经过三次状态转换后变为0，需要用2个bit来表示三个状态
    public int singleNumber(int[] nums){
        // first appearence:
        // add num to seen_once
        // don't add to seen_twice because of presence in seen_once

        // second appearance:
        // remove num from seen_once
        // add num to seen_twice

        // third appearance:
        // don't add to seen_once because of presence in seen_twice
        // remove num from seen_twice
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }
        return ones;
    }
}
