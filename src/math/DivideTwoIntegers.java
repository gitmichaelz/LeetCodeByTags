package math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 *
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 *
 * Return the quotient after dividing dividend by divisor.
 *
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 *
 *
 *
 * Example 1:
 *
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 *
 * Example 2:
 *
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 */
public class DivideTwoIntegers {
    ///take dividend = 0011 1110, divisor = 11 as example
    //since we know every integer can be denote the form like: num = a_0 * 2^0 + a_1 * 2^1 + a_2 * 2^2 +...+ a_n * 2^n   (a_i is 0 or 1)
    //so left shift by 1 means the num * 2.
    //so let's left shift the divisor until <= dividend, then we know there are 1 << (shift - 1) 个
    public int divide(int dividend, int divisor){
        //edge case, 如果dividend是Integer.MIN_value,(1000 0000 0000 0000 0000 0000...), 被-1除的话会溢出
        if(dividend == 1 << 31 && divisor == -1) return (1 << 31) - 1;//dont miss (), (1 << 31)
        int a  = Math.abs(dividend);
        int b = Math.abs(divisor);
        int res = 0;
        for(int x = 31; x >= 0; x--){//let a right shift x digit until a - b >= 0
            //一定注意计算顺序，不要忘记括号，a >>> x - b, 会先计算x - b, 所以要想先计算位移，要加括号(a >>> x) - b
            if((a >>> x) - b >= 0){//dont forget add (), (a >>> x), the first case meets this condition is that x will be 4 where 0011 - 11 >= 0, then x = 2,
                res += (1 << x);
                a -= (b << x);//update a, and try a's lower bits
            }
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }
}
