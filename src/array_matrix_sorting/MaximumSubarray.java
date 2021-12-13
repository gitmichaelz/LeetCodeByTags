package array_matrix_sorting;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 *
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 * Constraints:
 *     1 <= nums.length <= 105
 *     -104 <= nums[i] <= 104

 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarray {
    //O(n) solution
    //idea is, to find the contiguous subarray which has the largest sum, we need to find the starting point
    //starting point must meet: if (current sum + current num) > current num; we can add current num to current sum,
    //which is current sum += current num; if not, means current num can be the starting point and make current sum = current num;
    //besides, we need to maintain a maxSum to track the max sum of subarray
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0, curSum = 0; i < nums.length; i++) {
            if(curSum + nums[i] < nums[i]) {
                curSum = nums[i];
            } else {
                curSum += nums[i];
            }
            if(maxSum < curSum) {
                maxSum = curSum;
            }
        }
        return maxSum;
    }

    /*
     *the following is divide and conquer solution, which takes nlogn time
     */
//     public int maxSubArray(int[] nums) {
//         if(nums == null || nums.length == 0) return 0;
//         return maxSubArray(nums, 0, nums.length - 1);
//     }

//     private int maxSubArray(int[] nums, int left, int right) {
//         if(left == right) return nums[left];
//         int mid = (left + right)/2;
//         int leftMax = maxSubArray(nums, left, mid);
//         int rightMax = maxSubArray(nums, mid + 1, right);
//         int crossingMax = crossingMaxSubArray(nums, left, mid, right);
//         if(leftMax >= rightMax && leftMax >= crossingMax) {//important: dont forget =, see the case {-1, -1, -2, -2}
//             return leftMax;
//         } else if(rightMax >= leftMax && rightMax >= crossingMax) {
//             return rightMax;
//         } else {
//             return crossingMax;
//         }
//     }

//     private int crossingMaxSubArray(int[] nums, int left, int mid, int right) {
//         int leftMaxSum = Integer.MIN_VALUE;
//         int rightMaxSum = Integer.MIN_VALUE;
//         int sum = 0;
//         for(int i = mid; i >= left; i--) {
//             sum += nums[i];
//             if (sum > leftMaxSum) {
//                 leftMaxSum = sum;
//             }
//         }
//         sum = 0;
//         for(int i = mid + 1; i <= right; i++) {
//             sum +=  nums[i];
//             if (sum > rightMaxSum) {
//                 rightMaxSum = sum;
//             }
//         }
//         return leftMaxSum + rightMaxSum;
//     }
}
