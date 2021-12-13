package dfs_backtracking_Recursion;

import java.util.Stack;

/**
 * Given a string s represents the serialization of a nested list, implement a parser to deserialize it and return the deserialized NestedInteger.
 *
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "324"
 * Output: 324
 * Explanation: You should return a NestedInteger object which contains a single integer 324.
 *
 * Example 2:
 *
 * Input: s = "[123,[456,[789]]]"
 * Output: [123,[456,[789]]]
 */

public class MiniParser {
    public NestedInteger deserialize(String s) {
        if (!s.startsWith("[")) {
            return new NestedInteger(Integer.valueOf(s));
        }
        Stack<NestedInteger> stack = new Stack<>();
        NestedInteger res = new NestedInteger();
        stack.push(res);
        int start = 1;
        for (int i = 1; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '[') {
                NestedInteger ni = new NestedInteger();
                stack.peek().add(ni);
                stack.push(ni);
                start = i + 1;
            } else if (c == ',' || c == ']') {
                if (i > start) {
                    Integer val = Integer.valueOf(s.substring(start, i));
                    stack.peek().add(new NestedInteger(val));
                }
                start = i + 1;
                if (c == ']') {
                    stack.pop();
                }
            }
        }
        return res;
    }
    // public NestedInteger deserialize(String s) {
    //     NestedInteger ret = new NestedInteger();
    //     if (s == null || s.length() == 0) return ret;
    //     if (s.charAt(0) != '[') {
    //         ret.setInteger(Integer.parseInt(s));
    //     }
    //     else if (s.length() > 2) {
    //         int start = 1, count = 0;
    //         for (int i = 1; i < s.length(); i++) {
    //             char c = s.charAt(i);
    //             if (count == 0 && (c == ',' || i == s.length() - 1)) {
    //                 ret.add(deserialize(s.substring(start, i)));
    //                 start = i + 1;
    //             }
    //             else if (c == '[') count++;
    //             else if (c == ']') count--;
    //         }
    //     }
    //     return ret;
    // }
}
