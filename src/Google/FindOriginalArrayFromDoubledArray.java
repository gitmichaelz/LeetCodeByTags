package Google;

import java.util.Map;
import java.util.TreeMap;

/**
 * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling the resulting array.
 *
 * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: changed = [1,3,4,2,6,8]
 * Output: [1,3,4]
 * Explanation: One possible original array could be [1,3,4]:
 * - Twice the value of 1 is 1 * 2 = 2.
 * - Twice the value of 3 is 3 * 2 = 6.
 * - Twice the value of 4 is 4 * 2 = 8.
 * Other original arrays could be [4,3,1] or [3,1,4].
 *
 * Example 2:
 *
 * Input: changed = [6,3,0,1]
 * Output: []
 * Explanation: changed is not a doubled array.
 */
public class FindOriginalArrayFromDoubledArray {
    /**
     *
     Count all numbers.
     Loop all numbers on the order of its absolute.
     We have counter[x] of x, so we need the same amount of 2x wo match them.
     If c[x] > c[2 * x], then we return []
     If c[x] <= c[2 * x], then we repeatly do c[2 * x]-- and append x to result res

     Don't worry about 0, it doesn't fit the logic above but it won't break our algorithme.

     In case count[0] is odd, it won't get matched in the end.

     In case count[0] is even, we still have c[0] <= c[2 * 0].
     And we still need to check all other numbers.
     */
    //time: O(NlogK), where N is the length of array, K is number of different elements
    public int[] findOriginalArray(int[] A) {
        int n = A.length, i = 0;
        if (n % 2 == 1) return new int[0];
        int[] res = new int[n / 2];
        Map<Integer, Integer> count;
        count = new TreeMap<>();//tree map can make sure the key is sorted in ascending order by default
        for (int a : A)
            count.put(a, count.getOrDefault(a, 0) + 1);
        for (int x : count.keySet()) {
            if (count.get(x) > count.getOrDefault(x + x, 0))
                return new int[0];
            for (int j = 0; j < count.get(x); ++j) {
                res[i++] = x;
                count.put(x + x, count.get(x + x) - 1);
            }
        }
        return res;
    }
}
