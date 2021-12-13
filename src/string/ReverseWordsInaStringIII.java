package string;

/**
 * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 */
public class ReverseWordsInaStringIII {
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        for(int i = 0, j; i < arr.length; i = j + 1) {
            j = i;
            while(j < arr.length && arr[j] != ' ') {
                j++;
            }
            reverse(arr, i, j - 1);
        }
        return String.valueOf(arr);
    }

    private void reverse(char[] arr, int left, int right) {
        while(left < right) {
            char tmp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = tmp;
        }
    }
}
