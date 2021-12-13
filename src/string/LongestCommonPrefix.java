package string;

import java.util.Arrays;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 *
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 * Constraints:
 *
 *     1 <= strs.length <= 200
 *     0 <= strs[i].length <= 200
 *     strs[i] consists of only lower-case English letters.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        // Argument checks
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        StringBuilder sb = new StringBuilder();
        Arrays.sort(strs);
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        for (int i = 0, j = 0; i < first.length && j < last.length; i++, j++) {
            if (first[i] != last[j]) break;
            sb.append(first[i]);
        }
        return sb.toString();
    }
    // public String longestCommonPrefix(String[] strs){
    //     if(strs == null || strs.length == 0) return "";
    //     String prefix = strs[0];
    //     for(int i = 1; i < strs.length; i++){
    //         while(!strs[i].startsWith(prefix)) {
    //             prefix = prefix.substring(0, prefix.length() - 1);
    //         }
    //         if(prefix.isEmpty()) return prefix;//terminate early
    //     }
    //     return prefix;
    // }
}
