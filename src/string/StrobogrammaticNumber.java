package string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 *
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 *
 *
 *
 * Example 1:
 *
 * Input: num = "69"
 * Output: true
 *
 * Example 2:
 *
 * Input: num = "88"
 * Output: true
 *
 * Example 3:
 *
 * Input: num = "962"
 * Output: false
 */
public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num){
        Map<Character, Character> map = new HashMap<>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
        for(int left = 0, right = num.length() - 1; left <= right; left++, right--){
            if(!map.containsKey(num.charAt(left))) return false;//坑，第一遍写的时候写成了containsKey(left)
            if(map.get(num.charAt(left)) != num.charAt(right)) return false;
        }
        return true;
    }
}