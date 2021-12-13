package twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers arr, sort the array by performing a series of pancake flips.
 *
 * In one pancake flip we do the following steps:
 *
 *     Choose an integer k where 1 <= k <= arr.length.
 *     Reverse the sub-array arr[0...k-1] (0-indexed).
 *
 * For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.
 *
 * Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,2,4,1]
 * Output: [4,2,4,3]
 */
public class PancakeSorting {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        for(int len = A.length; len > 0; len--) {
            int j = 0;
            while(A[j] != len) j++;
            reverse(A, j);
            res.add(j + 1);
            reverse(A, len - 1);
            res.add(len);
        }
        return res;
    }

    private void reverse(int[] A, int k) {
        for(int i = 0; i < k; i++, k--) {
            int tmp = A[i];
            A[i] = A[k];
            A[k] = tmp;
        }
    }
}
