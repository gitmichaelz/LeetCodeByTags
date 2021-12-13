package math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a positive integer n, you can apply one of the following operations:
 *
 *     If n is even, replace n with n / 2.
 *     If n is odd, replace n with either n + 1 or n - 1.
 *
 * Return the minimum number of operations needed for n to become 1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 8
 * Output: 3
 * Explanation: 8 -> 4 -> 2 -> 1
 *
 * Example 2:
 *
 * Input: n = 7
 * Output: 4
 * Explanation: 7 -> 8 -> 4 -> 2 -> 1
 * or 7 -> 6 -> 3 -> 2 -> 1
 */
public class IntegerReplacement {
    private Map<Integer,Integer> memo = new HashMap<>();

    public int integerReplacement(int n) {
        if (n == 1) return 0;
        if (memo.containsKey(n)) return memo.get(n);
        int res = 0;
        if (n % 2 == 0) res = integerReplacement(n/2) + 1;
        else res = Math.min(integerReplacement(n/2 + 1), integerReplacement(n/2)) + 2;
        memo.put(n, res);
        return res;
    }
}
