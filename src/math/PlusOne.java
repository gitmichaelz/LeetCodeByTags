package math;

/**
 * You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
 *
 * Increment the large integer by one and return the resulting array of digits.
 *
 *
 *
 * Example 1:
 *
 * Input: digits = [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * Incrementing by one gives 123 + 1 = 124.
 * Thus, the result should be [1,2,4].
 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0) return digits;
        int carry = 1;
        for(int i = digits.length - 1; i >=0; i--){
            int digit = (digits[i] + carry) % 10;
            carry = (digits[i] + carry) / 10;
            digits[i] = digit;
            if(carry == 0) return digits;
        }
        //if we have not return the digits, means carry is still 1, we need one more digit
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }
}
