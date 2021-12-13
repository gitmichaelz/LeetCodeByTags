package dp;

/**
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 */
public class MaximumLengthOfRepeatedSubarray {
    //dp[i][j] max Length common subarray ending with A[i] and B[j]
    //dp formula: dp[i][j] = A[i] == B[j]? 1 + dp[i - 1][j - 1] : 0;
    public int findLength2D(int[] A, int[] B){
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(A[i - 1] == B[j - 1]){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
    //1d array,
    public int findLength(int[] A, int[] B){
        int m = A.length, n = B.length;
        int[] dp = new int[n + 1];
        int max = 0;
        for(int i = 1; i <= m; i++){
            for(int j = n; j > 0; j--){
                if(A[i - 1] == B[j - 1]){
                    dp[j] = 1 + dp[j - 1];
                    max = Math.max(max, dp[j]);
                } else {//注意。这里一定要重置为0，因为在2d里我们可以不管，但在1D里一定要设置为0
                    dp[j] = 0;
                }
            }
        }
        return max;
    }
}
