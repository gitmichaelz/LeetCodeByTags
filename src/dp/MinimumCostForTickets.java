package dp;

/**
 * You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
 *
 * Train tickets are sold in three different ways:
 *
 *     a 1-day pass is sold for costs[0] dollars,
 *     a 7-day pass is sold for costs[1] dollars, and
 *     a 30-day pass is sold for costs[2] dollars.
 *
 * The passes allow that many days of consecutive travel.
 *
 *     For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
 *
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 *
 *
 *
 * Example 1:
 *
 * Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 * Output: 11
 */
public class MinimumCostForTickets {
    //https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226670/Java-DP-Solution-with-explanation-O(n)
    public int mincostTickets(int[] days, int[] costs) {
        int n = days[days.length - 1];
        boolean[] travel = new boolean[n+1];
        int[] amount = new int[n+1];
        for(int day : days) {
            travel[day] = true;
        }
        amount[0] = 0;
        for(int i=1; i<=n; ++i) {
            if(travel[i]) {
                int min = amount[i-1] + costs[0];
                min = Math.min(min, (i < 7 ? 0 : amount[i-7]) + costs[1]);
                min = Math.min(min, (i < 30 ? 0 : amount[i-30]) + costs[2]);
                amount[i] = min;
            } else {
                amount[i] = amount[i-1];
            }
        }
        return amount[n];
    }
}
