package string;

/**
 * We can scramble a string s to get a string t using the following algorithm:
 *
 *     If the length of the string is 1, stop.
 *     If the length of the string is > 1, do the following:
 *         Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
 *         Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
 *         Apply step 1 recursively on each of the two substrings x and y.
 *
 * Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 * Explanation: One possible scenario applied on s1 is:
 * "great" --> "gr/eat" // divide at random index.
 * "gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
 * "gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at ranom index each of them.
 * "g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
 * "r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
 * "r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
 * The algorithm stops now and the result string is "rgeat" which is s2.
 * As there is one possible scenario that led s1 to be scrambled to s2, we return true.
 */
public class ScrambleString {
    //if s1.equals(s2) return true;
    //if we can find 0 <= i <= len; so that s1[0, i] can scramble to s2[0, i] and s1[i, len] scramble into s2[i, len]
    //                                   or s1[0, i] can scramble to s2[len - i, len] and s1[i, len] scramble into s2[0, len - i]
    // then s1 can scramble into s2
    public boolean isScramble(String s1, String s2) {
        if(s1.length() != s2.length()) return false;
        if(s1.equals(s2)) return true;
        int[] arr = new int[26];
        for(char c : s1.toCharArray()) {
            arr[c - 'a']++;
        }
        for(char c : s2.toCharArray()) {
            arr[c - 'a']--;
        }
        for(int num : arr) {
            if(num != 0) return false;
        }
        int len = s1.length();
        for(int i = 1; i < len; i++) {
            if(isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if(isScramble(s1.substring(0, i), s2.substring(len - i)) && isScramble(s1.substring(i), s2.substring(0, len - i))) {
                return true;
            }
        }
        return false;
    }
}
