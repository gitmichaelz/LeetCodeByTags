package string;

/**
 * A parentheses string is valid if and only if:
 *
 *     It is the empty string,
 *     It can be written as AB (A concatenated with B), where A and B are valid strings, or
 *     It can be written as (A), where A is a valid string.
 *
 * You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
 *
 *     For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
 *
 * Return the minimum number of moves required to make s valid.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "())"
 * Output: 1
 */
public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String s) {
        if(s == null || s.length() == 0) return 0;
        int invalidOpen = 0;
        int invalidClose = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') {
                invalidOpen++;
            } else {
                if(invalidOpen == 0) {
                    invalidClose++;
                } else {
                    invalidOpen--;
                }
            }
        }
        return invalidOpen + invalidClose;
    }
}
