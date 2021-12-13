package array_matrix_sorting;

/**
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 *
 * Note that you must do this in-place without making a copy of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int pos = 0;
        for(int n : nums){
            if(n != 0){
                nums[pos++] = n;
            }
        }
        while(pos < nums.length){
            nums[pos++] = 0;
        }
    }
}
