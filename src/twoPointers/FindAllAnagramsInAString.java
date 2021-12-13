package twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 */
public class FindAllAnagramsInAString {
    //sliding window
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.isEmpty() || s.length() < p.length()) return res;
        int[] lettermap = new int[26];//since string consists of lowercase letters only
        for(char c : p.toCharArray()){
            lettermap[c - 'a']++;
        }
        for(int left = 0, right = 0, count = p.length(); right < s.length(); ) {
            char r_ch = s.charAt(right);
            if(lettermap[r_ch - 'a']-- > 0){
                count--;
            }
            right++;
            while(count == 0){//means we already found a sequence from left to right that has same letters with p
                if(right - left == p.length()){
                    res.add(left);
                }
                char l_ch = s.charAt(left);
                if(lettermap[l_ch - 'a']++ == 0){
                    count++;
                }
                left++;
            }
        }
        return res;
    }
}
