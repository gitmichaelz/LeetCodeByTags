package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 */
public class FindAllNumbersDisappearedInAnArray {
    //scan the input array from left to right, for each element, we find it's position in the array and make the element in that position negative
    //then we scan it again and once we find an element at position i is positive, we know that there is no element which equals to i + 1 in the input array
    public List<Integer> findDisappearedNumbers(int[] nums){
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        for(int i = 0; i < nums.length; i++){
            int index = Math.abs(nums[i]) - 1;
            if(nums[index] > 0){
                nums[index] = -nums[index];
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0){
                res.add(i + 1);
            }
        }
        return res;
    }
}
