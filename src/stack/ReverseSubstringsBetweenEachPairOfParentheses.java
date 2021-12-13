package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a string s that consists of lower case English letters and brackets.
 *
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 *
 * Your result should not contain any brackets.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(abcd)"
 * Output: "dcba"
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
    //black magic, 黑魔术，这个太牛逼了！！！
    //虫洞 https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/discuss/383670/JavaC%2B%2BPython-Tenet-O(N)-Solution
    public String reverseParentheses(String s) {
        int len = s.length();
        int[] pair = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        for(int i = 0; i < len ; i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else if(s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0, d = 1; i < len; i += d) {
            if(s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = pair[i];
                d = -d;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }


    //Straightforward
    //time: O(N^2), space: O(N)
    // public String reverseParentheses(String s) {
    //     Deque<StringBuilder> stack = new LinkedList<>();
    //     stack.push(new StringBuilder());//level 0
    //     for(char c : s.toCharArray()) {
    //         if(c == '(') {//a new level
    //             stack.push(new StringBuilder());
    //         } else if(c == ')') {//reverse deeper level and append to cur level on ')'
    //             StringBuilder deeperLevel = stack.pop().reverse();
    //             stack.peek().append(deeperLevel);
    //         } else {
    //             stack.peek().append(c);//simply append chars to cur level
    //         }
    //     }
    //     return stack.pop().toString();
    // }
}
