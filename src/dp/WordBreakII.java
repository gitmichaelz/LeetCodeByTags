package dp;

import java.util.*;

/**
 * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
 * Output: ["cats and dog","cat sand dog"]
 */
public class WordBreakII {
    int maxLen = 0;
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        // for(String w: wordDict) {
        //     set.add(w);
        //     if(w.length() > maxLen) maxLen = w.length(); //get the maxLen of words
        // }
        Map<Integer, List<String>> map = new HashMap<>();
        return dfs(set, s, 0, map);
    }

    public List<String> dfs(Set<String> set, String s, int start, Map<Integer, List<String>> map) {
        if(map.containsKey(start)) return map.get(start);
        List<String> list = new ArrayList<>();
        if(start == s.length())  list.add("");
        //reduce the # of iterations using maxLen
        for(int i = start; i < s.length(); i++) {
            if(set.contains(s.substring(start, i + 1))) {
                List<String> nexts = dfs(set, s, i + 1, map);
                for(String next : nexts) {
                    if(next.isEmpty())//reaches the end
                        list.add(s.substring(start, i + 1));
                    else
                        list.add(s.substring(start, i + 1) + " " + next);
                }
            }
        }
        map.put(start, list);
        return list;
    }

    //这个更快？
//    public List<String> wordBreak(String s, List<String> wordDict) {
//        List<String> result = new ArrayList<>();
//        if (s == null || s.length() == 0) {
//            return result;
//        }
//
//        Set<String> set = new HashSet<>(wordDict);
//
//        dfs(result, set, 0, new StringBuilder(), s);
//        return result;
//    }
//    private void dfs(List<String> result, Set<String> wordDict, int index,
//                     StringBuilder sb, String s) {
//        if (index == s.length()) {
//            result.add(sb.toString());
//            return;
//        }
//        int len = sb.length();
//        for (int i = index + 1; i <= s.length(); ++i) {
//            if (wordDict.contains(s.substring(index, i))) {
//                sb.append(s.substring(index, i));
//                if (i != s.length()) {
//                    sb.append(" ");
//                }
//                dfs(result, wordDict, i, sb, s);
//                sb.setLength(len);
//            }
//        }
//    }
}
