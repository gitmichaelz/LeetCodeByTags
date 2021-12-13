package palindrome;

/**
 * Given a palindromic string of lowercase English letters palindrome, replace exactly one character with any lowercase English letter so that the resulting string is not a palindrome and that it is the lexicographically smallest one possible.
 *
 * Return the resulting string. If there is no way to replace a character to make it not a palindrome, return an empty string.
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, a has a character strictly smaller than the corresponding character in b. For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ is at the fourth character, and 'c' is smaller than 'd'.
 *
 *
 *
 * Example 1:
 *
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 * Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
 * Of all the ways, "aaccba" is the lexicographically smallest.
 */
public class BreakAPalindrome {
    //check half of the string
    //replace a non 'a' character to 'a'.
    //if all 'a's, replace the last char to 'b'
    //Time O(N)
    //Space O(N)
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() < 2) return "";
        char[] s = palindrome.toCharArray();
        int len = s.length;

        for (int i = 0; i < len/2; i++) {
            if (s[i] != 'a') {
                s[i] = 'a';
                return String.valueOf(s);
            }
        }
        s[len - 1] = 'b';
        return String.valueOf(s);
    }
}
