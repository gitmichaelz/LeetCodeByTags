package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * iven a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 *     Open brackets must be closed by the same type of brackets.
 *     Open brackets must be closed in the correct order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 */
public class ValidParentheses {
    /*
        stack ：eque<Integer>  stack = new ArrayDeque<>(); stack.push(); stack.pop(); stack.peek();  不允许null值插入
        queue: Deque<Integer> queue = new ArrayDeque<>(); queue.offer(); queue.poll(); queue.peek(); 不允许null值插入
        */
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if(c == ')' || c == ']' || c == '}') {
                if(stack.isEmpty()) return false;
                char open = stack.pop();
                if((open == '(' && c != ')') || (open == '[' && c != ']') ||
                        (open == '{' && c != '}')) return false;
            }
        }
        return stack.isEmpty();
    }
}
