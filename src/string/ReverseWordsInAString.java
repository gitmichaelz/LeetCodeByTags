package string;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should
 * only have a single space separating the words. Do not include any extra spaces.
 *
 * Example 1:
 *
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 *
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 */
public class ReverseWordsInAString {
    public String reverseWords(String s){
        if(s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder();
        int start = s.length() - 1, end = start;
        while(start >= 0){//从后往前
            while(end >= 0 && s.charAt(end) == ' '){
                end--;
            }
            start = end;
            while(start >= 0 && s.charAt(start) != ' '){
                start--;
            }
            String word = s.substring(start + 1, end + 1);//Note：若是start和end都到达了string的终点，不额外check也没关系，这种情况下word为空，我们最后会trim
            sb.append(word).append(" ");
            end = start;
        }
        return sb.toString().trim();
    }
}
