package palindrome;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 *
 * You may return the answer in any order. If s has no palindromic permutation, return an empty list.
 *
 * Example 1:
 *
 * Input: s = "aabb"
 * Output: ["abba","baab"]
 */
public class PalindromePermutationII {
    //Basically, the idea is to perform permutation on half of the palindromic string and then form the full palindromic result.
    //idea, count each char, find if there are any chars with odd count, if oddcount > 1, return empty list,
    //also makr the index of the char with odd count
    //then use a char array to store the tmp genarated string's chars. make chars[n/2] = (char)oddIndex;
    //then do dfs to get all possible palindrome
    public List<String> generatePalindromes(String s) {
        int n = s.length();
        char[] chs = new char[n];
        int[] count = new int[26];
        for(char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        int oddCount = 0;
        int oddIndex = -1;
        for(int i = 0; i < count.length; i++) {
            if(count[i] % 2 == 1) {
                oddCount++;
                oddIndex = i;
            }
        }
        if(oddCount > 1) return new ArrayList<>();//非法，返回空list
        if(oddCount == 1) {//不要忘记判断oddCount == 1.
            chs[n/2] = (char)('a' + oddIndex);
            count[oddIndex]--;//不要忘记count[oddIndex]--.重要！！！！
        }
        List<String> res = new ArrayList<>();

        backtrack(count, 0, n - 1, chs, res);
        return res;
    }

    private void backtrack(int[] count, int left, int right, char[] chs, List<String> res) {
        if(left >= right) {
            res.add(new String(chs));
            return;
        }
        for(int i = 0; i < 26; i++) {
            if(count[i] == 0) continue;
            chs[left] = chs[right] = (char)('a' + i);
            count[i] -= 2;
            backtrack(count, left + 1, right - 1, chs, res);
            count[i] += 2;
        }
    }
}
