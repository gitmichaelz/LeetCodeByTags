package string;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 *
 *     s = s1 + s2 + ... + sn
 *     t = t1 + t2 + ... + tm
 *     |n - m| <= 1
 *     The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 *
 * Note: a + b is the concatenation of strings a and b.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 *
 */
public class InterleavingString {
    //1d array, faster than 2d approach
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        if(len3 != len1 + len2) return false;
        if(len1 * len2 == 0) return s3.equals(s1) || s3.equals(s2);
        boolean[] dp = new boolean[len2 + 1];
        dp[0] = true;
        for(int j = 1; j <= len2; j++) {//when s1 empty, initialize dp array
            dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for(int i = 1; i <= len1; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);//when j = 0, initialize new row with dp[0]
            for(int j = 1; j <= len2; j++) {
                dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                        dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        return dp[len2];
    }

//     // dp[i][j] means whether first i chars in s1 and first j chars in s2 are interleaving in first i + j chars in S3
//     //if s1.charAt(i - 1) == s3.charAt(i + j - 1)  dp[i][j] = dp[i - 1][j]
//     //if s2.charAt(j - 1) == s3.charAt(i + j - 1)  dp[i][j] |= dp[i][j - 1]
    // public boolean isInterleave(String s1, String s2, String s3) {
    //     if(s1.length() + s2.length() != s3.length()) return false;
    //     boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
    //     dp[0][0] = true;//If 'm' and 'n' are empty, we can always find an empty string that will be interleaving them
    //     //case 1: s2 empty, check s1 with s3
    //     for(int i = 1; i <= s1.length(); i++) {
    //         dp[i][0] = s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0];
    //     }
    //     //case 2: s1 empty, check s2 with s3
    //     for(int i = 1; i <= s2.length(); i++) {
    //         dp[0][i] = s2.charAt(i - 1) == s3.charAt(i - 1) && dp[0][i - 1];
    //     }
    //     for(int i = 1; i <= s1.length(); i++) {
    //         for(int j = 1; j <= s2.length(); j++) {
    //             if(s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
    //                 dp[i][j] = dp[i - 1][j];
    //             }
    //             if(s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
    //                 dp[i][j] |= dp[i][j - 1];
    //             }
    //         }
    //     }
    //     return dp[s1.length()][s2.length()];
    // }
}
