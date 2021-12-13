package dp;

/**
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 */
public class BestTimeToBuyAndSellStockIII {
    //https://blog.csdn.net/linhuanmars/article/details/23236995 这个帖子好好读，下面的读者提问也要好好读一下
    //global[i][j]: max profit of j transactions upon ith day (note, here its at most j transactions)
    //local[i][j]: max profit of j transactions upon ith days, and the jth transaction occurred on ith day
    //global[i][j] = max(global[i - 1][j], local[i][j]); 取当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）
    //local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)   看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了
    //结合递推式，我们可以把2D array转换为1D，因为我们在计算local[i][j]的时候会用到global[i - 1][j - 1], 所以在1D array里，如果j是从小到大来更新的话，local[j] = global[j - 1] ...公式里的global[j - 1]实际上是global[i][j - 1],所以为了避免overwrite，我们让j从大到小更新，这样global[j - 1] (也就是实际的global[i - 1][j - 1])就不会被override

    //local[i][j] = Math.max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff);
    //global[i][j] = Math.max(global[i - 1][j], local[i][j]);
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int[] global = new int[3];
        int[] local = new int[3];
        for(int i = 1; i < prices.length; i++) {
            //diff只需要看当天的盈亏就可以了，因为global和local会把前面的盈亏考虑进来，要知道这一天的效果，只需要看local[i-1][j]+diff就可以了哈~
            int diff = prices[i] - prices[i - 1];
            for(int j = 2; j >= 1; j--) {
                local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[2];
    }
    //2D ARRAY.
    // public int maxProfit(int[] prices){
    //     int n = prices.length;
    //     if(prices == null || prices.length == 0) return 0;
    //     int[][] global = new int[n][3];
    //     int[][] local = new int[n][3];
    //     for(int i = 1; i < prices.length; i++){
    //         int diff = prices[i] - prices[i - 1];
    //         for(int j = 1; j <= 2; j++){
    //             local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
    //             global[i][j] = Math.max(global[i - 1][j], local[i][j]);
    //         }
    //     }
    //     return global[n - 1][2];
    // }
}
