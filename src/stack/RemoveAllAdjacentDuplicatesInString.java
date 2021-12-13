package stack;

/**
 * You are given a string s consisting of lowercase English letters. A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It can be proven that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abbaca"
 * Output: "ca"
 * Explanation:
 * For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 */
public class RemoveAllAdjacentDuplicatesInString {
    //why this solution? You can easily update this solution to remove more duplicates.
    //Now it's a special case where we only remove replicates k = 2.
    public String removeDuplicates(String S){
        int n = S.length();
        char[] res = S.toCharArray();
        int cur = 0;//cur position to set in res
        for(int i = 0; i < n; i++, cur++){
            res[cur] = res[i];
            if(cur > 0 && res[cur] == res[cur - 1]) {
                cur -= 2;
            }
        }
        return new String(res, 0, cur);
    }


    // public String removeDuplicates(String S){
    //     StringBuilder sb = new StringBuilder();//use sb as a stack
    //     for(int i = 0; i < S.length(); i++){
    //         if(sb.length() > 0 && sb.charAt(sb.length() - 1) == S.charAt(i)){
    //             sb.setLength(sb.length() - 1);
    //         } else {
    //             sb.append(S.charAt(i));
    //         }
    //     }
    //     return sb.toString();
    // }


    // public String removeDuplicates(String S) {
    //     Deque<Character> stack = new LinkedList<>();
    //     for(char c : S.toCharArray()){
    //         if(stack.isEmpty() || stack.peek() != c){
    //             stack.push(c);
    //         } else {
    //             stack.pop();
    //         }
    //     }
    //     StringBuilder sb = new StringBuilder();
    //     while(!stack.isEmpty()){
    //         sb.append(stack.pollLast());//the bottom of stack
    //     }
    //     return sb.toString();
    // }
}
