package palindrome;

/**
 * Given a string s, return the number of palindromic substrings in it.
 *
 * A string is a palindrome when it reads the same backward as forward.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        if(s == null || s.length() == 0) return 0;
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            count += countPalindrome(s, i, i);
            count += countPalindrome(s, i, i + 1);
        }
        return count;
    }
    private int countPalindrome(String s, int start, int end) {
        int count = 0;
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
            count++;
        }
        return count;
    }

    // dp, but  slower than above solution
    // public int countSubstrings(String s) {
    //     if(s == null || s.length() == 0) return 0;
    //     int n = s.length();
    //     boolean[][] dp = new boolean[n][n];
    //     int res = 0;
    //     for(int i = n - 1; i >= 0; i--) {
    //         dp[i][i] = true;
    //         res++;
    //         for(int j = i + 1; j < n; j++) {
    //             if(s.charAt(i) == s.charAt(j)) {
    //                 if(j - i == 1 || dp[i + 1][j - 1]) {
    //                     dp[i][j] = true;
    //                     res++;
    //                 }
    //             }
    //         }
    //     }
    //     return res;
    // }
}
