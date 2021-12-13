package math;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return true if n is an ugly number.
 *
 * Example 1:
 *
 * Input: n = 6
 * Output: true
 * Explanation: 6 = 2 × 3
 *
 * Example 2:
 *
 * Input: n = 8
 * Output: true
 * Explanation: 8 = 2 × 2 × 2
 */
public class UglyNumber {
    public boolean isUgly(int num) {
        if(num == 1) return true;
        if(num == 0) return false;
        while(num % 2 == 0) num /= 2;
        while(num % 3 == 0) num /= 3;
        while(num % 5 == 0) num /= 5;
        return num == 1;
    }
}
