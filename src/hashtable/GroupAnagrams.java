package hashtable;
/**
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 *
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= strs.length <= 104
 *     0 <= strs[i].length <= 100
 *     strs[i] consists of lowercase English letters.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    //we use the sorted char array of each str as a key, for each str in strs, we check if its sorted char array exists in map
    //if yes, add to corresponding list, if not, create a new list and add cur str to the list, and put the sorted char array and list into map
    //这两种方法都要掌握
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] count = new char[26];//另一种构造键字符串的方式
            for(char c : str.toCharArray()) {
                count[c - 'a']++;
            }
            String key = String.valueOf(count);
            List<String> anagramsList = map.computeIfAbsent(key, k -> new ArrayList<>());
            anagramsList.add(str);
        }
        return new ArrayList<>(map.values());
    }

    // public List<List<String>> groupAnagrams1(String[] strs) {
    //     Map<String, List<String>> map = new HashMap<>();
    //     for(String str : strs) {
    //         char[] arr = str.toCharArray();
    //         Arrays.sort(arr);
    //         String key = String.valueOf(arr);
    //         List<String> anagramsList = map.computeIfAbsent(key, k -> new ArrayList<>());
    //         anagramsList.add(str);
    //     }
    //     return new ArrayList<>(map.values());
    // }
}
