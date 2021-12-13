package math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 *
 * Input: n = 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 */
public class FactorialTrailingZeroes {
    //10 is the product of 2 and 5. In n!, we need to know how many 2 and 5, and the number of zeros is the minimum of the number of 2 and the number of 5.

    //Since multiple of 2 is more than multiple of 5, the number of zeros is dominant by the number of 5.
    //这题就是计算因数5的个数
    //注意到最大整数n! = 2147483647!
    //=2 * 3 * ...* 5 ... *10 ... 15* ... * 25 ... * 50 ... * 125 ... * 250...
    //=2 * 3 * ...* 5 ... * (5^1*2)...(5^1*3)...*(5^2*1)...*(5^2*2)...*(5^3*1)...*(5^3*2)... (Equation 1)
    //因此我们需要计算因数5的个数， 25的个数， 125的个数，等等，即return n/5 + n/25 + n/125...
    //注意，计算因素5的个数时，对于25，我们也提供了一个5，也就是25实际额外只提供了一个5， 计算到125的时候，他也只额外提供了一个5，以此类推。。。
    public int trailingZeroes(int n) {
        int res = 0;
        while(n >= 5){
            n /= 5;
            res += n;
        }
        return res;
    }
}
