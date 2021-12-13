package dp;

/**
 * Given an integer array nums and two integers firstLen and secondLen, return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.
 *
 * The array with length firstLen could occur before or after the array with length secondLen, but they have to be non-overlapping.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,6,5,2,2,5,1,9,4], firstLen = 1, secondLen = 2
 * Output: 20
 * Explanation: One choice of subarrays is [9] with length 1, and [6,5] with length 2.
 */
public class MaximumSumOfTwoNonOverlappingSubarrays {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        for(int i = 1; i < A.length; i++){
            A[i] += A[i - 1];//calculate presum
        }
        //Lmax: the max sum of contiguous L elements before the last M elements
        //Mmax: the max sum of contiguous M elements before the last L elements
        int Lmax = A[L - 1], Mmax = A[M - 1];
        int res = A[L + M - 1];
        for(int i = L + M; i < A.length; i++){
            Lmax = Math.max(Lmax, A[i - M] - A[i - M - L]);
            Mmax = Math.max(Mmax, A[i - L] - A[i - M - L]);
            res = Math.max(res, Math.max(Lmax + A[i] - A[i - M], Mmax + A[i] - A[i - L]));
        }
        return res;
    }
}
