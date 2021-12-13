package string;

/**
 * Write a function that reverses a string. The input string is given as an array of characters s.
 *
 * You must do this by modifying the input array in-place with O(1) extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 */
public class ReverseString {
    public void reverseString(char[] s) {
        for(int left = 0, right = s.length - 1; left < right; left++, right--){
            if(s[left] != s[right]){
                char tmp = s[left];
                s[left] = s[right];
                s[right] = tmp;
            }
        }
    }
}
