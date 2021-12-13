package dp;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 */
public class CoinChange2 {
    //dp[i][j]:the total number of combinations that for first i coins can make up amount j
    //dp[i][j] = dp[i - 1][j](cur coin excluded) + dp[i][j - coins[i - 1]](cur Coin included)
    //如何理解dp[i][j - coins[i - 1]]就是代表cur coin included to make up amount j? 因为coin可以被使用无数次
    //用包含curCoin (i - 1th coin)的first i coins 来make up amount j的组合数就等效于curCoin来make up amount j - coins[i - 1]
    //initialization: dp[i][0] = 1
    public int change2d(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];//dp[i][j] means the total number of ways for first i coins can make up amount j
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i < dp.length; i++) {//从前1个开始计算，前0个对任意amount > 0 都是0
            for(int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if(j >= coins[i - 1]) {//第i个是coins[i - 1]
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amount];
    }

    //2d -> 1d, since we only use last row's info and cur row's info, we can use 1d array instead
    //dp[j] = dp[j](cur coin excluded) + dp[j - coins[i]](j >= coins[i]),  coins[i]: i starts from 0
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length; i++) {
            for(int j = coins[i]; j <= amount; j++) {//j可以从coins[i]来开始计算。any excluded coin, dp[i][j] = dp[i - 1][j]
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
