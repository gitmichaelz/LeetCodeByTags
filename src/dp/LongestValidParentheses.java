package dp;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 *
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 *
 * Constraints:
 *
 *     0 <= s.length <= 3 * 10^4
 *     s[i] is '(', or ')'.
 */
public class LongestValidParentheses {
    //note: ())(()) this longest valid parenthese is 4
    //      ()(()) this longest valid parenthese is 6
    //dp[i] represents that length of current longest valid parenthese ending at i
    //we use a "open" var to mark the number of '(', when we hit a ')', we decrease open
    //if open>= 0, we found a match and dp[i] = dp[i - 1] + 2; also we need to add the previous match because till now dp[i] is from current open, and there might be valid parenthese before this one. see"()(())", 020024, at index 5, we have dp[5] = dp[4] + 2 = 4; we also need to add dp[5 - dp[5]] = 2 so dp[5] += dp[1] = 6
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];//注意，这里一定要 + 1， 否则在计算dp[i] += dp[i - dp[i]]时有可能角标越界，比如"()"，因为我们要处理的dp[i]是长度，所以i角标要向右shift一位
        int max = 0;
        int open = 0;
        for(int i = 1; i <= s.length(); i++) {
            if(s.charAt(i - 1) == '('){
                open++;
            } else {
                if(--open >=0) {//find a match
                    dp[i] = dp[i - 1] + 2;
                    dp[i] += dp[i - dp[i]];
                    max = Math.max(max, dp[i]);
                } else {
                    open = 0;//不要忘记这个，()) 保证open不为负
                }
            }
        }
        return max;
    }
}
