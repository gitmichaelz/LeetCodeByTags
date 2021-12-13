package palindrome;

/**
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aba"
 * Output: true
 */
public class ValidPalindromeII {
    public boolean validPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        char[] array = s.toCharArray();
        while (start < end) {
            if (array[start] == array[end]) {
                start ++;
                end --;
            } else {
                return isPalindrome(array, start + 1, end) || isPalindrome(array, start, end - 1);
            }
        }
        return true;
    }

    private boolean isPalindrome(char[] array, int start, int end) {
        while (start < end) {
            if (array[start] == array[end]) {
                start ++;
                end --;
            } else {
                return false;
            }
        }
        return true;
    }
}
