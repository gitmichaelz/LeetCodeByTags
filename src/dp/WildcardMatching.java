package dp;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
 *
 *     '?' Matches any single character.
 *     '*' Matches any sequence of characters (including the empty sequence).
 *
 * The matching should cover the entire input string (not partial).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aa", p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 *
 * Input: s = "aa", p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 *
 * Input: s = "cb", p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 */
public class WildcardMatching {
    //先用dp,如果要求优化则用non dp

    //non dp approach would be faster
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0, lastMatch_i = 0, starIdx = -1;
        int len1 = s.length(), len2 = p.length();
        while(i < len1){
            if(j < len2 && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if(j < len2 && p.charAt(j) == '*') {
                lastMatch_i = i;//used for restart the match where i = lastMatch_i and * = startIdx
                starIdx = j;
                j++;//only advance j to continue the remaining match
            } else if(starIdx != -1){ //last pattern pointer was *, advancing s pointer
                i = ++lastMatch_i;
                j = starIdx + 1;
            } else {//current pattern pointer is not star, last patter pointer was not *
                //characters do not match
                return false;
            }
        }
        while(j < len2 && p.charAt(j) == '*') j++;
        return j == len2;
    }
//     //dp[i][j]: if the first i chars in s can match first j chars in p
//     //if s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?' dp[i][j] = dp[i - 1][j - 1];
//     //else if(p.charAt(j - 1) == '*')    * can be treated as empty dp[i][j - 1] or current matched character dp[i - 1][j - 1] or
//     //     ####a     ####a*  * treated as empty   dp[i][j] = dp[i][j - 1]
//     //     ####ab    ####a*  * treated as any single character dp[i][j] = dp[i - 1][j - 1]
//     //     ####abc   ####a*  * treated as multiple characters  dp[i][j] = dp[i - 1][j]
//     public boolean isMatch(String s, String p) {
//         if(p == null || p.length() == 0) return s == null || s.length() == 0;
//         int len1 = s.length();
//         int len2 = p.length();
//         boolean[][] dp = new boolean[2][len2 + 1];
//         dp[0][0] = true;//empty string matches empty pattern
//         for(int j = 1; j <= len2; j++) {//if s is empty, dp[0][j] is false if p.charAt(j - 1) is not '*'
//             dp[0][j] = p.charAt(j - 1) == '*' && dp[0][ j - 1];
//         }
//         for(int i = 1; i <= len1; i++) {
//             dp[i % 2][0] = false;
//             for(int j = 1; j <= len2; j++) {

//                 if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
//                     dp[i % 2][j] = dp[(i - 1) % 2][ j - 1];
//                 } else if(p.charAt(j - 1) == '*') {
//                     dp[i % 2][j] = dp[(i - 1) % 2][j] || dp[(i - 1) % 2][j - 1] || dp[i % 2][j - 1];
//                 } else { //p.charAt(j - 1) != '*' '?' && p.charAt(j - 1) != s.charAt(i - 1), we need to set this value as false,
//                     dp[i % 2][j] = false;
//                 }
//             }
//         }
//         return dp[s.length() % 2][p.length()];
//     }
}
