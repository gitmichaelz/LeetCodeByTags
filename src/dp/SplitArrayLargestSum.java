package dp;

/**
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 *
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum {
    //for this question, we have a lower bound(max, when m = nums.length) and upper bound(sum, when m = 1).
    //and the subarray's largest sum is in [max, sum], we can use binary search directly to get the answer, let's lo = max, hi = sum
    //for each mid = (lo + hi)/2, which means we split nums into m part and the largest sum is mid. We check nums to see how many subarrays
    //can we get if their largest sum not more than mid. if count > m, which means mid is too small, we need to increase left = mid + 1 so that
    //the number of subarrays can be decrease to m or less.
    //and once we get count <= m, which means we successfully find the proper number of subarrays which sums are equal or less than mid, so we
    //set hi = mid, and continue to find the lowest one which can meet such requirement(subarrary with larget sum = mid), and eventually we can
    //find our answer which is the minimal largest sum
    public int splitArray(int[] nums, int m) {
        int max = 0, sum = 0;
        for(int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }
        int lo = max, hi = sum;
        while(lo < hi) {//break condition is lo == hi
            int mid = lo + (hi - lo)/2;
            int count = split(nums, mid);
            if(count > m){
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
    //return the number of sumarrays if we split nums with each subarray's largest sum is not more than largestSum
    private int split(int[] nums, int largestSum){
        int pieces = 1;//
        int tmpSum = 0;
        for(int num: nums) {
            if(tmpSum + num > largestSum) {
                tmpSum = num;
                pieces++;
            } else {
                tmpSum += num;
            }
        }
        return pieces;
    }
    //dp[i][j] represents the minimum largest subarray sum for splitting nums[0...i] into j parts
    //dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], nums[k + 1] + ... + nums[i])), k belongs to [0, i - 1]
    // public int splitArray(int[] nums, int m) {
    //     if(nums == null || nums.length == 0) return 0;
    //     if(nums.length == 1) return nums[0];
    //     int n = nums.length;
    //     int[][] dp = new int[n][m + 1];
    //     for(int i = 0; i < n; i++) {
    //         for(int j = 0; j <= m; j++) {
    //             dp[i][j] = Integer.MAX_VALUE;
    //         }
    //     }
    //     // Base case 1
    //     dp[0][1] = nums[0];
    //     // Base case 2 - dp[i][1] is the sum from nums[0] to nums[i]
    //     for(int i = 1; i < n; i++) {
    //         dp[i][1] = dp[i - 1][1] + nums[i];//when m = 1; means subarray is the array itself, we need can get the sum of [0..i] when m = 1
    //     }
    //     //we can start from i = 1, because dp[i][j] depend on previous col and all rows before i, also we already taken care of case i = 0 at the start
    //     for(int i = 1; i < n; i++) {
    //         for(int j = 2; j <= m; j++) {//j start from 2, cause we already initialized case j = 1 above
    //             for(int k = 0; k < i; k++) {
    //                 dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], dp[i][1] - dp[k][1]));// dp[i][1] - dp[k][1], sum between nums[k+1] to nums[i]
    //             }
    //         }
    //     }
    //     return dp[n - 1][m];
    // }
}
