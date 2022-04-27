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
    /**
     * idea: sort a string by default in alphabetical order,
     * for example, aab, aabc, aabcd, aabd.
     * we only need to find the first and last string in the array, and find the lcp
     */
    public String longestCommonPrefix(String[] strs) {
        // Argument checks
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];

        Arrays.sort(strs);//或者直接扫一遍找出最大和最小的 str.compareTo(first) < 0 fist = str; str.compareTo(last) > 0, last = str
        char[] first = strs[0].toCharArray();
        char[] last = strs[strs.length - 1].toCharArray();
        int i = 0, len = Math.min(first.length, last.length);
        while (i < len && first[i] == last[i]) i++;
        return strs[0].substring(0, i);
    }
    /**
     * Time: O(S), where S is the sum of all chars in all strings,
     * Space: (1)
     */
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
