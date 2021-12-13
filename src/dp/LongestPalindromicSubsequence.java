package dp;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 */
public class LongestPalindromicSubsequence {
    //dp[i][j] is the LPS between index i and j
    //dp[i][j] = s.charAt(i) == s.charAt(j)? 2 + dp[i + 1][j - 1] : Math.max(dp[i + 1][j], dp[i][j - 1])
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for(int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;//every letter itself is a palindromic with length 1
        }
        //try every possible subsequence
        //note: the scan order is very important, we are scanning from i = st.length() -1 to 0, and j = i  + 1 to st.length() - 1
        //because the equation dp[i][j] = 2 + dp[i + 1][j - 1], we need to calculate dp[i + 1] first, so i is descending, and dp[j - 1]
        //needs us to calculate dp[j - 1] first, so j is ascending.
        for(int i = s.length() - 1; i >= 0; i--) {
            for(int j = i + 1; j < s.length(); j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }
}
