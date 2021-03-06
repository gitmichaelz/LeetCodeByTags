package math;

/**
 * You are given two strings s and t.
 *
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 *
 * Return the letter that was added to t.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", t = "abcde"
 * Output: "e"
 * Explanation: 'e' is the letter that was added.
 */
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        int n = t.length();
        char c = t.charAt(n - 1);
        for (int i = 0; i < n - 1; ++i) {
            c ^= s.charAt(i);
            c ^= t.charAt(i);
        }
        return c;
    }
}
