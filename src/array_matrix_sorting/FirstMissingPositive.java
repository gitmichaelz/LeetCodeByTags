package array_matrix_sorting;

/**
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 5 * 10^5
 *     -2^31 <= nums[i] <= 2^31 - 1
 */
public class FirstMissingPositive {
    //similar to counting sort
    //we put element in their right place of the array. how? we swap nums[i] and nums[nums[i] - 1]'s value if nums[i] is not in the right place
    //for example, 1, should be in nums[0], we skip 0 and negative numbers and  numbers larger than nums.length
    //also if i already in its right place nums[i - 1], we skip it to next number
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {//for example, 3 should be in nums[3 - 1]
                int tmp = nums[nums[i] - 1];//注意： 这里一定是先把nums[nums[i] - 1] assign 给tmp，否则要是先把nums[i]assign给tmp 先求nums[i]， 那后边的nums[nums[i] - 1]就不是之前的那个nums[nums[i] - 1]
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
                i--;//把nums[i]放到该放的位置后，新的i位置的数需要重新判断，所以i--
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
