package dp;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 *     After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    //define 3 state; buy, sell, rest
    //buy[i] is the max profit in first i days with ith day being buy
    //sell[i] is the max profit in first i days with ith day being sell
    //rest[i] is the max profit in first i days with ith day being rest
    // 1> buy[i] = max(rest[i - 1] - price, buy[i - 1])    we have to rest before buy
    // 2> sell[i] = max(buy[i - 1] + price, sell[i - 1]);  we have to buy before sell
    // 3> rest[i] = max(rest[i - 1], sell[i - 1], buy[i - 1]); we can rest any day after buy, sell, and rest
    // also we know that rest[i] always equal to sell[i - 1]
    // so we only need two equations: 1> buy[i] = max(sell[i - 2] - price, buy[i - 1])     2> sell[i] = max(buy[i - 1] + price, sell[i - 1])
    //since we are only use i - 1 and i - 2 these 2 preceding values, we can use O(1) space
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length < 2) return 0;
        //为什么需要两个buy 一个buy 一个preBuy因为第一个方程会把buy原先的值覆盖掉，而原先的值在第二个方程里要用到，所以我们要两个buy
        //为什么需要两个sell, 因为一个sell可以用来存储前一个sell和当前sell, 然后在这之前用一个preSell来保存sell,这样sell更新后preSell就变为前两次的值而不是前一次
        int buy = Integer.MIN_VALUE, sell = 0, preBuy = 0, preSell = 0;
        for(int price : prices) {
            preBuy = buy;
            buy = Math.max(preSell - price, buy);
            preSell = sell;
            sell = Math.max(preBuy + price, sell);
        }
        return sell;
    }
}
