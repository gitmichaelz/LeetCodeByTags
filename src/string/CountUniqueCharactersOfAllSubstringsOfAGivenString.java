package string;

import java.util.Arrays;

/**
 * Let's define a function countUniqueChars(s) that returns the number of unique characters on s.
 *
 * For example if s = "LEETCODE" then "L", "T", "C", "O", "D" are the unique characters since they appear only once in s,
 * therefore countUniqueChars(s) = 5.
 *
 * Given a string s, return the sum of countUniqueChars(t) where t is a substring of s.
 *
 * Notice that some substrings can be repeated so in this case you have to count the repeated ones too.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABC"
 * Output: 10
 * Explanation: All possible substrings are: "A","B","C","AB","BC" and "ABC".
 * Evey substring is composed with only unique letters.
 * Sum of lengths of all substring is 1 + 1 + 1 + 2 + 2 + 3 = 10
 */
public class CountUniqueCharactersOfAllSubstringsOfAGivenString {
    //DP
    //https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/discuss/129021/O(N)-Java-Solution-DP-Clear-and-easy-to-Understand
    public int uniqueLetterString(String s) {
        int res = 0;
        if (s == null || s.isEmpty()) return res;
        int[] lastSeenPos = new int[26];
        int[] contribution = new int[26];
        Arrays.fill(lastSeenPos, -1);
        int len = s.length();
        for (int i = 0, cur = 0; i < len; i++) {//cur: unique letters in substring ending at i
            int idx = s.charAt(i) - 'A';
            cur -= contribution[idx];
            contribution[idx] = i - lastSeenPos[idx];
            cur += contribution[idx];
            lastSeenPos[idx] = i;
            res += cur;
        }
        return res;
    }

    // public int uniqueLetterString(String s) {
    //     int[][] indices = new int[26][2];
    //     for (int i = 0; i < 26; i++) {
    //         Arrays.fill(indices[i], -1);
    //     }
    //     int res = 0, len = s.length();
    //     for (int i = 0; i < len; i++) {
    //         int c = s.charAt(i) - 'A';
    //         res += (i - indices[c][1]) * (indices[c][1] - indices[c][0]);
    //         indices[c] = new int[] {indices[c][1], i};
    //     }
    //     for (int c = 0; c < 26; c++) {
    //         res += (len - indices[c][1]) * (indices[c][1] - indices[c][0]);
    //     }
    //     return res;
    // }
}
