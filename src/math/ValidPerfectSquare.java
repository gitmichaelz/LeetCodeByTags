package math;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Follow up: Do not use any built-in library function such as sqrt.
 *
 *
 *
 * Example 1:
 *
 * Input: num = 16
 * Output: true
 */
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) { // O(1)
        // Newtons's method.
        // Find square root of num and square it
        // square == num ? true : false;

        long t = num;
        while (t * t > num) {
            t = (t + num / t) / 2;
        }
        return t * t == num;
    }
}
