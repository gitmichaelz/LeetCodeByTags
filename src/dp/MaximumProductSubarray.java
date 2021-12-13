package dp;

/**
 * Given an integer array nums, find a contiguous non-empty subarray within the array that has the largest product, and return the product.
 *
 * It is guaranteed that the answer will fit in a 32-bit integer.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 */
public class MaximumProductSubarray {
    //维护一个curMax, curMax = Math.max(curMax * nums[i], nums[i]).  why? 因为我们在求连续的subarray,需要根据nums[i]来更新
    //因为元素有可能为负数，所以需要维护一个curMin,一个负数乘以curMin肯定比乘以curMax大，
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int curMin = nums[0];
        int curMax = nums[0];
        int globalMax = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < 0) {//if nums[i] is negative, we need to swap curMin and curMax
                int tmp = curMin;
                curMin = curMax;
                curMax = tmp;
            }
            curMin = Math.min(nums[i], curMin * nums[i]);
            curMax = Math.max(nums[i], curMax * nums[i]);
            globalMax = Math.max(globalMax, curMax);
        }
        return globalMax;
    }

    //follow up: 规定结果不能超过一个范围，然后返回最优解。[5,2,2,2,2,2,5]  不让超过35的话，在遇到第三个2 的时候超过35，就应该去掉前面的5。
    // 最后结果应该是[2,2,2,2,2] 32。想法：sliding window, 遇到大过35的话，update sliding window左边（参见713）。如果有负数的话，一个思路是先用绝对值，然后update answer的时候，检查一下从left到right的负号数，如果负号数为偶数，则update。

    //需要记录curMax, curMin的起始坐标, curMin_start, curMax_start, 当出现元素为负数是，交换curMin curMax以及起始坐标，curMin_start, curMax_start
    public static int maxProductWithLimit(int[] nums, int limit) {
        if (nums == null || nums.length == 0) return 0;
        int curMax = nums[0];
        int curMin = nums[0];
        int globalMax = nums[0];
        for (int i = 1, curMin_start = 0, curMax_start = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                int tmp = curMax;
                curMax = curMin;
                curMin = tmp;

                tmp = curMax_start;
                curMax_start = curMin_start;
                curMin_start = tmp;
            }
            if (curMax * nums[i] < nums[i]) {
                curMax = nums[i];
                curMax_start = i;
            } else {
                curMax *= nums[i];
            }

            if (curMin * nums[i] > nums[i]) {
                curMin = nums[i];
                curMin_start = i;
            } else {
                curMin *= nums[i];
            }

            if (globalMax < curMax && curMax <= limit) {
                globalMax = curMax;
            }
            if (curMax > limit) {
                //count the number of negatives from max_
                int count = 0;
                for (int j = curMax_start; j <= i; j++) {
                    if (nums[j] < 0) {
                        count++;
                    }
                }
                while(curMax_start < i && (curMax > limit || (curMax < 0 && count % 2 == 1))) {//if curMax is negative and number of negative numbers in the window is odd
                    if (nums[curMax_start] < 0) {
                        count--;
                    }
                    curMax /= nums[curMax_start++];
                }
                if (globalMax < curMax) {
                    globalMax = curMax;
                }
            }
        }
        return globalMax;
    }
}
