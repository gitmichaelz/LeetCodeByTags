package dp;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if(amount<1) return 0;
        return helper(coins, amount, new int[amount + 1]);
    }

    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
        if(rem<0) return -1; // not valid
        if(rem==0) return 0; // completed
        if(count[rem] != 0) return count[rem]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for(int coin : coins) {
            int res = helper(coins, rem-coin, count);
            if(res>=0 && res < min)
                min = 1+res;
        }
        count[rem] = (min==Integer.MAX_VALUE) ? -1 : min;
        return count[rem];
    }
    //dp[a] = Math.min(dp[a], dp[a - coins[i]] + 1);
    // public int coinChange(int[] coins, int amount) {
    //     if(amount == 0) return 0;
    //     int[] dp = new int[amount + 1];
    //     for(int i = 1; i <= amount; i++) {
    //         dp[i] = amount + 1;//make dp[i] as a number that larger than amount
    //     }
    //     for(int i = 0; i < coins.length; i++) {
    //         for (int j = coins[i]; j <= amount; j++) {//这里j可以从coins[i]开始，因为要用dp[j - coins[i]]
    //             dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
    //         }
    //     }
    //     return dp[amount] == amount + 1? -1 : dp[amount];
    // }
//     public int coinChange(int[] coins, int amount) {
//         int n = coins.length;
//         int[][] dp = new int[n][amount + 1];

//         for(int i = 0; i < n; i++) {
//             for(int j = 1; j <= amount; j++) {
//                 dp[i][j] = amount + 1;
//             }
//         }

//         for(int i = 0; i < n; i++) {
//             for(int j = 1; j <= amount; j++) {
//                 if(i > 0) {//exclude i
//                     dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
//                 }
//                 if(j >= coins[i]) {//include i, we need to add + 1 if include current coin
//                     dp[i][j] = Math.min(dp[i][j - coins[i]] + 1, dp[i][j]);//important!!!
//                 }
//             }
//         }
//         return dp[n - 1][amount] == amount + 1? -1 : dp[n - 1][amount];
//     }
}
