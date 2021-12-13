package string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string s, find if s follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 */
public class WordPattern {
    //为了防止map.containsValue(),可以额外用一个set来存储value
    public boolean wordPattern(String pattern, String str) {
        Map<Character,String> map = new HashMap<>();
        int len = pattern.length();
        String[] words = str.split(" ");
        if(words.length != len){
            return false;
        }
        Set<String> set = new HashSet<>();
        for(int i = 0; i < len; i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(words[i])) return false;
            } else {
                if(!set.add(words[i])) return false;
                map.put(c, words[i]);
            }
        }
        return true;
    }
    // public boolean wordPattern(String pattern, String str) {
    //     if(str == null || str.isEmpty()) return false;
    //     String[] arr = str.split(" ");
    //     Map<Character, String> map = new HashMap<>();
    //     if(pattern.length() != arr.length) return false;
    //     for(int i = 0; i < arr.length; i++){
    //         char c = pattern.charAt(i);
    //         if(map.containsKey(c)){
    //             if(!map.get(c).equals(arr[i])) return false;
    //         } else {
    //             if(map.containsValue(arr[i])) return false;
    //             map.put(c, arr[i]);
    //         }
    //     }
    //     return true;
    // }
}
