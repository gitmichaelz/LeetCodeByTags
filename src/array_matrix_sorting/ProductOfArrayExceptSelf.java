package array_matrix_sorting;

/**
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 */
public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        //first scanning to get product of left elements of nums[i]
        for(int i = 0, product = 1; i < nums.length; i++) {
            res[i] = product;
            product *= nums[i];
        }
        //then get product of right elements of nums[i], productExceptSelf = left product * right product
        for(int i = nums.length - 1, product = 1; i >= 0; i--) {
            res[i] *= product;
            product *= nums[i];
        }
        return res;
    }
}
