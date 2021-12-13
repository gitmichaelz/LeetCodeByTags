package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    /**
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could
     * represent. Return the answer in any order.
     *
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to
     * any letters.
     *
     * Example 1:
     *
     * Input: digits = "23"
     * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
     *
     * Example 2:
     *
     * Input: digits = ""
     * Output: []
     *
     * Example 3:
     *
     * Input: digits = "2"
     * Output: ["a","b","c"]
     *
     *
     *
     * Constraints:
     *
     *     0 <= digits.length <= 4
     *     digits[i] is a digit in the range ['2', '9'].
     */
    //time: O(N^K), N is the possible char vals for each digits， k is the number of digit in string digits
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits == null || digits.isEmpty()) return res;
        String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrack(res, digits, map, new StringBuilder(), 0);
        return res;
    }

    private void backtrack(List<String> res, String digits, String[] map, StringBuilder sb, int idx) {
        if(idx == digits.length()) {//其实根据题目描述(digit不会是0和1)，也可以判断sb.length() == digits.length()
            res.add(sb.toString());
            return;
        }
        int digit = digits.charAt(idx) - '0';//这个一开始忘了，直接写成map[idx]了，傻逼了。。。
        String letters = map[digit];
        for(char ch : letters.toCharArray()) {
            sb.append(ch);
            backtrack(res, digits, map, sb, idx + 1);
            sb.deleteCharAt(sb.length() - 1);//backtracking + pruning, remove the newly added element and try next one
        }
    }

}
