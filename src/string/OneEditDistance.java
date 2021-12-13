package string;

/**
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 *     Insert exactly one character into s to get t.
 *     Delete exactly one character from s to get t.
 *     Replace exactly one character of s with a different character to get t.
 *
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 *
 * Example 2:
 *
 * Input: s = "", t = ""
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 */
public class OneEditDistance {
    //两种思路都看看
//     //这题注意一点就是，如果m > n, 不匹配的话，只让i++,否则，如果m == n，则让i,j 同事移动
//     public boolean isOneEditDistance(String s, String t) {
//         int m = s.length(), n = t.length();
//         if(m < n) return isOneEditDistance(t, s);//we always assume s is the longer one
//         if(s.equals(t) || m - n > 1) return false;
//         for(int i = 0, j = 0, count = 0; i < m && j < n; ){//count is the num of edit distance
//             if(s.charAt(i) != t.charAt(j)){
//                 count++;
//                 if(count > 1) return false;//more than one edit distance
//                 if(m != n){
//                     i++;//注意这里的坑，i++完了之后一定要直接continue
//                     continue;
//                 }
//             }
//             i++;//注意这里的坑，一定要把i++, j++写到这里
//             j++;
//         }
//         return true;
//     }
// }

    public boolean isOneEditDistance(String s, String t){
        int m = s.length(), n = t.length();
        if(m > n) return isOneEditDistance(t, s);//m is always shorter
        for(int i = 0; i < m; i++){
            if(s.charAt(i) != t.charAt(i)){
                if(m == n){
                    return s.substring(i + 1).equals(t.substring(i + 1));
                }
                return s.substring(i).equals(t.substring(i + 1));
            }
        }
        return m + 1 == n;
    }
}
