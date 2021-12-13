package dp;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 *     Insert a character
 *     Delete a character
 *     Replace a character
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 */
public class EditDistance {
    //dp[i][j]: min distance of first i chars in word1 and first j chars in word2
    // 1> if(s1[i - 1] == s2[j - 1]) dp[i][j] = dp[i - 1][j - 1];
    // 2> if(s1[i - 1] != s2[j - 1])
    //          a> dp[i][j] = dp[i - 1][j] + 1//s1里面需要加一位才能匹配s2，因此要匹配s1的前i - 1位和当前s2
    //			b> dp[i][j] = dp[i][j - 1] + 1//s1里面需要减一位才能匹配s2, 因此要匹配当前s1和s2的前j - 1位
    //		    c> dp[i][j] = dp[i - 1][j - 1] + 1//需要替换一位，匹配各自的前一位，再加一
    //          然后我们再从以上三种情况中选最少的， Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]),dp[i - 1][j - 1]) + 1;
    public int minDistance(String word1, String word2) {
        if(word1.length() < word2.length()) return minDistance(word2, word1);//we always assume word1.length() >= word2.length();
        if(word2.isEmpty()) return word1.length();
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for(int i = 0; i < dp.length; i++) {
            dp[i] = i;//for 0 char in word1, there will be i steps to first i chars in word2
        }
        for(int i = 1; i <= m; i++) {
            int[] tmp = new int[n + 1];
            tmp[0] = i;//dont forget this step, we are matching the first i chars in word1 to 0 char in word2
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    tmp[j] = dp[j - 1];
                }else {
                    tmp[j] = Math.min(Math.min(tmp[j - 1], dp[j]), dp[j - 1]) + 1;
                }
            }
            dp = tmp;//update dp to the latest one
        }
        return dp[n];
    }
    //2d dp
//     public int mindDistance(Stirng s1, String s2) {
//         int[][] dp = new int[s1.length()+1][s2.length()+1];

//         // if s2 is empty, we can remove all the characters of s1 to make it empty too
//         for(int i1=0; i1 <= s1.length(); i1++)
//           dp[i1][0] = i1;

//         // if s1 is empty, we have to insert all the characters of s2
//         for(int i2=0; i2 <= s2.length(); i2++)
//           dp[0][i2] = i2;

//         for(int i1=1; i1 <= s1.length(); i1++) {
//             for(int i2=1; i2 <= s2.length(); i2++) {
//             // If the strings have a matching character, we can recursively match for the remaining lengths
//                 if(s1.charAt(i1-1) == s2.charAt(i2-1))
//                     dp[i1][i2] = dp[i1-1][i2-1];
//                 else
//                     dp[i1][i2] = 1 + Math.min(dp[i1-1][i2], //delete
//                                 Math.min(dp[i1][i2-1], //insert
//                                          dp[i1-1][i2-1])); //replace
//             }
//         }
//         return dp[s1.length()][s2.length()];
//     }
}
