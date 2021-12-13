package string;

/**
 * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
 *
 * Return any possible rearrangement of s or return "" if not possible.
 *
 * Example 1:
 *
 * Input: s = "aab"
 * Output: "aba"
 */
public class ReorganizeString {
    /**
     No Sort O(N):

     1. count letter appearance and store in hash[i]
     2. find the letter with largest occurence.
     3. put the letter into even index numbe (0, 2, 4 ...) char array
     4. put the rest into the array
     */
    public String reorganizeString(String S) {
        if(S == null || S.isEmpty()) return "";
        int[] count = new int[26];
        for(char c : S.toCharArray()) {
            count[c - 'a']++;
        }
        int maxCount = 0, letter = 0;
        for(int i = 0; i < count.length; i++) {
            if(maxCount < count[i]) {
                maxCount = count[i];
                letter = i;
            }
        }
        if(maxCount > (S.length() + 1) / 2) return "";
        char[] res = new char[S.length()];
        int idx = 0;
        while(count[letter] > 0) {
            res[idx] = (char)(letter + 'a');
            count[letter]--;
            idx += 2;
        }
        for(int i = 0; i < count.length; i++) {
            while(count[i] > 0) {
                if(idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char)(i + 'a');
                count[i]--;
                idx += 2;
            }
        }
        return String.valueOf(res);
    }
}
