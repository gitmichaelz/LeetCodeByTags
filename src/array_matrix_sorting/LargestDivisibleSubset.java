package array_matrix_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 *
 *     answer[i] % answer[j] == 0, or
 *     answer[j] % answer[i] == 0
 *
 * If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,2]
 * Explanation: [1,3] is also accepted.
 */
public class LargestDivisibleSubset {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] count = new int[nums.length];
        int[] pre = new int[nums.length];//to store the pre index of index i. pre[i] = j, if j is i's pre index
        int max = 0, index = -1;
        for(int i = 0; i < nums.length; i++) {
            count[i] = 1;
            pre[i] = -1;
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] % nums[j] == 0 && count[i] < 1 + count[j]) {
                    count[i] = 1 + count[j];
                    pre[i] = j;
                }
            }
            if(count[i] > max) {
                max = count[i];
                index = i;//track where is the index of largest subset
            }
        }
        List<Integer> res = new ArrayList<>();
        while(index != -1) {
            res.add(nums[index]);
            index = pre[index];
        }
        return res;
    }
}
