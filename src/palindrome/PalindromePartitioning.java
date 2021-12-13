package palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 *
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= s.length <= 16
 *     s contains only lowercase English letters.
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        List<List<String>> r = new ArrayList<>();
        dfs(r, s, 0, new ArrayList<>(), dp);
        return r;
    }

    void dfs(List<List<String>> r, String s, int start, List<String> path, boolean[][] dp) {
        int len = s.length();
        if (start >= len) r.add(new ArrayList<>(path));

        for (int i = start; i < len; i++) {
            //    i
            // abca ...  //a == a, ok
            if (s.charAt(i) != s.charAt(start)) continue;
            //    i
            // abca ...   //b != c continue
            if (i - 1 > start + 1 && !dp[start + 1][i - 1]) continue;
            dp[start][i] = true;
            path.add(s.substring(start, i + 1));
            dfs(r, s, i + 1, path, dp);
            path.remove(path.size() - 1);
        }
    }


//    public List<List<String>> partition1(String s) {
//        List<List<String>> res = new ArrayList<>();
//        backtrack(s, 0, res, new ArrayList<>());
//        return res;
//    }
//    //find the palindrome from idx of s,
//    private void backtrack(String s, int idx, List<List<String>> res, List<String> list) {
//        if(idx == s.length()) {
//            res.add(new ArrayList<>(list));
//            return;
//        }
//        for(int i = idx; i < s.length(); i++) {
//            if(isPal(s, idx, i)) {
//                list.add(s.substring(idx, i + 1));
//                backtrack(s, i + 1, res, list);
//                list.remove(list.size() - 1);
//            }
//        }
//    }
//
//    private boolean isPal(String s, int low, int high) {
//        while(low < high) {
//            if(s.charAt(low++) != s.charAt(high--)) return false;
//        }
//        return true;
//    }
//



}
