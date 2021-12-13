package twoPointers;

/**
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous
 * subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSizeSubarraySum {
    //The O(n) solution is to use two pointers: l and r. First we move r until we get a sum >= s, then we move l to the right until sum < s.
    // In this process, store the minimum length between l and r. Since each element in nums will be visited by l and r for at most once. This algorithm is of O(n) time.
    //双指针的思路一般是先移动右指针直到满足条件，然后移动左指针直到不满足条件
    public int minSubArrayLen(int s, int[] nums){
        int minLen = Integer.MAX_VALUE;
        for(int left = 0, right = 0, sum = 0; right < nums.length; right++){
            sum += nums[right];
            while(sum >= s){
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE? 0 : minLen;
    }



    //follow up中nlogn解法, 先求preSum,在对每一个元素坐起点，找到使得window和满足>=s最小的end
    // public int minSubArrayLen(int s, int[] nums){
    //     if(nums == null || nums.length == 0) return 0;
    //     int n = nums.length;
    //     int[] preSum = new int[n];
    //     preSum[0] = nums[0];
    //     for(int i = 1; i < n; i++){
    //         preSum[i] = nums[i] + preSum[i - 1];
    //     }
    //     int minLen = Integer.MAX_VALUE;
    //     for(int start = 0; start < n; start++){
    //         int end = findWindowEnd(start, preSum, s);
    //         if(end == -1) break;
    //         minLen = Math.min(minLen, end - start + 1);
    //     }
    //     return minLen == Integer.MAX_VALUE? 0 : minLen;
    // }
    // //find the end of min Window such that sums[start, end] >= s
    // //二分，找下界问题
    // private static int findWindowEnd(int start, int[] sums, int s){
    //     int end = sums.length - 1, offset = start == 0? 0 : sums[start - 1];//offset 是指sums[0, start - 1];
    //     while(start < end){
    //         int mid = start + (end - start)/2;
    //         int sum = sums[mid] - offset;
    //         if(sum >= s){
    //             end = mid;
    //         } else {
    //             start = mid + 1;
    //         }
    //     }
    //     //当start==end,要多一个判断
    //     return sums[start] - offset >= s? start : -1;//注意，这一行判断必须要加，因为有start== end, 但sum[start] - offset小于 s的可能
    // }

}
