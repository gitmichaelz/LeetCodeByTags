package array_matrix_sorting;

/**
 * Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].
 *
 * Return the array in the form [x1,y1,x2,y2,...,xn,yn].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,5,1,3,4,7], n = 3
 * Output: [2,3,5,4,1,7]
 * Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
 */
public class ShuffleTheArray {
    //思想类似于LC1920, 用x = a + b * y来存储两个数nums[i] (a), nums[n + i] (b)，然后再扫一遍，for i in [0, n - 1]，我们nums[2 * i] = x % y,
    //对于nums[2 * i + 1] = x / y
    //其中y的选择有讲究，必须比nums[i]大，这里我们让y = 1001
    public int[] shuffle(int[] nums, int n) {
        int y = 1001;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] + nums[n + i] * y;
        }

        for (int i = n - 1; i >= 0; i--) {//scan backwards, since in pre step we were storing new values for the first n items, to avoid overwrite, we need to scan backwards
            nums[2 * i + 1] = nums[i] / y;//same reason, for example, if i = 0, if we compute nums[2 * i] first, nums[i] will be overwriten, so we need to calculate from right to left
            nums[2 * i] = nums[i] % y;
        }
        return nums;
    }
}
