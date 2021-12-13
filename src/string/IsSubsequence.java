package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
 *
 * A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 */
public class IsSubsequence {
//naive and fastest!!
    // public boolean isSubsequence(String s, String t){
    //     if(t == null || t.length() == 0) return s == null || s.length() == 0;
    //     if(t.length() < s.length()) return false;
    //     int i = 0;
    //     for(int j = 0; i < s.length() && j < t.length(); j++){//dont foget check i < s.length() incase s is empty()
    //         if(s.charAt(i) == t.charAt(j)){
    //             i++;
    //         }
    //     }
    //     return i == s.length();
    // }


    //follow up: if there are lots of s to compare, we can preprocess t, record every chars and their counts in t.
    // then for each chars in s, we check if there exist proper index in t,
    public boolean isSubsequence(String s, String t){
        if(t == null || t.isEmpty()) return s == null || s.isEmpty();
        if(t.length() < s.length()) return false;
        Map<Character, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char c = t.charAt(i);
            List<Integer> indexList = map.getOrDefault(c, new ArrayList<>());
            indexList.add(i);
            map.put(c, indexList);
        }

        int preIndex = -1;
        for(char c : s.toCharArray()){
            if(!map.containsKey(c)) return false;
            preIndex = binarySearch(map.get(c), preIndex);
            if(preIndex == -1) return false;
        }
        return true;
    }
    //find the fist element in list that greater than preIndex, for example: list: [1, 3, 5], preIndex = 1, return 3
    private int binarySearch(List<Integer> list, int preIndex){
        int start = 0, end = list.size() - 1;
        while(start < end){
            int mid = start + (end - start)/2;
            if(list.get(mid) > preIndex){
                end = mid;
            } else if(list.get(mid) < preIndex){
                start = mid + 1;
            } else {
                return mid == end? -1 : list.get(mid + 1);
            }
        }
        return list.get(start) > preIndex? list.get(start) : -1;//注意是返回list中的元素，即char在t中的index
    }



    //dp[i][j] represent if the first i characters in s is a subsequence of first j characters in t
    //if(s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1]
    //else dp[i][j] = dp[i][j - 1]
    //we can use a 2 row array(we cannot use 1d array since it will overwrite the j - 1)
    // public boolean isSubsequence(String s, String t) {
    //     if(t == null || t.length() == 0) return s == null || s.length() == 0;
    //     if(t.length() < s.length()) return false;
    //     boolean[][] dp = new boolean[2][t.length() + 1];//since t is potentially a very long string, so let's make the array's length as short as possible
    //     for(int j = 0; j <= t.length(); j++) {
    //         dp[0][j] = true;
    //     }
    //     for(int i = 1; i <= s.length(); i++){
    //         dp[i % 2][0] = false;//important: dont forget this step, because for any empty t, we cannot get an subsequence with length > 0, if
    //         //we dont set false, it will get from previous row's value which may not right
    //         for(int j = 1; j <= t.length(); j++) {
    //             if(s.charAt(i - 1) == t.charAt(j - 1)) {
    //                 dp[i % 2][j] = dp[(i - 1) % 2][j - 1];
    //             } else {
    //                 dp[i % 2][j] = dp[i % 2][j - 1];
    //             }
    //         }
    //     }
    //     return dp[s.length() % 2][t.length()];
    // }
}
