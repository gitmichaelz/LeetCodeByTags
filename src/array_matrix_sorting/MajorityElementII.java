package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: [3]
 *
 * Example 2:
 *
 * Input: nums = [1]
 * Output: [1]
 *
 * Example 3:
 *
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 * Constraints:
 *
 *     1 <= nums.length <= 5 * 104
 *     -109 <= nums[i] <= 109
 */
public class MajorityElementII {
    //there should be at most 2 different elements in nums occurs more than n/3 times
    //so we can find at most 2 elements based on Boyer-Moore Majority Vote algorithm
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;
        for(int n : nums){
            if(n ==  candidate1){//记住这些判断顺序
                count1++;
            } else if(n == candidate2){
                count2++;
            } else if(count1 == 0){
                candidate1 = n;
                count1 = 1;
            } else if(count2 == 0){
                candidate2 = n;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }

        }
        //以上步骤选出了两个肯能的候选者。要验证是不是真的满足条件，还需要以下步骤。
        count1 = 0;
        count2 = 0;
        for(int n : nums){
            if(n == candidate1) {//这里要用if, else if
                count1++;
            } else if(n == candidate2) {
                count2++;
            }
        }
        if(count1 > nums.length/3) res.add(candidate1);
        if(count2 > nums.length/3) res.add(candidate2);
        return res;
    }
}
