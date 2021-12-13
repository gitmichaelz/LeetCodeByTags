package string;

/**
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them, causing the left and the right side of the deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcd", k = 2
 * Output: "abcd"
 * Explanation: There's nothing to delete.
 */
public class RemoveAllAdjacentDuplicatesInStringII {
    //similar to https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    public String removeDuplicates(String s, int k){
        int n = s.length();
        int[] count = new int[s.length()];//count[i] means the number of repeating chars ending at i
        int cur = 0;//cur pos to set in res
        char[] res = s.toCharArray();
        for(int i = 0; i < n; i++, cur++){
            res[cur] = res[i];
            count[cur] = cur > 0 && res[cur] == res[cur - 1]? count[cur - 1] + 1 : 1;
            if(count[cur] == k) {
                cur -= k;
            }
        }
        return new String(res, 0, cur);
    }

    //use sb to simulate a stack
    // public String removeDuplicates(String s, int k){
    //     int n = s.length();
    //     StringBuilder sb = new StringBuilder();
    //     int[] count = new int[n];
    //     for(char c : s.toCharArray()){
    //         sb.append(c);
    //         int index = sb.length() - 1;
    //         count[index] = index > 0 && sb.charAt(index - 1) == sb.charAt(index)? count[index - 1] + 1 : 1;
    //         if(count[index] == k) {
    //             sb.setLength(sb.length() - k);
    //         }
    //     }
    //     return sb.toString();
    // }
}
