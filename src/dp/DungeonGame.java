package dp;

import java.util.Arrays;

/**
 * The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid. Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
 *
 * The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
 *
 * Some of the rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms; other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
 *
 * To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
 *
 * Return the knight's minimum initial health so that he can rescue the princess.
 *
 * Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.
 *
 *
 *
 * Example 1:
 *
 * Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
 * Output: 7
 * Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
 */
public class DungeonGame {
    //这题思路就是从最后的位置倒推，救完公主后必须保证health point至少为1(任何时刻骑士的血槽必须大于0，否则立即挂掉)
    //因此在进入最后的位置的时候，必须保证骑士的minHP = 1 - dungeon[i][j];//dungeon[i][j]是最后公主的位置
    //拓展到任意一点，dp[i][j] 表示在进入位置i,j之前必须保有的最少健康值，则有dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j], 如果该值小于等于0，则让dp[i][j] = 1;
    //since we are using last row(i + 1)'s value, we can use 1d array, and need to scan backwards each row since we are using j+ 1 at current row
    //初始化的时候注意，每个cell先初始化为Integer.MAX_VALUE, 然后令dp[n - 1] = 1, 即骑士的血槽救完公主后至少为1.
    //怎样理解minHP = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]呢
    //比如我们在到达i，j之前已经知道最少需要的能量 E， 为了能达到这个能量，在i,j minHP = E - dungeon[i][j]
    //minHP +消耗的dungeon[i][j]才能make up to E
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);;//since we are bottoming up, we are initializing the last row with integer.max 为什么用max呢，主要是为了方便计算，Math.min(dp[i + 1][j], dp[i][j + 1]);

        //initialization
        dp[n - 1] = 1;//初始化在救完公主后骑士血槽最少为1.
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                dp[j] = Math.min(dp[j], dp[j + 1]) - dungeon[i][j];
                if(dp[j] <= 0) dp[j] = 1;//任何时候骑士的血不能等于0，最少是1
            }
        }
        return dp[0];
    }
}
