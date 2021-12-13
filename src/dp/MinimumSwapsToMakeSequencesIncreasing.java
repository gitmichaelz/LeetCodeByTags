package dp;

/**
 * You are given two integer arrays of the same length nums1 and nums2. In one operation, you are allowed to swap nums1[i] with nums2[i].
 *
 *     For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
 *
 * Return the minimum number of needed operations to make nums1 and nums2 strictly increasing. The test cases are generated so that the given input always makes it possible.
 *
 * An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * Output: 1
 */
public class MinimumSwapsToMakeSequencesIncreasing {
    //https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/discuss/161887/Bottom-up-DP-with-Optimization-(Java-Python)
    public int minSwap(int[] A, int[] B) {
        int n = A.length, prevNotSwap = 0, prevSwap = 1;

        for (int i = 1; i < n; i++) {
            boolean areBothSelfIncreasing = A[i - 1] < A[i] && B[i - 1] < B[i];
            boolean areInterchangeIncreasing = A[i - 1] < B[i] && B[i - 1] < A[i];

            if (areBothSelfIncreasing && areInterchangeIncreasing) {
                int newPrevNotSwap = Math.min(prevNotSwap, prevSwap);
                prevSwap = Math.min(prevNotSwap, prevSwap) + 1;
                prevNotSwap = newPrevNotSwap;
            } else if (areBothSelfIncreasing) {
                prevSwap++;
            } else { // if (areInterchangeIncreasing)
                int newPrevNotSwap = prevSwap;
                prevSwap = prevNotSwap + 1;
                prevNotSwap = newPrevNotSwap;
            }
        }

        return Math.min(prevSwap, prevNotSwap);
    }
}
