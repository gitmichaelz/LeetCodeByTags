package string;

/**
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 */
public class CountBinarySubstrings {
    //maintain the current character run length and previous character run length, if preRunLength >= curRunLength, we have
    //found a valid string
    //time: O(N), space: O(1)
    public int countBinarySubstrings(String s) {
        int preRunLen = 0, curRunLen = 1, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == s.charAt(i - 1)) {
                curRunLen++;
            } else {
                preRunLen = curRunLen;
                curRunLen = 1;
            }
            if (preRunLen >= curRunLen) {
                res++;
            }
        }
        return res;
    }
}
