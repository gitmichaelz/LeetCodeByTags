package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] map = new int[26];
        for(char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for(char c : t.toCharArray()) {
            map[c - 'a']--;
            if(map[c - 'a'] < 0) return false;
        }
        //actuvally, no need to scan map and check its element, cause we already compared length and element
        // for(int n : map) {
        //     if(n != 0) return false;
        // }
        return true;
    }

    //follow up
    public boolean isAnagramFollowUp(String s, String t) {
        if(s.length() != t.length()) return false;
        Map<Character, Integer> map = new HashMap<>();
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for(char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) - 1);
            if(map.get(c) < 0) return false;
        }
        return true;
    }
}
