package dp;

/**
 * Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
 *
 *     '.' Matches any single character.
 *     '*' Matches zero or more of the preceding element.
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
 * Input: s = "aa", p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 *
 * Input: s = "ab", p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 *
 * Input: s = "aab", p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
 *
 * Example 5:
 *
 * Input: s = "mississippi", p = "mis*is*p*."
 * Output: false
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 20
 *     1 <= p.length <= 30
 *     s contains only lowercase English letters.
 *     p contains only lowercase English letters, '.', and '*'.
 *     It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
 */
public class RegularExpressionMatching {
    //'*' asterisk [ˈæstərɪsk]
    //这个题主要就是考虑当p[j - 1] == '*'时，如何分类。有三种，<1>a*当做一个空串 <2> a*当做一个单独的a <3> a* 当做多个a
    public boolean isMatch(String s, String p) {
        if (p == null || p.length() == 0) return (s == null || s.length() == 0);
        int len1 = s.length();
        int len2 = p.length();
        //dp[i][j] represents if first i chars in s  can match first j chars in p
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;//empty string matches empty pattern
        //when s is emtpy, either p is empty, or p is like "a*b*c*"
        for(int j = 2; j <=len2; j += 2) {
            dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];//不要忘记判断 dp[0][j - 2]
        }
        //1. if s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'    dp[i][j] = dp[i - 1][j - 1]
        //2. else if (p.charAt(j - 1) == *

        //     1> if (p.charAt(j - 2) != '.' && p.charAt(j - 2) != s.charAt(i - 1))    dp[i][j] = dp[i][j - 2]; j > 2;  a* counts as empty
        //     2> if(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')
        //          2.1> dp[i][j] = dp[i][j - 2], j> 2,  a* counts as empty
        //          2.2> dp[i - 1][j - 2]  a* as single a
        //          2.3> dp[i - 1][j] a* as multiple a,即如果是匹配多个a,则看看dp[i - 1][j]能否匹配多个a
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j<= len2; j++)  {
                if(s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (j >= 2 && p.charAt(j - 1) == '*'){//dont forget j >= 2
                    if(p.charAt(j - 2) != '.' && p.charAt(j - 2) != s.charAt(i - 1)){
                        dp[i][j] = dp[i][j - 2];//a* counts as empty
                    } else {
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
                    }
                }
            }
        }
        return dp[len1][len2];
    }
}
