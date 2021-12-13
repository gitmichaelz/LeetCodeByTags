package dp;

/**
 * Given an integer n, return the count of all numbers with unique digits, x, where 0 <= x < 10n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99
 */
public class CountNumbersWithUniqueDigits {
    /**
     Let f(n) = count of number with unique digits of length n.
     f(1) = 10. (0, 1, 2, 3, ...., 9)
     f(2) = 9 * 9. Because for each number i from 1, ..., 9, we can pick j to form a 2-digit number ij and     there are 9 numbers that are different from i for j to choose from.
     f(3) = f(2) * 8 = 9 * 9 * 8. Because for each number with unique digits of length 2, say ij, we can       pick k to form a 3 digit number ijk and there are 8 numbers that are different from i and j for k to     choose from.
     Similarly f(4) = f(3) * 7 = 9 * 9 * 8 * 7....
     ...
     f(10) = 9 * 9 * 8 * 7 * 6 * ... * 1
     f(11) = 0 = f(12) = f(13)....
     any number with length > 10 couldn't be unique digits number.
     */
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) return 1;
        int res = 10;//case n == 1
        for(int availableNumber = 9, unique = 9; availableNumber > 0 && n > 1; n--, availableNumber--) {//snce we are processing case n > 2, so availbaleNumber = 9
            unique = unique * availableNumber;
            res += unique;
        }
        return res;
    }
}
