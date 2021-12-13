package dp;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 *
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 *     "AAJF" with the grouping (1 1 10 6)
 *     "KJF" with the grouping (11 10 6)
 *
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 */
public class DecodeWays {
    //dp[i] represents the max number decoding ways
    //at each position i, there are two possible decode ways : single digit and double digit
    //dp[i] = s.charAt(i)=='0' ? 0 : dp[i - 1] single digit
    //dp[i] += dp[i - 2] double digit, note: we need to make sure that s.charAt(i - 1) == '1' || (s.charAt(i) <= '6' && s.charAt(i - 1) == '2')
    //since we are using the two preceding numbers, we can use 2 variable instead of a array with length s.length()
    //lets initialize first element dp[0] = 1, means empty string has 1 way to code, in our code, its a = 1;
    public int numDecodings(String s) {
        if(s == null || s.length() == 0) return 0;
        int a = 1;
        int b = s.charAt(0) == '0'? 0 : 1;

        for(int i = 1; i < s.length(); i++) {
            int cur = s.charAt(i)=='0' ? 0 : b;//ith position decoded as single digit，cur = the one before it i.e cur = b
            if(s.charAt(i - 1) == '1' || (s.charAt(i) <= '6' && s.charAt(i - 1) == '2')) {//decoded as double digit
                cur += a;
            }
            a = b;
            b = cur;
        }
        return b;
    }
}
