package dp;

/**
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 */
public class PartitionEqualSubsetSum {
    // 1d array dp[s] would be true if we can get sum 's' from previous items(through i iterations)
    //dp[s] = dp[s] || dp[s - nums[i]] if s >= nums[i],
    //        this dp[s] is from last iteration
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i : nums) {
            sum += i;
        }
        if((sum & 1) == 1) return false;//odd, false
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        //initialize. sum = 0, can always be found by an empty subset
        dp[0] = true;
        //if there is only 1 item, we can get s only if its value equals current sum 's'
        for(int s = 1; s <= sum; s++) {
            dp[s] = nums[0] == s;
        }
        for(int i = 1; i < nums.length; i++) {//here i from 1, because we already processed i = 0 above
            for(int s = sum; s >= nums[i]; s--) {
                dp[s] = dp[s] || dp[s - nums[i]];
            }
        }
        return dp[sum];
    }


    //dp[i][s] will be true if we can make sum s from the first i items

//     public boolean canPartition(int[] nums) {
//         int sum = 0;
//         for(int i : nums) {
//             sum += i;
//         }
//         if((sum & 1) == 1) return false;//odd, false
//         boolean[][] dp = new boolean[nums.length][sum/2 + 1];
//         //initialize. sum = 0, can always be found by an empty subset
//         for(int i = 0; i < nums.length; i++) {
//             dp[i][0] = true;
//         }
//         //if there is only one item, we can set dp[i][s] = true only if its value equals s
//         for(int s = 1; s <= sum/2; s++) {
//             dp[0][s] = nums[0] == s;
//         }

//         for(int i = 1; i < nums.length; i++) {
//             for(int s = 1; s <= sum/2; s++) {
//                 //if we can get sum 's' without the number at index 'i'
//                 if(dp[i - 1][s]) {
//                     dp[i][s] = true;
//                 } else if(s >= nums[i]) {//else if we can find a subset to get the remaining sum
//                     dp[i][s] = dp[i - 1][s - nums[i]];//dp[i][s] is true if we can get sum 's - nums[i]' from first i - 1 items
//                 }
//             }
//         }
//         return dp[nums.length - 1][sum/2];
//     }
}
