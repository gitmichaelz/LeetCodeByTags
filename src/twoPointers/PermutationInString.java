package twoPointers;

/**
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 *
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "eidbaooo"
 * Output: true
 * Explanation: s2 contains one permutation of s1 ("ba").
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length()>s2.length()) return false;
        int[] alphabets = new int[26];
        int len =s1.length();
        for(char ch : s1.toCharArray()){
            alphabets[ch-'a']++;
        }
        int cnt =len;
        char[] sArr = s2.toCharArray();

        int start =0;
        int end =0;
        while(end<sArr.length){
            if(alphabets[sArr[end++]-'a']-->0) cnt--;
            while(cnt==0){
                if(end-start == len) return true;
                if(alphabets[sArr[start++]-'a']++==0) cnt++;
            }

        }

        return false;

    }
}
