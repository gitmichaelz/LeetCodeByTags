package math;

/**
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 123
 * Output: 321
 *
 * Example 2:
 *
 * Input: x = -123
 * Output: -321
 *
 * Example 3:
 *
 * Input: x = 120
 * Output: 21
 *
 * Example 4:
 *
 * Input: x = 0
 * Output: 0
 */

/**
 * idea: Pop and Push Digits & Check before Overflow
 * We can build up the reverse integer one digit at a time. While doing so, we can check  if we need to append another digit
 * in case of overflow.
 * We want to repeatedly "pop" the last digit off of x and "push" it to the back of the res. In the end, res
 * will be the reverse of the x.
 *
 * time: O(log10(x)), space: O(1)
 */
public class ReverseInteger {
    public int reverse(int x) {
        int res = 0;
        while(x != 0) {
            int pop = x % 10;
            x /= 10;
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if(res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            res = res * 10 + pop;
        }
        return res;
    }
}
