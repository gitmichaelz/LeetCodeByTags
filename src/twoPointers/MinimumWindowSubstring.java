package twoPointers;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 * A substring is a contiguous sequence of characters within the string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 */
public class MinimumWindowSubstring {
    //几个划窗问题一起看，3，76，159， 239， 340， 424， 480， 567， 995， 1004， 1040
    //用一个辅助数组也可以,没有两个数组好理解，重点掌握双数组的那个解法
    //用一个数组可以这样理解，数组map来存放t中字符的count  map[c],
    //然后在扫描s时，判断是否有 map[s_char] > 0, 如果有，则found++; 同时注意一点，所有扫描到的字符，我们都要进行这样一个操作，即map[s_char]--，这样当found == t.length()时，到目前为止map里所有值都是小于等于0的。
    //然后当我们有found == t.length()时，我们收缩左指针，判断是否有map[l_char] == 0, 如果有，则说明找到了一个在t中出现的字符，则found--;同时注意，所有扫描的左字符，都要进行map[l_char]++,操作，restore map.
    public String minWindow(String s, String t){
        int[] map = new int[256];
        for(char ch : t.toCharArray()){
            map[ch]++;
        }
        int minLength = Integer.MAX_VALUE, start = 0, found = 0;
        for(int left = 0, right = 0; right < s.length(); right++){
            char r_ch = s.charAt(right);
            if(map[r_ch]-- > 0){
                found++;
            }

            while(found == t.length()){
                if(minLength > right - left + 1){
                    minLength = right - left + 1;
                    start = left;
                }
                char l_ch = s.charAt(left);
                if(map[l_ch]++ == 0){//map[l_ch] == 0 means l_ch is a char in t, otherwise map[l_ch] < 0, since we are decreasing count of chars till position 'right'
                    found--;
                }
                left++;
            }
        }
        return  minLength == Integer.MAX_VALUE? "" : s.substring(start, start + minLength);
    }





    /*
    //sliding window
    //spread right index to find a window contain all characters in t
    //shrink the left pointer to make the window invalid, and continue spreading the right index to find a window contains all characters in t
    //maintain a min len (right - left), and corresponding minWindow would be s.substring(left, right);
    public String minWindow(String s, String t) {
        if(s == null || s.length() < t.length()) return "";
        int[] smap = new int[256];
        int[] tmap = new int[256];
        for(char c : t.toCharArray()){
            tmap[c]++;
        }
        int found = 0;//the number of matched chars found in s
        int minLen = Integer.MAX_VALUE;//the min window length so far
        int start = 0;//the start index of min window
        for(int left = 0, right = 0; right < s.length(); right++){
            char ch = s.charAt(right);
            if(tmap[ch] > 0) {
                smap[ch]++;
                if(smap[ch] <= tmap[ch]) {//only if smap[ch] <= tmap[ch], we increase found, if >, it's not counted as found
                    found++;
                }
            }

            while(found == t.length()) {//if we found a window, we maintain the minLen of the window, and then shrink left pointer
                if(right - left + 1 < minLen){
                    minLen = right - left + 1;
                    start = left;
                }
                char schar = s.charAt(left);
                if(tmap[schar] > 0) {
                    smap[schar]--;
                    if(smap[schar] < tmap[schar]){//means smap 里含的字符比tmap里少，相当于found变少了一个
                        found--;
                    }
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE? "" : s.substring(start, start + minLen);
    }

    */
}
