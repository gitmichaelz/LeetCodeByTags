package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 */
public class EvaluateReversePolishNotation {
    //这两种形式都要会写
    // public int evalRPN(String[] tokens){
    //     if(tokens == null || tokens.length == 0) return 0;
    //     Deque<Integer> stack = new ArrayDeque<>();
    //     for(String token : tokens) {//因为题目说tokens总是有效的，即总能正确计算出一个结果
    //         if(token.equals("+")) {//注意这里是字符串"+"
    //             stack.push(stack.pop() + stack.pop());
    //         } else if(token.equals("-")) {
    //             stack.push(-stack.pop() + stack.pop());
    //         } else if(token.equals("*")) {
    //             stack.push(stack.pop() * stack.pop());
    //         } else if(token.equals("/")) {
    //             int n1 = stack.pop(), n2 = stack.pop();
    //             stack.push(n2 / n1);
    //         } else {
    //             stack.push(Integer.valueOf(token));
    //         }
    //     }
    //     return stack.pop();
    // }

    public int evalRPN(String[] tokens) {
        if(tokens == null || tokens.length == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(String token : tokens) {
            switch(token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int n1 = stack.pop(), n2 = stack.pop();
                    stack.push(n2 / n1);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
}
