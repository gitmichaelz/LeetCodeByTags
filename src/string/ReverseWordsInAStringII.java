package string;

/**
 * Given a character array s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 *
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 *
 * Example 1:
 *
 * Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 */
public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        for(int end = 0; end < s.length; end++){
            int start = end;//坑一，每次要把start初始化为end
            while(end < s.length && s[end] != ' '){
                end++;
            }
            reverse(s, start, end - 1);//坑二，不要忘记end - 1
        }
    }
    private void reverse(char[] s, int start, int end){
        while(start < end){
            char tmp = s[start];
            s[start++] = s[end];
            s[end--] = tmp;
        }
    }
}
