package string;

/**
 * Given a string s, reverse only all the vowels in the string and return it.
 *
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "hello"
 * Output: "holle"
 */
public class ReverseVowelsOfAString {
    public String reverseVowels(String s){
        if(s == null || s.isEmpty()) return s;
        char[] chars = s.toCharArray();
        String vowels = "aeiouAEIOU";
        int start = 0, end = s.length() - 1;
        while(start < end){
            while(start < end && vowels.indexOf(chars[start]) < 0) {
                start++;
            }
            while(start < end && vowels.indexOf(chars[end]) < 0) {
                end--;
            }
            swap(chars, start, end);
            start++;
            end--;
        }
        return new String(chars);
    }
    private void swap(char[] chars, int start, int end){
        char tmp = chars[start];
        chars[start] = chars[end];
        chars[end] = tmp;
    }
}
