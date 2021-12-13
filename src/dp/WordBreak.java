package dp;
/**
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 */

import java.util.*;

public class WordBreak {
    //dp[i] represent if first i characters in s can be segmented into a space-separated sequence of one or more dict words
    //dp[i] = true if wordDict.contains(s.subsequence(j, i)) && dp[j ]
    //a faster version of DP
    //1ms
    public boolean wordBreakDP2(String s, List<String> wordDict){
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;//0 character or empty string can always be a subsequence of dict words
        for(int i = 0; i < s.length(); i++){
            if(!dp[i]) continue;
            for(String word : wordDict){
                if(s.startsWith(word, i)){
                    dp[i + word.length()] = true;
                }
            }
        }
        return dp[s.length()];
    }


    //brute force  TLE
    /*
    Time complexity : O(2^n). Given a string of length nnn, there are n+1 ways to split it into two parts. At each step, we
    have a choice: to split or not to split. In the worse case, when all choices are to be checked, that results in O(2^n).

    Space complexity : O(n). The depth of the recursion tree can go upto n
     */
    public boolean wordBreakBF(String s, List<String> wordDict) {
        if(wordDict.isEmpty()) return false;
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, dict, 0);
    }

    private boolean dfs(String s, Set<String> dict, int idx) {
        if(idx == s.length()) return true;
        for(int i = idx; i < s.length(); i++) {
            String sub = s.substring(idx, i + 1);
            if(dict.contains(sub) && dfs(s, dict, i + 1)) return true;
        }
        return false;
    }

    //recursion with memoization
    //注意到上面的大量重复计算部分[idx ... s.length]部分有好多重复递归调用，所以可将计算过的cache住
    /*
    Time complexity : O(n^3). Size of recursion tree can go up to n^2
    Space complexity : O(n). The depth of recursion tree can go up to n.
    //6ms
     */
    public boolean wordBreakWithCache(String s, List<String> wordDict) {
        if(wordDict.isEmpty()) return false;
        Set<String> dict = new HashSet<>(wordDict);
        return recursionWithCache(s, dict, 0, new Boolean[s.length()]);
    }

    private boolean recursionWithCache(String s, Set<String> dict, int idx, Boolean[] cache) {
        if(idx == s.length()) return true;
        if(cache[idx] != null) return cache[idx];
        for(int i = idx; i < s.length(); i++) {
            String sub = s.substring(idx, i + 1);
            if(dict.contains(sub) && recursionWithCache(s, dict, i + 1, cache)) return cache[idx] = true;
        }
        return cache[idx] = false;
    }

    //approach 3: dp
    //dp[i] represents if first i chars(s[0, i - 1]) in s is breakable(formed by dict)
    //dp[i] = true if dict.contains(s.substring(j, i)]) and dp[j]
    //7ms
    public boolean wordBreakDP1(String s, List<String> wordsDict) {
        Set<String> set = new HashSet<>(wordsDict);
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;//we got nothing from dict to form "".
        for(int i = 1; i <= len; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }


    //approach 4: recursion with cache 2
    //2ms
    public boolean wordBreak(String s, List<String> wordsDict) {
        return recurse(s, wordsDict, new HashMap<String, Boolean>());
    }

    private boolean recurse(String s, List<String> wordsDict, Map<String, Boolean> cache) {
        if(cache.containsKey(s)) return cache.get(s);
        if(s.isEmpty()) return true;
        for(String word : wordsDict) {
            if(s.startsWith(word)) {
                if(recurse(s.substring(word.length()), wordsDict, cache)) {
                    return true;
                }
            }
        }
        cache.put(s, false);
        return false;
    }
}
