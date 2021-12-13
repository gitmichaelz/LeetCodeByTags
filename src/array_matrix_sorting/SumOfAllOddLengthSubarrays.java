package array_matrix_sorting;

/**
 * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
 *
 * A subarray is a contiguous subsequence of the array.
 *
 * Return the sum of all odd-length subarrays of arr.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,4,2,5,3]
 * Output: 58
 * Explanation: The odd-length subarrays of arr and their sums are:
 * [1] = 1
 * [4] = 4
 * [2] = 2
 * [5] = 5
 * [3] = 3
 * [1,4,2] = 7
 * [4,2,5] = 11
 * [2,5,3] = 10
 * [1,4,2,5,3] = 15
 * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
 */
public class SumOfAllOddLengthSubarrays {
    /*
    Consider the subarray that contains A[i],
    we can take 0,1,2..,i elements on the left,
    from A[0] to A[i],
    we have i + 1 choices.

    we can take 0,1,2..,n-1-i elements on the right,
    from A[i] to A[n-1],
    we have n - i choices.

    In total, there are k = (i + 1) * (n - i) subarrays, that contains A[i].
    And there are (k + 1) / 2 subarrays with odd length, that contains A[i].
    And there are k / 2 subarrays with even length, that contains A[i].
    A[i] will be counted ((i + 1) * (n - i) + 1) / 2 times for our question.
     */
    public int sumOddLengthSubarrays(int[] arr) {
        int res = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            res += ((i + 1) * (n - i) + 1) /2 * arr[i];
        }
        return res;
    }
}
