package Google;

import java.util.Arrays;

public class MaximumHeightByStackingCuboids {
    public static void main(String[] args) {

    }
    //if we can put one cuboid i on another one j, we must have width[i] <= width[j] and length[i] <= length[j] and  height[i] < height[j]
    //so how to get the maximum height?
    //for 3 edges of each cuboid, we always use the biggest edge as height,
    //the above condition will hold after we sort each edge of the cuboid, so that
    //small[i] <= small[j] and mid[i] <= mid[j] and big[i] <= big[j]
    //after sorting edges for each cuboid, we compare each two cuboids and if they satisfy the above condition, we can put
    //one on another
    public int maxHeight(int[][] A) {
        for (int[] a : A) {
            Arrays.sort(a);//sort edges for each cuboid
        }
        //cuboids sorted in descending order
        Arrays.sort(A, (a, b) -> {
            if (a[0] != b[0])
                return b[0] - a[0];
            if (a[1] != b[1])
                return b[1] - a[1];
            return b[2] - a[2];
        });
        int n = A.length, res = 0;
        int[] dp = new int[n];//dp[i]: max height from cuboid[0] to cuboid[i]
        for (int i = 0; i < A.length; i++) {
            dp[i] = A[i][2];
            for (int j = 0; j < i; j++) {
                if (A[j][0] >= A[i][0] && A[j][1] >= A[i][1] && A[j][2] >= A[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + A[i][2]);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
