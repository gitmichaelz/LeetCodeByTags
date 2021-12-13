package string;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 *     It is the empty string, contains only lowercase characters, or
 *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *     It can be written as (A), where A is a valid string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 */
public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        if(s == null || s.isEmpty()) return s;
        char[] chs = s.toCharArray();
        int n = s.length();
        //find invalid close Parentheses
        int open = 0;
        for(int i = 0; i < n; i++) {
            if(chs[i] == '(') {
                open++;
            } else if(chs[i] == ')') {
                if(open == 0) {
                    chs[i] = '*';
                } else {
                    open--;
                }
            }
        }
        //mark any extra open parenthese
        //important!!! Must scan from right to left
        for(int i = n - 1; i >= 0; i--) {
            if(chs[i] == '(') {
                if(--open >= 0) {
                    chs[i] = '*';
                } else {
                    break;
                }
            }
        }

        int idx = 0;
        for(int i = 0; i < n; i++) {
            if(chs[i] != '*') {
                chs[idx++] = chs[i];
            }
        }
        return String.valueOf(chs, 0, idx);
    }
}
