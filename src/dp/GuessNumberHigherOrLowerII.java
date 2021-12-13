package dp;

/**
 * We are playing the Guessing Game. The game will work as follows:
 *
 *     I pick a number between 1 and n.
 *     You guess a number.
 *     If you guess the right number, you win the game.
 *     If you guess the wrong number, then I will tell you whether the number I picked is higher or lower, and you will continue guessing.
 *     Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 *
 * Given a particular n, return the minimum amount of money you need to guarantee a win regardless of what number I pick.
 */
public class GuessNumberHigherOrLowerII {
    //dp[i][j] represents the lowest cost from i to j.
    //for every x in [1...n], the worst case is x + max(dp[1][x - 1], dp[x + 1][j])
    //so the lowest possible cost is dp[i][j] = min(xi,...,xj);
    //take a closer look at the induction rule for k in [i, j],, dp[i][j] = min(k + max(dp[i][k - 1], dp[k + 1][j]))
    //we need to loop from left to right, and bottom to up
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 1];//to process the edge case where k == j, k + 1 > j, and j maybe equal to n
        for(int i = n; i >= 1; i--) {
            for(int j = i + 1; j <= n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= j; k++) {
                    dp[i][j] = Math.min(dp[i][j], k + Math.max(dp[i][k - 1], dp[k + 1][j]));
                }
            }
        }
        return dp[1][n];
    }
//     public int getMoneyAmount(int n) {
//          int[][] memo = new int[n + 1][n + 1];
//         return getMoneyAmountRecursive(memo, 1, n);
//     }

//     public int getMoneyAmountRecursive(int[][] memo, int start, int end){
//         if(start >= end) return 0;
//         if(memo[start][end] != 0) return memo[start][end];
//         int max = Integer.MAX_VALUE;
//         for(int i = start; i <= end; i++) {
//             max = Math.min(max, Math.max(getMoneyAmountRecursive(memo, start, i - 1), getMoneyAmountRecursive(memo, i + 1, end)) + i);
//         }
//         memo[start][end] = max;
//         return max;
//     }
}
