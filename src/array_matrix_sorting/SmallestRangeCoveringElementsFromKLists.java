package array_matrix_sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
 *
 * We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
 * Output: [20,24]
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 */
public class SmallestRangeCoveringElementsFromKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            for (int num : nums.get(i)) {
                list.add(new int[]{num, i});
            }
        }
        Collections.sort(list, (a, b) -> (a[0] - b[0]));
        int[] res = new int[]{list.get(0)[0], list.get(list.size() - 1)[0]};
        int start = 0;
        int end = 0;
        int[] count = new int[nums.size()];
        int total = 0;
        while (end < list.size()) {
            int[] pair = list.get(end);
            int index = pair[1];
            if (count[index] == 0) {
                total++;
            }
            count[index]++;
            end++;
            while (start < end && total == nums.size()) {  // covers
                if (res[1] - res[0] > list.get(end - 1)[0] - list.get(start)[0]) {
                    res[0] = list.get(start)[0];
                    res[1] = list.get(end - 1)[0];
                }
                int[] prev = list.get(start);
                int prevIndex = prev[1];
                count[prevIndex]--;
                if (count[prevIndex] == 0) {
                    total--;
                }
                start++;
            }
        }
        return res;
    }
}
