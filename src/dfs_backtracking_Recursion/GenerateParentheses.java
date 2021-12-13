package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    /**
     *Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
     *
     *
     *
     * Example 1:
     *
     * Input: n = 3
     * Output: ["((()))","(()())","(())()","()(())","()()()"]
     *
     * Example 2:
     *
     * Input: n = 1
     * Output: ["()"]
     *
     *
     *
     * Constraints:
     *
     *     1 <= n <= 8
     */
    //we have n parenthesis, and n right parenthesis
    //we use dfs to generate parenthesis, if number of left parenthesis > 0, we add a left parenthesis
    //if right parenthesis > 0, we add a right parenthesis
    public List<String> generateParenthesis(int n){
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, sb, n, n);
        return res;
    }

    private void helper(List<String> res, StringBuilder sb, int left , int right){
        if(left == 0 && right == 0) {
            res.add(sb.toString());
            return;
        }
        if(left > 0) {
            sb.append('(');
            helper(res, sb, left - 1, right);
            sb.deleteCharAt(sb.length() - 1);//backtracking + pruning, remove the left one and generate the right one
        }
        if(right > left) {//if right > left, we can always safe to add right parenthesis
            sb.append(')');
            helper(res, sb, left, right - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
