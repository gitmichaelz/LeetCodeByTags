package array_matrix_sorting;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 */
public class SingleElementInASortedArray {
    //lo and hi are not regular index, but the pair index here. Basically you want to find the first even-index number not followed by the same number.
    public int singleNonDuplicate(int[] nums) {
        // binary search
        int n=nums.length, lo=0, hi=n/2;
        while (lo < hi) {
            int m = (lo + hi) / 2;
            if (nums[2*m]!=nums[2*m+1]) hi = m;
            else lo = m+1;
        }
        return nums[2*lo];
    }


//     public int singleNonDuplicate(int[] nums) {
//         int res = nums[0];

//         for (int i = 1; i<nums.length; ++i)
//         {
//             res^=nums[i];
//         }


//         return res;
//     }
}
