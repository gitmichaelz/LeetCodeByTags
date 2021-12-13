package binarySearch;

import java.util.Arrays;

/**
 * You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.
 *
 * One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.
 *
 * Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).
 *
 * Note: You cannot rotate an envelope.
 *
 *
 *
 * Example 1:
 *
 * Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    //类似LIS 用binary search做
    //tails 数组存放长度为i + 1的子序列的最小尾部 tails数组很明显是递增的，所以可以二分查找
    //NlogN解法
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0]? b[1] - a[1] : a[0] - b[0]);//注意这个排序，width升序排，如果width相同，height降序排,这样可以保证相同的width下，只能取其中一个height.(即最大的那个)
        int[] tails = new int[envelopes.length];
        int size = 0;
        for(int[] envelop : envelopes) {
            int pos = binarySearch(tails, 0, size, envelop[1]);
            tails[pos] = envelop[1];
            if(pos == size) size++;
        }
        return size;
    }

    private int binarySearch(int[] num, int left, int right, int key) {
        while(left < right) {
            int mid = left + (right - left)/2;
            if(num[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    //sort the envelopes first, then it is an lis problem(longest increasing subsequence)
    //dp[i] is the LIS ending with ith number
    //dp[i] = max{1, dp[j] + 1} which j < i and arr[j] < arr[i]
    //time: O(N^2)
    // public int maxEnvelopes(int[][] envelopes) {
    //     if(envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) return 0;
    //     Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
    //     int[] dp = new int[envelopes.length];
    //     Arrays.fill(dp, 1);
    //     int max = 0;
    //     for(int i = 0; i < envelopes.length; i++) {
    //         for(int j = 0; j < i; j++) {
    //             if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1] && dp[i] < dp[j] + 1) {
    //                 dp[i] = dp[j] + 1;
    //             }
    //         }
    //         max = Math.max(max, dp[i]);
    //     }
    //     return max;
    // }
}
