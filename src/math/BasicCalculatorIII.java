package math;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1+1"
 * Output: 2
 *
 * Example 2:
 *
 * Input: s = "6-4/2"
 * Output: 4
 *
 * Example 3:
 *
 * Input: s = "2*(5+5*2)/3+(6/2+8)"
 * Output: 21
 */
public class BasicCalculatorIII {
    //在II的基础上稍作改动，当遇到左括号后，递归计算左括号内的值，遇到右括号，先把当前值通过计算后入栈，然后退出while循环，然后计算栈内元素和返回，其他操作和II一样。
    int idx = 0;
    public int calculate(String s){
        if(s == null || s.isEmpty()) return 0;
        char operator = '+';
        Deque<Integer> stack = new LinkedList<>();
        int curVal = 0;
        if(!s.endsWith("+")){
            s += '+';
        }
        while(idx < s.length()){
            char c = s.charAt(idx++);
            if(Character.isDigit(c)){
                curVal = curVal * 10 + c - '0';
            } else if(c == '('){
                curVal = calculate(s);//递归计算括号内值
            } else if(c != ' '){//c为'+' '-' '*' '/' 或者 ')'
                if(operator == '+'){
                    stack.push(curVal);
                } else if(operator == '-'){
                    stack.push(-curVal);
                } else if(operator == '*'){
                    stack.push(stack.pop() * curVal);
                } else if(operator == '/'){
                    stack.push(stack.pop() / curVal);
                }
                if(c == ')') break;//退出while
                curVal = 0;
                operator = c;
            }
        }
        int sum = 0;
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }
}
