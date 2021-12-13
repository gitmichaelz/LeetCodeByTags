package dfs_backtracking_Recursion;

/**
 * A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 *     "s10n" ("s ubstitutio n")
 *     "sub4u4" ("sub stit u tion")
 *     "12" ("substitution")
 *     "su3i1u2on" ("su bst i t u ti on")
 *     "substitution" (no substrings replaced)
 *
 * Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.
 *
 * The length of an abbreviation is the number of letters that were not replaced plus the number of substrings that were replaced. For example, the abbreviation "s10n" has a length of 3 (2 letters + 1 substring) and "su3i1u2on" has a length of 9 (6 letters + 3 substrings).
 *
 * Given a target string target and an array of strings dictionary, return an abbreviation of target with the shortest possible length such that it is not an abbreviation of any string in dictionary. If there are multiple shortest abbreviations, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: target = "apple", dictionary = ["blade"]
 * Output: "a4"
 */
public class MinimumUniqueWordAbbreviation {
    public String minAbbreviation(String target, String[] dictionary) {
        char[] c = target.toCharArray();
        char[] tmp = new char[c.length];
        // traverse length from min to max
        for (int l = 1; l <= target.length(); l++) {
            String abbr = minAbbreviation(c, 0, tmp, 0, dictionary, l);
            if (abbr != null) return abbr;
        }
        return null;
    }

    private String minAbbreviation(char[] c, int p, char[] tmp, int t, String[] dictionary, int l) {
        if (l == 0) {// all length has been used up
            if (p == c.length && !conflict(tmp, t, dictionary, c.length)) return new String(tmp, 0, t);
            else return null;
        }
        if (t == 0 || tmp[t - 1] > '9') {// can use abbr
            // c.length - 1 - (end + 1) + 1 >= l - 1 => c.length - end >= l
            for (int end = p + 1; end <= c.length - l; end++) {// we don't need to check length of abbr = 1, it will have the same length with the one that does not use abbr and has less elements to distinguish a word
                int s = end - p + 1;
                if (s >= 10) {
                    tmp[t] = (char) (s / 10 + '0');
                    tmp[t + 1] = (char) (s % 10 + '0');
                    String r = minAbbreviation(c, end + 1, tmp, t + 2, dictionary, l - 1);
                    if (r != null) return r;
                } else {
                    tmp[t] = (char) (s + '0');
                    String r = minAbbreviation(c, end + 1, tmp, t + 1, dictionary, l - 1);
                    if (r != null) return r;
                }
            }
        }
        // use original character
        tmp[t] = c[p];
        return minAbbreviation(c, p + 1, tmp, t + 1, dictionary, l - 1);
    }

    private boolean conflict(char[] abbr, int t, String[] dictionary, int l) {
        char[] pattern = new char[abbr.length];
        int p = 0; // pointer for pattern
        int count = 0;
        for (int i = 0; i < t; i++) {
            char c = abbr[i];
            if (c <= '9') count = count * 10 + c - '0';
            else {
                if (count != 0) {
                    pattern[p++] = (char) count; // store count to pattern. (note that count must be less than 22)
                    count = 0;
                }
                pattern[p++] = c;
            }
        }
        //if (count != 0) pattern[p++] = (char)count; tailing pattern doesn't need to check
        for (String s : dictionary) {
            if (s.length() != l) continue;
            int j = 0;
            boolean match = true;
            for (int i = 0; i < p; i++) {
                if (pattern[i] < 22) j += pattern[i]; // pass count characters
                else if (s.charAt(j) != pattern[i]) {
                    match = false;
                    break;
                } else j++; // match one character
            }
            if (match) return true;
        }
        return false;
    }
}
