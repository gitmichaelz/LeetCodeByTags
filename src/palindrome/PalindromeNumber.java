package palindrome;

/**
 * Given an integer x, return true if x is palindrome integer.
 *
 * An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 121
 * Output: true
 *
 * Example 2:
 *
 * Input: x = -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 */
public class PalindromeNumber {
    //approach 2. we can construct a number reversely and compare with the original x
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        int newNum = 0;
        int tmp = x;
        while(tmp > 0) {
            newNum = newNum * 10 + tmp % 10;
            tmp /= 10;
        }
        return newNum == x;
    }

    // public boolean isPalindrome(int x) {
    //     if(x < 0) return false;
    //     int div = 1;
    //     while(div <= x/10){
    //         div *= 10;
    //     }
    //     while(x > 0) {
    //         if(x / div != x % 10) return false;
    //         x = (x % div)/10;
    //         div /= 100;
    //     }
    //     return true;
    // }
}
