package Google;

import java.util.Map;
import java.util.TreeMap;

/**
 * Given an integer array of even length arr, return true if it is possible to reorder arr such that arr[2 * i + 1] = 2 * arr[2 * i] for every 0 <= i < len(arr) / 2, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,3,6]
 * Output: false
 *
 * Example 2:
 *
 * Input: arr = [2,1,2,6]
 * Output: false
 *
 * Example 3:
 *
 * Input: arr = [4,-2,2,-4]
 * Output: true
 * Explanation: We can take two groups, [-2,-4] and [2,4] to form [-2,-4,2,4] or [2,4,-2,-4].
 */
public class ArrayOfDoubledPairs {
    /**
     * Match from smallest to biggest
     * sort the numbers by their absolute value in treemap so [2, -1, 1 -2] can be sorted as[-1, 1, -2, 2]
     *
     * Let's see a simple case
     * Assume all interger are positive, for example [2,4,4,8].
     * We have one x = 2, we need to match it with one 2x = 4.
     * Then one 4 is gone, we have the other x = 4.
     * We need to match it with one 2x = 8.
     * Finaly no number left.
     *
     * Why we start from 2?
     * Because it's the smallest and we no there is no x/2 left.
     * So we know we need to find 2x
     *
     * What if the case negative?
     * One way is that start from the biggest (with abosolute value smallest),
     * and we aplly same logic.
     *
     * Explanation
     *
     *     Count all numbers.
     *     Loop all numbers on the order of its absolute.
     *     We have counter[x] of x, so we need the same amount of 2x wo match them.
     *     If c[x] > c[2 * x], then we return false
     *     If c[x] <= c[2 * x], then we do c[2 * x] -= c[x] to remove matched 2x.
     *
     * Don't worry about 0, it doesn't fit the logic above but it won't break our algorithme.
     *
     * In case count[0] is odd, it won't get matched in the end.
     * (Anyway you can return false earlier here)
     *
     * In case count[0] is even, we still have c[0] <= c[2 * 0].
     * And we still need to check all other numbers.
     *
     * O(NlogK), N is the len of Array, K is the number of different elements
     */
    public boolean canReorderDoubled(int[] A) {
        if (A.length == 0) return true;
        Map<Integer, Integer> map = new TreeMap<>((a, b) -> Math.abs(a) == Math.abs(b)? a - b : Math.abs(a) - Math.abs(b));
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        for (int key : map.keySet()) {
            if (map.get(key) == 0) continue;
            int doubled = key * 2;
            if (map.containsKey(doubled) && map.get(doubled) >= map.get(key)) {
                map.put(doubled, map.get(doubled) - map.get(key));
            } else {
                return false;
            }
        }
        return true;
    }
}
