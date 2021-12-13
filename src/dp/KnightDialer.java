package dp;

import java.util.Arrays;

/**
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:
 *
 * A chess knight can move as indicated in the chess diagram below:
 *
 * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
 *
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 *
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
 *
 * As the answer may be very large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 10
 * Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
 */
public class KnightDialer {
    //2d -> 1d, from the above formula dp[i][j] = dp[i][j] + dp[i - 1][key], we only need 2 rows.
    public int knightDialer(int n) {
        int[][] moves = new int[][] {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3},{2, 4}};
        int[] dp = new int[10];
        Arrays.fill(dp, 1);

        for(int i = 2; i <= n; i++) {
            int[] next = new int[10];
            for(int j = 0; j < 10; j++) {
                int[] pre = moves[j];
                for(int num : pre) {
                    next[j] = (next[j] + dp[num]) % mod;
                }
            }
            dp = next;
        }
        int total = 0;
        for(int num : dp) {
            total = (total + num) % mod;
        }
        return total;
    }



    //bottom up, 2d dp
    //dp[i][j]: total number of distinct phone number of length i, starting digit j
    //dp[i][j] = dp[i][j] + dp[i - 1][key], key is the pre step where j came from, for example j = 0, key = {4, 6}
    public int knightDialerdp(int n) {
        int[][] moves = new int[][] {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3},{2, 4}};
        int[][] dp = new int[n + 1][10];
        Arrays.fill(dp[1], 1);//base case, for any single number, there is only 1 way to form a phone number
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < 10; j++) {
                int[] pre = moves[j];//for example, moves[0] = {4, 6}, since 0 can jump to 4 and 6, it also means we can get to 0 from 4 and 6
                for(int num : pre) {// Previous move of knight in order to reach digit j
                    dp[i][j] = (dp[i][j] + dp[i - 1][num]) % mod;// cumulatively add from the previous knight move. For instance., F(2, 0) -> F(1,4) +  F(1,6) F(2,6) -> F(1,0) + F(1,1) + F(1,7)
                }
            }
        }
        int total = 0;
        for(int i = 0; i < 10; i++) {
            total = (total + dp[n][i]) % mod;
        }
        return total;
    }




    //top down, recursion with memoization
    int mod = 1000000007;
    public int knightDialer1(int n) {
        int[][] moves = new int[][] {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3},{2, 4}};
        Integer[][] cache = new Integer[n + 1][10];//cache[i][j]: the number of distinct phone number of length i, starting digit j
        int count = 0;
        for(int i = 0; i <= 9; i++) {
            count = (count + dfs(n, i, moves, cache)) % mod;
        }
        return count;
    }

    private int dfs(int n, int start, int[][] moves, Integer [][] cache) {
        if(n == 1) return 1;
        if(cache[n][start] != null) return cache[n][start];
        int count = 0;
        for(int next : moves[start]) {
            count = (count + dfs(n - 1, next, moves, cache)) % mod;
        }
        return cache[n][start] = count;
    }
}
