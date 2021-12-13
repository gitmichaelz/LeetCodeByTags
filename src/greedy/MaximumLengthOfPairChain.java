package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 */
public class MaximumLengthOfPairChain {
    //这题最好用greedy,类似于meeting schedule, 注意，一定要sort by end, 因为我们要尽可能从小的end开始，这样chain才可能拿到最长的
    public int findLongestChain(int[][] pairs){
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int count = 1, end = pairs[0][1];
        for(int i = 1; i < pairs.length; i++){
            if(pairs[i][0] > end){
                count++;
                end = pairs[i][1];
            }
        }
        return count;
    }


    //dp[i] is the max length of pair chain ending at pairs[i] (sort pairs first)
    // public int findLongestChain(int[][] pairs){
    //     Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
    //     int n = pairs.length;
    //     int[] dp = new int[n];
    //     Arrays.fill(dp, 1);
    //     for(int i = 1; i < n; i++){
    //         for(int j = 0; j < i; j++){
    //             if(pairs[i][0] > pairs[j][1]){
    //                 dp[i] = Math.max(dp[i], dp[j] + 1);
    //             }
    //         }
    //     }
    //     return dp[n - 1];
    // }
}
