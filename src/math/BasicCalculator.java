package math;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 * Example 1:
 *
 * Input: s = "1 + 1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: s = " 2-1 + 2 "
 * Output: 3
 *
 * Example 3:
 *
 * Input: s = "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 3 * 105
 *     s consists of digits, '+', '-', '(', ')', and ' '.
 *     s represents a valid expression.
 *     '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
 *     '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
 *     There will be no two consecutive operators in the input.
 *     Every number and running calculation will fit in a signed 32-bit integer.
 */
public class BasicCalculator {
    public int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        int sign = 1;
        int res = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int cur = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))){
                    cur = cur * 10 + s.charAt(++i) - '0';
                }
                res += cur * sign;//坑，第一遍做的时候忘记这一步.遇到非digit的字符，我们要计算result
            } else if(c == '+'){
                sign = 1;
            } else if(c == '-'){
                sign = -1;
            } else if(c == '('){
                stack.push(res);//遇到左括号入栈，以后备用
                stack.push(sign);
                res = 0;//reset res and sign
                sign = 1;
            } else if(c == ')'){
                res = stack.pop() * res + stack.pop();//first pop is to get the sign
            }
        }
        return res;
    }
}
