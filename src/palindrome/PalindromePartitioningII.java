package palindrome;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioningII {
    //1d dp, more efficient
    public int minCut(String s) {
        if(s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len];
        //dp[i] is the minimum cuts from index 0 to i
        for(int i = 0; i < len; i++) {
            dp[i] = i;
        }
        for(int i = 0; i < len; i++) {
            expand(s, i, i, dp);
            expand(s, i, i + 1, dp);
        }
        return dp[len - 1];
    }
    //if(s.charAt(start) == s.charAt(end)) dp[end] = Math.min(dp[end], dp[start - 1] + 1), note if start == 0, dp[end] = 0,说明从0到当前end不需要切割
    private void expand(String s, int start, int end, int[] dp){
        while(start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            dp[end] = start == 0? 0 : Math.min(dp[end], 1 + dp[start - 1]);//不要忘记Math.min()
            start--;
            end++;
        }
    }

    //2d array dp, not efficient as above one
//     public int minCut(String s) {
//         if(st == null || st.length() < 2) return 0;
//         int n = st.length();
//         boolean[][] isPalindrome = new boolean[n][n];
//         //cuts[i] is the minimum cuts from index 0 to i
//         int[] cuts = new int[n];
//         for(int i = 0; i < n; i++) {
//             cuts[i] = i;
//             for(int j = 0; j <= i; j++) {
//                 if(st.charAt(j) == st.charAt(i)) {
//                     if(i - j < 2 || isPalindrome[j + 1][i - 1]) {//i - j < 2   a or aa, i == j or i - j == 1
//                         isPalindrome[j][i] = true;
//                         cuts[i] = j == 0 ? 0 : Math.min(cuts[i], 1 + cuts[j - 1]);//1 + cuts[j - 1]意味着把当前元素和他前面的cut,例如当前元素b,和前面的aa切割
//                     }
//                 }

//             }
//         }
//         return cuts[n - 1];
//     }
}
