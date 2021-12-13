package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a sorted unique integer array nums.
 *
 * Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.
 *
 * Each range [a,b] in the list should be output as:
 *
 *     "a->b" if a != b
 *     "a" if a == b
 * Example 1:
 *
 * Input: nums = [0,1,2,4,5,7]
 * Output: ["0->2","4->5","7"]
 * Explanation: The ranges are:
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        for(int i = 0, count = 1; i < nums.length; i++) {//count: the count of range size
            if(i < nums.length - 1 && nums[i + 1] == nums[i] + 1) {
                count++;
            } else {
                if(count == 1) {
                    res.add(String.valueOf(nums[i]));
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(nums[i - count  + 1]).append("->").append(nums[i]);
                    res.add(sb.toString());
                    count = 1;//reset count. 只在这个else里面reset即可，因为上面if count==1
                }
            }
        }
        return res;
    }
}
