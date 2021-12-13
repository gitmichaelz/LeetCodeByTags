package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string expression representing arbitrarily nested ternary expressions, evaluate the expression, and return the result of it.
 *
 * You can always assume that the given expression is valid and only contains digits, '?', ':', 'T', and 'F' where 'T' is true and 'F' is false. All the numbers in the expression are one-digit numbers (i.e., in the range [0, 9]).
 *
 * The conditional expressions group right-to-left (as usual in most languages), and the result of the expression will always evaluate to either a digit, 'T' or 'F'.
 *
 *
 *
 * Example 1:
 *
 * Input: expression = "T?2:3"
 * Output: "2"
 * Explanation: If true, then result is 2; otherwise result is 3.
 */
public class TernaryExpressionParser {
    public String parseTernary(String expression) {
        if (expression == null || expression.length() == 0)
            return expression;

        Deque<String> stack = new ArrayDeque<>();
        for (int i = expression.length() - 1; i >= 0; i--) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == 'T' || c == 'F')
                stack.push(String.valueOf(c));
            else if (c == '?') {
                boolean question = expression.charAt(--i) == 'T' ? true : false;
                String first = stack.pop(), second = stack.pop();
                stack.push(question ? first : second);
            }
        }
        return stack.pop();
    }
}
