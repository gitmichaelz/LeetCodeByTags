package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: expression = "2-1-1"
 * Output: [0,2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 *
 * Constraints:
 *
 *     1 <= expression.length <= 20
 *     expression consists of digits and the operator '+', '-', and '*'.
 *     All the integer values in the input expression are in the range [0, 99].
 */
public class DifferentWaysToAddParentheses {
    // //idea: scan across the input string, each time we hit an operator, we split the string into two parts by
    // //the operator, recursively solve the left/right part, and calculate the final result by the left value/
    // //right value and the operator, if we dont hit an operator, we just add the value of the input string

    //optimization for the commented solution: use a global map to store the string and coresponding list of values, so we dont have to calculate for the same string each time
    //one thing need to be taken care of is, there are a lot of repetitive computations, for example :3 + 2 * 5 - 3 - 9 * 10, say we hit the second '-', so left "3 + 2 * 5 - 3" and right is "9 * 10" , and in the left part, we need to recusively compute each substirng splited by each operator, which are already processed in prior steps, so if we can use a map to store the computed string and value, it would be improve the performance
    Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String input) {
        if(map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> res = new ArrayList<>();
        if(input == null || input.isEmpty()) return res;//不要忘记这一步
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for(Integer leftVal : left) {
                    for(Integer rightVal : right) {
                        if(c == '+') {
                            res.add(leftVal + rightVal);
                        } else if(c == '-') {
                            res.add(leftVal - rightVal);
                        } else {
                            res.add(leftVal * rightVal);
                        }
                    }
                }
            }
        }
        if(res.isEmpty()) {//we don't hit any operator so far, so we need to add the int val of input string
            res.add(Integer.valueOf(input));
        }
        map.put(input, res);
        return res;
    }

    // //idea: scan across the input string, each time we hit an operator, we split the string into two parts by
    // //the operator, recursively solve the left/right part, and calculate the final result by the left value/
    // //right value and the operator, if we dont hit an operator, we just add the value of the input string
    // public List<Integer> diffWaysToCompute(String input) {
    //     List<Integer> res = new ArrayList<>();
    //     if(input == null || input.length() == 0) return res;
    //     for(int i = 0; i < input.length(); i++) {
    //         char c = input.charAt(i);
    //         if(c == '+' || c == '-' || c == '*') {
    //             List<Integer> left = diffWaysToCompute(input.substring(0, i));
    //             List<Integer> right = diffWaysToCompute(input.substring(i + 1));
    //             for(Integer leftVal : left) {
    //                 for(Integer rightVal : right) {
    //                     if(c == '+') {
    //                         res.add(leftVal + rightVal);
    //                     } else if(c == '-') {
    //                         res.add(leftVal - rightVal);
    //                     } else {
    //                         res.add(leftVal * rightVal);
    //                     }
    //                 }
    //             }
    //         }
    //     }
    //     if(res.isEmpty()) {//we don't hit any operator so far, so we need to add the int val of input string
    //         res.add(Integer.valueOf(input));
    //     }
    //     return res;
    // }
}
