package divideAndConquer;

/**
 * Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaabb", k = 3
 * Output: 3
 * Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    //divide and conquer, 0ms, beat 100%
    public int longestSubstring(String s, int k) {
        if(s == null || s.isEmpty()) return 0;
        char[] chars = s.toCharArray();
        return helper(chars, 0, chars.length -1, k);
    }
    private int helper(char[] chars, int start, int end, int k){
        if(end - start + 1 < k) return 0;
        int[] count = new int[26];
        for(int i = start; i <= end; i++){//注意一定是在start和end的range里面搜
            count[chars[i] - 'a']++;
        }
        for(int i = start; i <= end; i++){
            if(count[chars[i] - 'a'] < k){//the split point
                int j = i + 1;
                while(j <= end && count[chars[j] - 'a'] < k) j++;//find the first char with count >=k,
                return Math.max(helper(chars, start, i - 1, k), helper(chars, j, end, k));
            }
        }
        return end - start + 1;
    }
}
