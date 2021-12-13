package math;

/**
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 * Example 1:
 *
 * Input: left = 5, right = 7
 * Output: 4
 *
 * Example 2:
 *
 * Input: left = 0, right = 0
 * Output: 0
 *
 * Example 3:
 *
 * Input: left = 1, right = 2147483647
 * Output: 0
 */
public class BitwiseANDofNumbersRange {
    //approach 1. find the common prefix of binary form of numbers in the range [m, n]
    //for example, [9, 12] => {1001, 1010, 1011, 1100}, the common prefix is 1000
    public int rangeBitwiseAnd(int m, int n){
        int shift = 0;
        while(m != n){//left shift m and n until they are equal to each other, mark how many bits we shift
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }

    //approach 2, another way to find the common prefix is to use n & (n - 1) to turn off the rightmost 1 in binary form until we have m <= n;
    // public int rangeBitwiseAnd(int m, int n) {
    //     while(m < n){
    //         n = n & (n - 1);
    //     }
    //     return m & n;
    // }

    //以下方法在数据量大的时候回TLE
    // public int rangeBitwiseAnd(int m, int n) {
    //     int res = m;
    //     while(m <= n){
    //         res &= m++;
    //     }
    //     return res;
    // }
}
