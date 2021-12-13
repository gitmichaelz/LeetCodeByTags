package string;

/**
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 */
public class FirstUniqueCharacterInAString {
    //use 2 pointer
    public int firstUniqChar(String s) {
        if(s == null || s.isEmpty()) return -1;
        int fast = 0, slow = 0;
        char[] chars = s.toCharArray();
        int[] count = new int[26];
        int n = chars.length;
        while(fast < n){
            count[chars[fast] - 'a']++;
            while(slow < n && count[chars[slow] -'a'] > 1){
                slow++;//move slow to next unique chars or unvisited chars
            }
            if(slow == n) return -1;
            fast++;
        }
        return slow;
    }
    // public int firstUniqChar(String s) {
    //    int[] count = new int[26];
    //    char[] chars = s.toCharArray();
    //    for(char c : chars){
    //        count[c - 'a']++;
    //    }
    //    for(int i = 0; i < chars.length; i++){
    //        if(count[chars[i] - 'a'] == 1){
    //            return i;
    //        }
    //    }
    //    return -1;
    // }
}
