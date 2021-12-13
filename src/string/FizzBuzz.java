package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return a string array answer (1-indexed) where:
 *
 *     answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 *     answer[i] == "Fizz" if i is divisible by 3.
 *     answer[i] == "Buzz" if i is divisible by 5.
 *     answer[i] == i (as a string) if none of the above conditions are true.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["1","2","Fizz"]
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            if(i % 15 == 0){
                res.add("FizzBuzz");
            } else if(i % 3 == 0){
                res.add("Fizz");
            } else if(i % 5 == 0){
                res.add("Buzz");
            } else {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }
    /*
    //follow up, 如果问如何扩展
    public List<String> fizzBuzz(int n) {
        if (n <= 0) {
            return new ArrayList();
        }
        TreeMap<Integer, String> map = new TreeMap<>((a, b) -> a - b); // define the order of how you want to concat the words here using lambda comparator
        map.put(3, "Fizz");
        map.put(5, "Buzz");
        // map.put(7, "Yuzz");
        // map.put(9, "Mozz"); add more encoding options here ...
        List<String> numsStrs = new LinkedList();
        for (int i = 1; i <= n; i++) {
            StringBuilder encoded = new StringBuilder();
            for (int num : map.keySet()) {
                if (i % num == 0) encoded.append(map.get(num));
            }
            numsStrs.add((encoded.length() == 0) ? String.valueOf(i) : encoded.toString());
        }
        return numsStrs;
    }*/
}
