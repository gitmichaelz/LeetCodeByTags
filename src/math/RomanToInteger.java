package math;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    /**
     * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
     *
     * Symbol       Value
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     *
     * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
     *
     * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV.
     * Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
     *
     *     I can be placed before V (5) and X (10) to make 4 and 9.
     *     X can be placed before L (50) and C (100) to make 40 and 90.
     *     C can be placed before D (500) and M (1000) to make 400 and 900.
     *
     * Given a roman numeral, convert it to an integer.
     */
    //罗马数字中的字符代表的数值大小，一般是从右往左非严格递减的（也就是说可能相邻的两个字符相等, left >= right），然后不停地累加就行了（从后往前加，即从最右往左加）。
    //但如果发生相邻的两个元素，左边的字符比右边的字符代表的数值小，就需要减去左边这个字符代表的数值。如IV就是5-1=4，所以CIV就是5-1+100=104。
    //空间复杂度O(N)。时间复杂度O(N)。

    /**
     * By observing the way how it works, we can add them up, but there is one special case:
     * if left is less than right, we need subtract right by left.
     * use a map to store the roman symbols and numbers
     * So we can scan the input string from right to left and add them up if(), else, subtract right from left
     *
     * Time: O(N), space: O(1)
     */
    //since there are some special rules for roman number like 'IV' = 5 - 4 = 1
    //so we scan from right to left, if the val of s[i] < val of s[i + 1], res - val of s[i], else res += val of s[i]
    public int romanToInt(String s) {
        if(s == null || s.isEmpty()) return 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int len = s.length();
        int res = map.get(s.charAt(len - 1));
        for(int i = len - 2; i >= 0; i--) {
            if(map.get(s.charAt(i)) >= map.get(s.charAt(i + 1))) {
                res += map.get(s.charAt(i));
            } else {
                res -= map.get(s.charAt(i));
            }
        }
        return res;
    }
}
