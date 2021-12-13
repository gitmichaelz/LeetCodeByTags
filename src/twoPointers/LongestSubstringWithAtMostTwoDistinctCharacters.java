package twoPointers;

/**
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 *
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {
    //几个划窗问题一起看，3，76，159， 239， 340， 424， 480， 567， 995， 1004， 1040
    //sliding window
    //用map或者character-indexed array可以扩展到K distinct characters
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null) return 0;
        int n = s.length();
        int maxLen = 0;
        int numDistinct = 0;//the number of distinct characters
        int[] count = new int[128];//map to store count of each character count[a]
        for(int left = 0, right = 0; right < n; right++){
            char c = s.charAt(right);
            count[c]++;
            if(count[c] == 1) numDistinct++;
            while(numDistinct > 2) {
                char leftC = s.charAt(left);
                count[leftC]--;
                if(count[leftC] == 0) numDistinct--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);//为什么不像划窗问题3一样放在while里面？因为这里是求最大长度。
        }
        return maxLen;
    }
}
