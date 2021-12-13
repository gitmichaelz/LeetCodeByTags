package dp;

/**
 * Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.
 *
 * Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.
 *
 * Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [5,3,4,5]
 * Output: true
 */
public class StoneGame {
    //in general case, if we dont know the number of piles which could be odd and even
    //we use dp[i][j] indicates from [i, j],the biggest number of stones you can get more than opponent picking piles in piles[i] ~ piles[j].
    //so dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]). why? let's assume it's alex turn to pick from [i, j]
    //and he can either choose piles[i] or piles[j], then the remaining [i - 1, j] or [i, j - 1] will be Lee's turn to pick first.
    //so it is dp[i + 1][j] or dp[i][j - 1] is biggest number of stones LEE can get over than alex picking from remaining range
    //so dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]).
    //initialization: dp[i][i] = piles[i];  since if only one pile can be pick
    //then we start from small subarray with length(2, 3, 4..), then expands to full array
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        //some skill to mention to process the equation dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1])
        //if you think we are using the value dp[i + 1][j], and dp[i][j - 1], so we need a out for loop from i = n - 1 and inner for loop from j = 1
        //then its wrong. because we are dealing with an range [i, j], i must be smaller than j. so we must calculate small range, and the big range can
        //be derived from small range
        for(int d = 1; d < n; d++) {
            for(int i = 0; i + d < n; i++) {
                int j = i + d;
                dp[i][j] = Math.max(piles[i] -  dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }





    //since we has even number of piles, this leads to an interesting fact:
    //alex can always pick odd piles or always pick even piles!
    //For example,
    //If Alex wants to pick even indexed piles piles[0], piles[2], ....., piles[n-2],
    //he picks first piles[0], then Lee can pick either piles[1] or piles[n - 1].
    //Every turn, Alex can always pick even indexed piles and Lee can only pick odd indexed piles.
    //
    //In the description, we know that sum(piles) is odd.
    //If sum(piles[even]) > sum(piles[odd]), Alex just picks all evens and wins.
    //If sum(piles[even]) < sum(piles[odd]), Alex just picks all odds and wins.
    // public boolean stoneGame(int[] piles) {
    //     return true;
    // }
}
