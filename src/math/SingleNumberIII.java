package math;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 *
 * Example 2:
 *
 * Input: nums = [-1,0]
 * Output: [-1,0]
 */
public class SingleNumberIII {
    //https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C%2B%2BJava-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations
    public int[] singleNumber(int[] nums){
        int diff = 0;
        for(int n : nums){
            diff ^= n;
        }
        //after above step, diff is actually the xor of the two unique numbers a, b
        // so there must be at lest a bit from which the two numbers differ from each other, we need to find such digit in the diff, here we are finding the rightmost set bit (with value 1)
        //to get the rightmost reset bit, for example. diff = 0101, mask = 0001    (0101 & 1011 = 0001)
        int mask = diff & -diff;//或者diff & （~diff + 1);二者等价，因为负数的二进制即正数的补码，即反码 + 1
        int[] res = new int[2];
        //second pass, we let n & mask, we will divide all numbers into two groups,
        //assume the ith digit is 1, then on ith digit, we have two groups, one groupe with ith digit = 0, the other with ith digit = 1, and all dup numbers also will become zero in two groups after ^ operation
        for(int n : nums){
            if((n & mask) == 0){//the bit is not set
                res[0] ^= n;
            } else {
                res[1] ^= n;
            }
        }
        return res;
    }
}
