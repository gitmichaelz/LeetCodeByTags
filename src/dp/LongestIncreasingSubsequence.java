package dp;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 */
public class LongestIncreasingSubsequence {
    //nlogn的思想是，用一个tails数组来存储所有递增子序列的最小末尾(举例说明)，容易证明这样tails数组里的元素是递增的，用二分查找来找到下一个元素的插入位置，
    //如果令tails[pos] = key, 如果pos == idx，idx++;//idx is current tail position
    //https://leetcode.com/problems/longest-increasing-subsequence/discuss/74824/JavaPython-Binary-search-O(nlogn)-time-with-explanation
    //http://blog.csdn.net/left_la/article/details/11951085
    public int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        //store the smallest tails of all incresing susequences with length i + 1, in tails[i]
        int[] tails = new int[nums.length];
        int idx = 0;//current idx of tail position
        for(int key : nums) {
            int pos = insertPosition(tails, 0, idx, key);//注意，此时idx位置上还是0，因为idx++后还没有新的更新操作
            tails[pos] = key;
            if(pos == idx) {
                idx++;
            }
        }
        return idx;
    }
    private int insertPosition(int[] nums, int left, int right, int key) {
        while(left < right) {
            int mid = left + (right - left)/2;
            if(nums[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;//if nums[mid] > key, position mid still could be the insertion position for example:  1 3 5, key = 2,
            }
        }
        return left;
    }

    /**
     state:
     错误的方法：f[i] 表示前i个数字中最长的LIS的长度
     正确的方法：f[i] 表示前i个数字中以第i个结尾的LIS的长度
     function: f[i] = max{f[j] + 1}, j < i && arr[j] < arr[i]
     initialize: f[0], f[1]....fn-1] = 1;
     answer: max(f[0], f[1]....f[n-1])
     time: N^2, space: N
     */
//     public int lengthOfLIS(int[] nums) {
//         if(nums == null || nums.length == 0) {
//             return 0;
//         }
//         int[] arr = new int[nums.length];//this array to store the longest subsequence length ending at index i;
//         Arrays.fill(arr, 1);
//         int max = 0;
//         for(int i = 0; i < nums.length; i++) {
//             for(int j = 0; j < i; j++) {
//                 if(nums[j] < nums[i] && arr[j] + 1 > arr[i]) {
//                     arr[i] = arr[j] + 1;
//                 }
//             }
//             max = Math.max(max, arr[i]);
//         }
//         return max;
//     }
}
