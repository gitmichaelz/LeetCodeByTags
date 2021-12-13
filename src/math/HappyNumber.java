package math;

/**
 *Write an algorithm to determine if a number n is happy.
 *
 * A happy number is a number defined by the following process:
 *
 *     Starting with any positive integer, replace the number by the sum of the squares of its digits.
 *     Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
 *     Those numbers for which this process ends in 1 are happy.
 *
 * Return true if n is a happy number, and false if not.
 *
 * Example 1:
 *
 * Input: n = 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 *
 * Example 2:
 *
 * Input: n = 2
 * Output: false
 *
 * Constraints:
 *
 *     1 <= n <= 231 - 1
 */
public class HappyNumber {
    //找环，跟在linked list里找环一样，用快慢指针
    //因为根据题意，这里面总有一个环（A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay, 如果是1，那么快fast会一直保持1直到slow也变成1), or it loops endlessly in a cycle which does not include 1. ）
    //等检测到环退出循环，然后判断是否等于一
    public boolean isHappy(int n) {
        int slow = n;
        int fast = getDigitSquareSum(n);
        while(slow != fast){
            slow = getDigitSquareSum(slow);
            fast = getDigitSquareSum(getDigitSquareSum(fast));
        }
        return slow == 1;
    }

    private int getDigitSquareSum(int n){
        int sum = 0;
        while(n > 0){
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
