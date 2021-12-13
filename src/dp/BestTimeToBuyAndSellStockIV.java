package dp;

/**
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class BestTimeToBuyAndSellStockIV {
    //参考123 https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
    //global[i][j]: max profit of j transactions in first i days (note, here its at most j transactions)
    //local[i][j]: max profit of j transactions in first i days, and the jth transaction occurred on ith day
    //global[i][j] = max(global[i - 1][j], local[i][j]);
    //local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), global[i - 1][j] + diff)   since diff = prices[i] - prices[i - 1]

    public int maxProfit(int k, int[] prices) {
        if(k > prices.length/2) return maxP(prices);//because for n prices, we have at most n/2 transactions, so if k > n/2, it is best time to buy and sell stock II
        int[] global = new int[k + 1];
        int[] local = new int[k + 1];
        for(int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            for(int j = k; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k];
    }
    public int maxP(int[] prices){
        int res = 0;
        for(int i = 1;i < prices.length; i++){
            if(prices[i] > prices[i-1]){
                res += prices[i]-prices[i-1];
            }
        }
        return res;
    }
}
