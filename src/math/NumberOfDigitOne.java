package math;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: 6
 *
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 */
public class NumberOfDigitOne {
    //看solution的解释 https://leetcode.com/problems/number-of-digit-one/solution/
    //consider the 1s in one's place, ten's place, hundred's place...
    //ones place: there is 1 one in every 10 numbers, so we need to count how many 10 are there: n/10,
    //then we need to count the ones digit, if n % 10 != 0, then + 1.
    //tens place: there are 10 ones in every 100 numbers, so we need to count how many 100 are thre: n / 100;
    //then we need to multoiple 10, n/100 *10, then we need to know the reminder of n % 100, compared with 10, for example, 1234, n % 100 = 34, 34 > 10, so we need to plus 10, so basically, it is min(max(n % i * 10 - i + 1, 0), i)
    public int countDigitOne(int n) {
        int ones = 0;
        for(long k = 1; k <= n; k *= 10){//注意要用long类型，因为下面的k * 10有可能溢出
            long divisor = k * 10;
            ones += (n / divisor) * k + Math.min(Math.max(n % divisor - k + 1, 0), k);
        }
        return ones;
    }
}
