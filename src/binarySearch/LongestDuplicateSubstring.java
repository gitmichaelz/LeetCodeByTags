package binarySearch;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, consider all duplicated substrings: (contiguous) substrings of s that occur 2 or more times. The occurrences may overlap.
 *
 * Return any duplicated substring that has the longest possible length. If s does not have a duplicated substring, the answer is "".
 *
 *
 *
 * Example 1:
 *
 * Input: s = "banana"
 * Output: "ana"
 */
public class LongestDuplicateSubstring {
    //binary search + rolling hash
    //https://leetcode.com/problems/longest-duplicate-substring/solution/
    //h1 = h0 * a - c0* a^l + cl
    public String longestDupSubstring(String s) {
        int n = s.length();
        int[] nums = new int[n];
        for(int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        int start = 0, end = n - 1;
        int longest = 0;
        int a = 26;
        long modulus = (long)Math.pow(2, 37) - 1;//这个modulus选取太JB tricky了！！！！卧槽尼玛。
        while(start <= end) {
            int len = start + (end - start)/2;
            if(search(nums, len, n, a, modulus) >= 0) {
                longest = len;
                start = len + 1;
            } else {
                end = len - 1;
            }
        }
        int p = search(nums, longest, n, a, modulus);
        return s.substring(p, p + longest);
    }

    private int search(int[] nums, int len, int n, int a, long modulus) {
        long h = 0, al = 1;
        for(int i = 0; i < len; i++) {
            h = (h * a + nums[i]) % modulus;
            al = (al * a) % modulus;
        }
        Set<Long> set = new HashSet<>();
        set.add(h);//不要忘记这一步！！！
        for(int i = 1; i <= n - len; i++) {
            h = (h * a - nums[i - 1] * al % modulus + modulus) % modulus;//h * a - nums[i - 1] * al % modulus could benegative
            h = (h + nums[i + len - 1]) % modulus;
            if(!set.add(h)) {
                return i;
            }
        }
        return -1;
    }



    //以下方法如果字符串很大(3 * 10^4)时，会超过空间限制, Memory Limit Exceeded, 因为我们在不停的往set里添加子串。最坏情况可能超过题目要求的空间限制。
//     public String longestDupSubstring(String s) {
//         int lo = 1, hi = s.length() - 1, len = s.length();
//         String res = "";
//         while(lo <= hi) {
//             int mid = (lo + hi)/2;
//             String found = search(s, len, mid);
//             if(!found.isEmpty()) {
//                 res = found;
//                 lo = mid + 1;
//             } else {
//                 hi = mid - 1;
//             }
//         }
//         return res;
//     }

//     private String search(String s, int len, int k) {
//         Set<String> set = new HashSet<>();
//         for(int i = 0; i <= len - k; i++) {
//             String sub = s.substring(i, i + k);
//             if(!set.add(sub)) {
//                 return sub;
//             }
//         }
//         return "";
//     }
}
