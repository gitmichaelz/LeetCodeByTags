package string;

/**
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 *
 *
 *
 * Example 1:
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * Example 3:
 *
 * Input: haystack = "", needle = ""
 * Output: 0
 */
public class ImplementStrStr {
    //O(M * N) brute force is fine
    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()) return 0;
        for(int i = 0; i <= haystack.length() - needle.length(); i++){
            for(int j = 0; j < needle.length() && haystack.charAt(i + j) == needle.charAt(j); j++) {
                if(j == needle.length() - 1) return i;
            }
        }
        return -1;
    }


    //https://leetcode.com/problems/implement-strstr/discuss/535326/Java-KMP-Solution-O(m%2Bn)-Clean-code
    //https://www.youtube.com/watch?v=uKr9qIZMtzw
    //O(M + N), build next table (O(N)), Search in haystack, O(M)
//    public int strStr(String haystack, String needle){
//        int m = haystack.length(), n = needle.length();
//        if(n == 0) return 0;
//        int[] next = computePrefix(needle);
//        for(int i = 0, j = 0; i < m; i++){//i, j is index in haystack and needle, we are comparing haystack[i], needle[j]
//            while(j > 0 && needle.charAt(j) != haystack.charAt(i)){//a mismatch occures, to find the right j in needle
//                j = next[j - 1];
//            }
//            if(needle.charAt(j) == haystack.charAt(i)){
//                j++;
//                if(j == n){
//                    return i - n + 1;
//                }
//            }
//        }
//        return -1;
//    }
//    //build next table, also called failure function,
//    private int[] computePrefix(String needle){
//        int n = needle.length();
//        int[] next = new int[n];//next[i] means the length of longest same prefix and suffix ending at ith element
//        for(int i = 1, j = 0; i < n; i++){//j indicates how many letters we matched so far, from is the length
//            while(j > 0 && needle.charAt(j) != needle.charAt(i)){//fail then jump until find a match or j == 0
//                j = next[j - 1];//skip to previous possible position, ?????????????????????j?????????pattern, ????????????????????????????????????next[j - 1] = lenght of longestPrefixSuffix[0, j- 1], and we can move j to next[j - 1], ????????????????????????????????????????????????????????????j = next[j - 1] ??????j == 0?????????????????????   next[next[j - 1] - 1] ?????????0???j??????????????????????????????//????????????????????????????????????????????????????????????????????????????????????ABCABCABCABCABCABCDABC??????????????????
//            }
//            if(needle.charAt(j) == needle.charAt(i)){
//                next[i] = ++j;
//            }
//        }
//        return next;
//    }




}
