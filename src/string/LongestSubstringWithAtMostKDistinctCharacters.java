package string;

/**
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eceba", k = 2
 * Output: 3
 * Explanation: The substring is "ece" with length 3.
 */
public class LongestSubstringWithAtMostKDistinctCharacters {
    //sliding window, similar to https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null) return 0;//坑。只需要判断s == null即可。如果s.length < k; 则返回s.length;
        int[] count = new int[128];
        int numDistinct = 0;
        int maxLen = 0;
        int n = s.length();
        int left = 0;
        for(int right = 0; right < n; right ++){
            char c = s.charAt(right);
            count[c]++;
            if(count[c] == 1) numDistinct++;
            while(numDistinct > k){
                char leftC = s.charAt(left);
                count[leftC]--;
                if(count[leftC] == 0) numDistinct--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}
