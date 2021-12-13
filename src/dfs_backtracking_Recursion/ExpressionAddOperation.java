package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
 *
 * Note that operands in the returned expressions should not contain leading zeros.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1*2*3","1+2+3"]
 * Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
 */
public class ExpressionAddOperation {
//这题两个容易出问题的地方。1. if(i != start && num[start] == '0') break;//这一步重要，不要忘记！！！
    //                      2. if(start == 0), 此时不需要加各种操作符。

    //all possible result, use dfs
//use a variable "curVal" to track the current value, and "expression" to mark the current expression generated by recursion,  and an index of string to track where we are, if we hit the end of string, we compare the current value and the target, if same, we add the expression.
//Note that if we use "*", we need to minus the previous value to eliminate the effect of multiplication( 1 + 2 * 3, first we need to do curVal - 2 + 2 * 3), so we need a "lastOperand" to track the previous operand, one thing to mention is when it is *, the last Operatnd should be updated to lastOperand * cur
//anohter trap is when
//dfs order: "123" => 1 + 2 + 3, 1 + 2 -3, 1 + 2 * 3
// 1 + 23
// 1 - 2 + 3, 1 - 2 - 3, 1 - 2 * 3
// 1 - 23
// 1 * 2 + 3, 1 * 2 - 3, 1 * 2 * 3
// 123

    //we avoid using string.substring() so that we can improve efficiency
    //For people who don't understand the time complexity, T(N) is the time complexity for the function DFS at a particular node (which is pos). For a node, we have three options (add, subtract, multiply) for the incoming positions pos + 1, pos + 2 ... num.length in the for-loop. Therefore, we have the equation T(n) = 3 * T(n-1) + 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1).
//We can simplify it by using the fact that T(n - 1) = 3 * T(n-2) + 3 * T(n-3) + ... + 3 *T(1) such that now T(n) = 3*T(n - 1) + T(n - 1) = 4*T(n - 1)
    //so time coule be 4^n
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0) return res;
        helper(num.toCharArray(), target, res, new StringBuilder(), 0, 0, 0);
        return res;
    }
    //path: current generated path so far
    //start: the position where we start to scan the string num
    //val: current value evaluated by path
    //lastOperand: last operand which we will use in the case of "*", which need to reverse the effect of the expressions's
    //value while considering the multiply operator
    //also we need to consider overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
    public void helper(char[] num, int target, List<String> res, StringBuilder path, int start, long val, long lastOperand) {
        if(start == num.length && val == target) {
            res.add(path.toString());
            return;
        }

        long cur = 0;
        for(int i = start; i < num.length; i++) {
            //edge case: we cannot have numbers with multiple digits started with zero
            //012... or 00123... are not acceptable
            if(i != start && num[start] == '0') break;//这一步重要，不要忘记！！！
            cur = cur * 10 + num[i] - '0';//avoid using cur = Long.parseLong(num.substring(start, i+1)) to improve effciency
            int len = path.length();//store the expression's length, we need to restore the expression once last recurision is done
            if(start == 0) {//edge case, the first one we just need to add it into expression directly, note it is not i == 0
                helper(num, target, res, path.append(cur), i + 1,  cur, cur);
                path.setLength(len);//after recursive call, restore stringbuilder
            } else {
                helper(num, target, res, path.append("+").append(cur), i + 1, val + cur, cur);
                path.setLength(len);
                helper(num, target, res, path.append("-").append(cur), i + 1, val - cur, -cur);
                path.setLength(len);
                //important!!! here lastOperand is updated as lastOpernd * cur
                helper(num, target, res, path.append("*").append(cur), i + 1, val - lastOperand + lastOperand * cur, lastOperand * cur);
                path.setLength(len);
            }
        }
    }
}
