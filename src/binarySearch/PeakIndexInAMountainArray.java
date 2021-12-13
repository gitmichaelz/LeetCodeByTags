package binarySearch;

/**
 * Let's call an array arr a mountain if the following properties hold:
 *
 *     arr.length >= 3
 *     There exists some i with 0 < i < arr.length - 1 such that:
 *         arr[0] < arr[1] < ... arr[i-1] < arr[i]
 *         arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 *
 * Given an integer array arr that is guaranteed to be a mountain, return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [0,1,0]
 * Output: 1
 *
 * Example 2:
 *
 * Input: arr = [0,2,1,0]
 * Output: 1
 */
public class PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] A) {
        int start = 0, end = A.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
