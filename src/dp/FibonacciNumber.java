package dp;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 *
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 */
public class FibonacciNumber {
    //f(0) = 0, f(1) = 1
    public int fib(int N){
        if(N < 2) return N;//N in the range[0, 30]
        int a = 0, b = 1;
        int c = a + b;
        for(int i = 2; i <= N; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
