package tree;

import java.util.List;

/**
 * Given a triangle array, return the minimum path sum from top to bottom.
 *
 * For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.
 *
 *
 *
 * Example 1:
 *
 * Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
 * Output: 11
 * Explanation: The triangle looks like:
 *    2
 *   3 4
 *  6 5 7
 * 4 1 8 3
 * The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
 */
public class Triangle {
    //bottom up approach, more neat
    //dp[i][j] is min sum from bottom to current nums[i][j]
    //dp[i][j] = nums[i][j] + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
    //we are only using the (i + 1)th row's info, so we can reduce 2d to 1d array
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) return 0;
        int[] dp = new int[triangle.size() + 1];//这里有个 + 1的操作，是因为有个dp[i + 1][j + 1]的操作
        for(int i = triangle.size() - 1; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }





    //top down
    //dp[i][j]: the minimum sum from top till the num[i, j], which is list.get(i).get(j)
    //dp[i][j] = num[i, j] + Math.min(dp[i - 1][j - 1], dp[i - 1][j])
    //since we are only use (i - 1)th row's information, so we can use 1d array, also we are using dp[i - 1][j - 1] before overwrite it
    //so we need  to update the res array from right to left. ie, we need to scan each list backwards
    //这个解法需要注意的是，当j == i 或者 j == 0时，需要特殊处理，因为这两种情况只有一种走法
    public int minimumTotalTopDown(List<List<Integer>> triangle) {
        if(triangle == null || triangle.isEmpty() || triangle.get(0).isEmpty()) return 0;
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < triangle.size(); i++) {
            for(int j = triangle.get(i).size(); j >= 0; j--) {
                if(j > 0 && j < i) {
                    dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j - 1]);
                } else if(j == i) {
                    dp[j] = triangle.get(i).get(j) + dp[j - 1];//if i == j, last row wont have dp[j]
                } else {//j == 0
                    dp[j] += triangle.get(i).get(j);//only one way
                }
            }
        }
        for(int sum : dp) {
            min = Math.min(sum, min);
        }
        return min;
    }
}
