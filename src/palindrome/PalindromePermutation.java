package palindrome;

/**
 * Given a string s, return true if a permutation of the string could form a palindrome.
 *
 * Example 1:
 *
 * Input: s = "code"
 * Output: false
 *
 * Example 2:
 *
 * Input: s = "aab"
 * Output: true
 *
 * Example 3:
 *
 * Input: s = "carerac"
 * Output: true
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s){
        int[] arr = new int[26];
        for(char c : s.toCharArray()){
            arr[c - 'a']++;
        }
        int oddCount = 0;
        for(int n : arr){
            if(n % 2 != 0){
                oddCount++;
                if(oddCount > 1) return false;
            }
        }
        return true;
    }
}
