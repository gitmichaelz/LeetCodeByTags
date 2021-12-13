package string;

/**
 * A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).
 *
 * You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
 *
 * Return the minimum number of flips to make s monotone increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110"
 * Output: 1
 * Explanation: We flip the last digit to get 00111.
 */
public class FlipStringToMonotoneIncreasing {
    //idea: let's keep track of the number of ones and flips(either 0 -> 1 or 1 -> 0)
    //suppose we already have mono string from s[0, i - 1], for the new coming char ch,
    // if ch == '1', no flips needed as it is appended to the tails of the substring s[0, i - 1], just update the number of '1'
    // if ch == '0', we either flip the new char 0 -> 1(by increasing the cur flips by 1), or flip the pre "ones" '1' in the substring to '0',
    //and the result would be the min(flips, ones)
    public int minFlipsMonoIncr(String s) {
        if (s == null || s.length() < 2) return 0;
        int flips = 0;//initially this flip means: 0 -> 1
        int ones = 0;// number of 1's
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '1') {
                ones++;
            } else {
                flips++;
                flips =  Math.min(flips, ones);
            }

        }
        return flips;
    }
}
