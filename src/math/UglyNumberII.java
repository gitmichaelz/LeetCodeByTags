package math;

/**
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return the nth ugly number.
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 */
public class UglyNumberII {
    public int nthUglyNumber(int n) {
        if(n <= 1) return n;
        int[] k = new int[n];
        k[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for(int i = 1; i < n; i++) {
            //generate ugly number by multiply all the 3 factors，the next ugly number must be built from a smaller ugly number
            k[i] = Math.min(2 * k[p2], Math.min(3 * k[p3], 5*k[p5]));
            //move index for the current minimum ugly number
            if(k[i] == 2 * k[p2]) p2++;
            if(k[i] == 3 * k[p3]) p3++;//注意，我们这里不用else,而是要判断每个数，因为6 = 2 * 3， 3 * 2
            if(k[i] == 5 * k[p5]) p5++;
        }
        return k[n - 1];
    }
}
