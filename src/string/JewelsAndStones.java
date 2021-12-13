package string;

import java.util.HashSet;
import java.util.Set;

/**
 * You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
 *
 * Letters are case sensitive, so "a" is considered a different type of stone from "A".
 *
 *
 *
 * Example 1:
 *
 * Input: jewels = "aA", stones = "aAAbbbb"
 * Output: 3
 */
public class JewelsAndStones {
    // we used hash set and it's O(1) time to check if it contains an element.
    //So the total time complexity will be O(J+S), instead of O(JS)  Space is O(J)
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<>();
        for (char ch: J.toCharArray()){
            set.add(ch);
        }
        int count = 0;
        for (char ch: S.toCharArray()){
            if (set.contains(ch)){
                count++;
            }
        }
        return count;
    }
}
