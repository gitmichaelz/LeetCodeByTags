package prefixSum;

/**
 * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k. If there is no such subarray, return -1.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1], k = 1
 * Output: 1
 */
public class ShortestSubarrayWithSumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        int[] prefixMinSum = new int[N];
        prefixMinSum[N - 1] = A[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            prefixMinSum[i] = prefixMinSum[i + 1] < 0 ? prefixMinSum[i + 1] + A[i] : A[i];
        }
        int len = N + 1;
        int sum = 0, j = 0;
        for (int i = 0; i < N; i++) {
            sum += A[i];
            while (j < i && sum - prefixMinSum[j] >= K) {
                sum -= A[j];
                j++;
            }
            if (sum >= K) {
                len = Math.min(len, i - j + 1);
            }
        }
        return len == N + 1 ? -1 : len;
    }
}
