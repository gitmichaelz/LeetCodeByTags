package array_matrix_sorting;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 *
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 *
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 *
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 */
public class NextPermutation {
//take (2, 3, 6, 5, 4, 1) as an example
    //三步：1>. 从右往左找出第一个打破升序的数字 3    (6 > 3)
    //     2>. 从最右往左找第一个大于三的数字 4, 然后交换这两个数的位置。 2， 4， 6，5， 3， 1
    //     3>. 然后对于从4往后的剩余部分(不包括4)，将其翻转。
    //还有最重要一点！！！在找的时候，不要忘记“=” nums[p] >= nums[p + 1]   nums[i] <= nums[p]


    //1. scan the input array from right to left, and find the first index of element that breaks descending order
    //2. if no such element, that means next permutation will be the reverse of the whole array
    //3. if such element nums[p] exist, to the element's right, find the min element which is bigger than nums[p]
    //4. swap the two element
    //5. for the right part of nums[p] (descending part), reverse it
    public void nextPermutation(int[] nums) {
        int p = nums.length - 2;
        while(p >= 0 && nums[p] >= nums[p + 1]) {//since we are looking for the first element breaks descending order, so dont foget =, if nums[p] >= nums[p + 1], p--
            p--;
        }
        if(p >= 0) {
            int i = nums.length - 1;
            while(nums[i] <= nums[p]) {//since we are looking for the first element bigger than nums[p], so dont forget =, if nums[i] <= nums[p], i--
                i--;
            }
            swap(nums, p, i);
        }
        //reverse all elements after p
        reverse(nums, p + 1, nums.length - 1);
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j){
        while(i < j){
            swap(nums, i++, j--);
        }
    }
}
