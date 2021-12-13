package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We can shift a string by shifting each of its letters to its successive letter.
 *
 *     For example, "abc" can be shifted to be "bcd".
 *
 * We can keep shifting the string to form a sequence.
 *
 *     For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 *
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 */
public class GroupShiftedStrings {
    //先把string转换成字符数组，然后计算美国字符数组第一个值相对于'a'的offset,
    //然后根据得出的offset把字符数组转换成按'a'开始 比如      "gij"得出offset = g-a = 6, 则它可以转换为"acd"
    //g - 6 = a,  i - 6 = c,  j - 6 = d， 然后转化为字符串key存入map
    public List<List<String>> groupStrings(String[] strings) {
        if(strings == null || strings.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            char[] arr = s.toCharArray();
            int offset = arr[0] - 'a';
            for(int i = 0; i < arr.length; i++){
                arr[i] -= offset;
                if(arr[i] < 'a'){
                    arr[i] += 26;
                }
            }
            String key = String.valueOf(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());//map.values return Collection<List<String>>
    }
}
