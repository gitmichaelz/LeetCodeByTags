package dfs_backtracking_Recursion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a pattern and a string s, return true if s matches the pattern.
 *
 * A string s matches a pattern if there is some bijective mapping of single characters to strings such that if each character in pattern is replaced by the string it maps to, then the resulting string is s. A bijective mapping means that no two characters map to the same string, and no character maps to two different strings.
 *
 *
 *
 * Example 1:
 *
 * Input: pattern = "abab", s = "redblueredblue"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "red"
 * 'b' -> "blue"
 *
 * Example 2:
 *
 * Input: pattern = "aaaa", s = "asdasdasdasd"
 * Output: true
 * Explanation: One possible mapping is as follows:
 * 'a' -> "asd"
 */
public class WordPatternII {
    //dfs
    //这题注意一个优化是在遍历str时，我们只需要找到 k <= str.length() - reminingLen即可。
    //note: we use a hash set to avoid duplicate matches, if character a matches string "red", then character b cannot be used to match "red"
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();//dedupe
        return match(pattern, 0, str, 0, map, set);
    }

    private boolean match(String pattern, int i, String str, int j, Map<Character, String> map, Set<String> set){
        if(i == pattern.length() && j == str.length()) return true;
        if(i == pattern.length() || j == str.length()) return false;
        char c = pattern.charAt(i);
        if(map.containsKey(c)){
            String s = map.get(c);
            if(!str.startsWith(s, j)) return false;//注意，这里不能用if(!s.equals(str.substring(j, j + s.length())))，否则会有string index out of bounds exception
            return match(pattern, i + 1, str, j + s.length(), map, set);
        }
        //if pattern character does not exist in the map
        int remainingLen = pattern.length() - i;//这个优化让运行时间从33m减少到1ms
        for(int k = j; k <= str.length() - remainingLen; k++){//note: 这里有个小优化，不必让k到str.length(), 我们要让剩下的字符和不能少于pattern剩下的未匹配字符尝试匹配
            String candidate = str.substring(j, k + 1);//注意index 是k + 1
            if(set.contains(candidate)) continue;//to avoid duplicate math, if 'a' already matches 'red', 'b' cannot match it
            map.put(c, candidate);
            set.add(candidate);
            //continue to match the rest
            if(match(pattern, i + 1, str, k + 1, map, set)) return true;
            map.remove(c);
            set.remove(candidate);
        }
        return false;
    }
}
