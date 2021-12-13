package stack;

import java.util.*;

/**
 * Given a string formula representing a chemical formula, return the count of each atom.
 *
 * The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
 *
 * One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
 *
 *     For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
 *
 * Two formulas are concatenated together to produce another formula.
 *
 *     For example, "H2O2He3Mg4" is also a formula.
 *
 * A formula placed in parentheses, and a count (optionally added) is also a formula.
 *
 *     For example, "(H2O2)" and "(H2O2)3" are formulas.
 *
 * Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
 *
 *
 *
 * Example 1:
 *
 * Input: formula = "H2O"
 * Output: "H2O"
 * Explanation: The count of elements are {'H': 2, 'O': 1}.
 */
public class NumberOfAtoms {
    public String countOfAtoms(String formula){
        Deque<Map<String, Integer>> stack = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        int i = 0, n = formula.length();
        while(i < n){
            char c = formula.charAt(i++);
            if(c == '('){
                stack.push(map);
                map = new HashMap<>();
            } else if(c == ')'){
                int val = 0;
                while(i < n && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i++) - '0';
                }
                if(val == 0) val = 1;
                if(!stack.isEmpty()){
                    Map<String, Integer> tmp = map;
                    map = stack.pop();
                    for(String key : tmp.keySet()){
                        map.put(key, map.getOrDefault(key, 0) + tmp.get(key) * val);
                    }
                }
            } else{
                int start = i - 1;
                while(i < n && Character.isLowerCase(formula.charAt(i))){
                    i++;
                }
                String name = formula.substring(start, i);
                int val = 0;
                while(i < n && Character.isDigit(formula.charAt(i))){
                    val = val * 10 + formula.charAt(i++) - '0';
                }
                if(val == 0) val = 1;
                map.put(name, map.getOrDefault(name, 0) + val);
            }
        }
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(String key : list){
            sb.append(key);
            if(map.get(key) > 1){
                sb.append(map.get(key));
            }
        }
        return sb.toString();
    }
}
