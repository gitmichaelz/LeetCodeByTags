package twoPointers;

import java.util.Arrays;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 *
 * Example 2
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1
 *
 *

 Constraints:
 0 <= s.length <= 5 * 104
 s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    //几个划窗问题一起看，3，76，159， 239， 340， 424， 480， 567， 995， 1004， 1040
    //two points, i is the starting point, j is the ending point, use j to scan the string, if set already contians the cur char, we remove the leading chars in s ending with the first occurrence of ch.
    //one pass, 用一个整数数组map来记录每个字符以及它上次被访问的位置(index)，并维护当前的left指针(即包含non_repeated的substring的起始位置）如果碰到重复的（map[right] != -1）并且map[cur_c] >= left; update left = map[cur_c] + 1;
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) return 0;
        int[] map = new int[128];//map[i]: is the last visited position of char i
        Arrays.fill(map, -1);//defaulting index to -1
        int maxLen = 0;
        for(int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if(map[c] != -1 && map[c] >= left) {//一定要判断map[c] >= left, 例如abacdba,当right指针在第二个a时，left = 2, 可以这样理解:left 以及left右边的字符串是nonrepeating的，如果当前字符c出现在其任意位置，都需要left = map[c] + 1, map[c] 记录这c上一次被访问的位置。
                //而此时map[a] == 0, 则不比对left进行更新，如果更新反而出错。
                left = map[c] + 1;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            map[c] = right;
        }
        return maxLen;
    }


    //2 pass
    // public int lengthOfLongestSubstring(String s) {
    //     if(s == null || s.isEmpty()) return 0;
    //     int[] count = new int[128];
    //     int maxLen = 0;
    //     for(int i = 0, j = 0; j < s.length(); j++) {
    //         char c = s.charAt(j);
    //         while(count[c] != 0) {
    //             count[s.charAt(i++)]--;
    //         }
    //         count[c]++;
    //         maxLen = Math.max(maxLen, j - i + 1);
    //     }
    //     return maxLen;
    // }
}
