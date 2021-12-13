package math;

/**
 * Given an integer n, return true if it is a power of three. Otherwise, return false.
 *
 * An integer n is a power of three, if there exists an integer x such that n == 3x.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 27
 * Output: true
 */
public class PowerOfThree {
    /*
        It follows that 3^X == N
        It follows that log (3^X) == log N
        It follows that X log 3 == log N
        It follows that X == (log N) / (log 3)
        For the basis to hold, X must be an integer.
        In Java, we check if a number is an integer by taking the decimal part (using % 1) and checking if it is 0.
         */
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        double res = Math.log10(n) / Math.log10(3);
        return res % 1 == 0;
    }
    //brute force
    //public boolean isPowerOfThree(int n) {
    // if(n <= 0) return false;
    // while(n % 3 == 0) {
    //     n / = 3;
    // }
    // return n == 1;
    //}
}
