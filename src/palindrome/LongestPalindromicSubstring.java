package palindrome;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    //time: N^2, 注意，expand 是O(N)的复杂度
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) return s;
        int start = 0, end = 0, n = s.length();//start, end is the start/end index of the max len parlindrome
        for(int i = 0; i < n; i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int maxLen = Math.max(len1, len2);
            if(maxLen > end - start + 1) {
                start = i - (maxLen - 1)/2; //记住start,end 的计算方法，这样可以覆盖maxLen的奇偶情况
                end = i + maxLen/2;
            }
        }
        return s.substring(start, end + 1);
    }
    private int expand(String s, int start, int end) {
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return end - start - 1;//注意这里是end - start - 1,因为上面while循环结束后， start end在回文之外
    }
}


//dp, but looks like slower than check every substring centered by each element
//dp[i][j] means if substring from index i to j is palindrome
//dp[i][j] = true if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]), we need to calculate dp[i + 1] first, so i is descending, and dp[j - 1] needs to calculate dp[j - 1] first, so j is ascending.
//     public String longestPalindrome(String s) {
//         if(s == null || s.length() == 0) return "";
//         int n = s.length();
//         boolean[][] dp = new boolean[n][n];
//         int maxLen = 1;
//         String res = s.substring(0, 1);
//         for(int i = n - 1; i >= 0; i--) {
//             dp[i][i] = true;
//             for(int j = i + 1; j < n; j++) {
//                 if(s.charAt(i) == s.charAt(j)){
//                     if(j - i == 1 || dp[i + 1][j - 1]) {
//                         dp[i][j] = true;
//                         if(maxLen < j - i + 1) {
//                             maxLen = j - i + 1;
//                             res = s.substring(i, j + 1);
//                         }
//                     }
//                 }
//             }
//         }
//         return res;
//     }
//}



//该题的变形，求最长回文串长度
//dp[i][j] is the LPS between index i and j
//dp[i][j] = s.charAt(i) == s.charAt(j)? 2 + dp[i + 1][j - 1] : Math.max(dp[i + 1][j], dp[i][j - 1])
// public static int longestPalindromeSubseq(String s) {
//     if(s == null || s.length() == 0) return 0;
//     int[][] dp = new int[s.length()][s.length()];
//     for(int i = 0; i < s.length(); i++) {
//         dp[i][i] = 1;//every letter itself is a palindromic with length 1
//     }
//     //try every possible subsequence
//     //note: the scan order is very important, we are scanning from i = st.length() -1 to 0, and j = i  + 1 to st.length() - 1
//     //because the equation dp[i][j] = 2 + dp[i + 1][j - 1], we need to calculate dp[i + 1] first, so i is descending, and dp[j - 1]
//     //needs us to calculate dp[j - 1] first, so j is ascending.
//     for(int i = s.length() - 1; i >= 0; i--) {
//         for(int j = i + 1; j < s.length(); j++) {
//             if(s.charAt(i) == s.charAt(j)) {
//                 dp[i][j] = 2 + dp[i + 1][j - 1];
//             } else {
//                 dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
//             }
//         }
//     }
//     return dp[0][s.length() - 1];
// }
