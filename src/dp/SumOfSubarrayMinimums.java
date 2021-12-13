package dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 */
public class SumOfSubarrayMinimums {
    //1, 3 1,       1, 1 2, 1 2 4
    //1, 1 2, 1 2 4, 3 1, 3 1 2,  3 1 2 4
    //res = sum(A[i] * f(i)), where f(i) is the number of subarrays, A[i] is the minimum in each subarray
    //to get f(i), we need to find left[i] and right[i], where left[i] is length of strict bigger numbers on the left of A[i]
    //and right[i] is length of bigger numbers on the right of A[i]
    //f(i) = left(i) * right(i)
    //use a

    public int sumSubarrayMins1(int[] arr) {
        long res = 0, mod = 1000000000 + 7;
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<int[]> s1 = new ArrayDeque<>();
        Deque<int[]> s2 = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int count = 1;
            while (!s1.isEmpty() && s1.peek()[0] > arr[i]) {
                count += s1.pop()[1];
            }
            s1.push(new int[] {arr[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; i--) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= arr[i]) {//to handle duplicates, we need to set non-strict and strict subarray
                count += s2.pop()[1];
            }
            s2.push(new int[] {arr[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; i++) {
            res = (res + (long)arr[i] * left[i] *  right[i]) % mod;
        }
        return (int)res;
    }

    //one pass
    public int sumSubarrayMins(int[] A) {
        Deque<Integer> stack = new ArrayDeque<>();//store the index of element
        int len = A.length;
        long res = 0, mod = 1000000000 + 7;
        for (int i = 0, j = 0, k = 0; i <= len; i++) {
            while (!stack.isEmpty() && A[stack.peek()] > (i == len? 0 : A[i])) {
                j = stack.pop();
                k = stack.isEmpty()? -1 : stack.peek();
                res = (res + (long)A[j] * (i - j) * (j - k)) % mod;
            }
            stack.push(i);
        }
        return (int) res;
    }
}
