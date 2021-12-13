package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * A word's generalized abbreviation can be constructed by taking any number of non-overlapping and non-adjacent substrings and replacing them with their respective lengths.
 *
 *     For example, "abcde" can be abbreviated into:
 *         "a3e" ("bcd" turned into "3")
 *         "1bcd1" ("a" and "e" both turned into "1")
 *         "5" ("abcde" turned into "5")
 *         "abcde" (no substrings replaced)
 *     However, these abbreviations are invalid:
 *         "23" ("ab" turned into "2" and "cde" turned into "3") is invalid as the substrings chosen are adjacent.
 *         "22de" ("ab" turned into "2" and "bc" turned into "2") is invalid as the substring chosen overlap.
 *
 * Given a string word, return a list of all the possible generalized abbreviations of word. Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "word"
 * Output: ["4","3d","2r1","2rd","1o2","1o1d","1or1","1ord","w3","w2d","w1r1","w1rd","wo2","wo1d","wor1","word"]
 */
public class GeneralizedAbbreviation {
    /*
        For each char c[i], either abbreviate it or not.

        Abbreviate: count accumulate num of abbreviating chars, but don't append it yet.
        Not Abbreviate: append accumulated num as well as current char c[i].
        In the end append remaining num.
        Using StringBuilder can decrease 36.4% time.

    This comes to the pattern I find powerful:

    int len = sb.length(); // decision point
    ... backtracking logic ...
    sb.setLength(len);     // reset to decision point

        */
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        DFS(res, new StringBuilder(), word.toCharArray(), 0, 0);
        return res;
    }

    public void DFS(List<String> res, StringBuilder sb, char[] c, int i, int num) {
        int len = sb.length();
        if(i == c.length) {
            if(num != 0) sb.append(num);
            res.add(sb.toString());
        } else {
            DFS(res, sb, c, i + 1, num + 1);               // abbr c[i]

            if(num != 0) sb.append(num);                   // not abbr c[i]
            DFS(res, sb.append(c[i]), c, i + 1, 0);
        }
        sb.setLength(len);
    }
}
