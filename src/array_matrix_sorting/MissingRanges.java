package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 *
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 *
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 *
 * Each range [a,b] in the list should be output as:
 *
 *     "a->b" if a != b
 *     "a" if a == b
 *
 * Example 1:
 *
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();

        if(nums.length == 0){
            result.add(getRange(lower, upper));
            return result;
        }
        if(nums[0] > lower){
            result.add(getRange(lower, nums[0] - 1));
        }
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1] || nums[i] + 1 == nums[i + 1])//如果相邻相等或者差一(升序)，则没问题
                continue;
            result.add(getRange(nums[i] + 1, nums[i + 1] - 1));
        }
        if(nums[nums.length - 1] < upper)//不要最后忘记check 是否< upper，(根据题意，不会超过upper范围)
            result.add(getRange(nums[nums.length - 1] + 1, upper));
        return result;
    }

    private String getRange(int start, int end){
        if(end == start){
            return String.valueOf(start);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(start);
        builder.append("->");
        builder.append(end);
        return builder.toString();
    }
}
