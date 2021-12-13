package string;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 */
public class IsomorphicStrings {
    //这个题让你assume 两个字符串same length,面试时要问清楚
    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[256];
        int[] map2 = new int[256];
        for(int i = 0; i < s.length(); i++) {
            char chs = s.charAt(i);
            char cht = t.charAt(i);
            if(map1[chs] != map2[cht]) return false;
            map1[chs] = map2[cht] = i + 1;//let map1[s[i]] = map2[t[i]] = the length already matched
        }
        return true;
    }
}
