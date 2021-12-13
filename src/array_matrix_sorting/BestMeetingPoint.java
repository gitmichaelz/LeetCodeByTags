package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n binary grid grid where each 1 marks the home of one friend, return the minimal total travel distance.
 *
 * The total travel distance is the sum of the distances between the houses of the friends and the meeting point.
 *
 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0,0,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 * Output: 6
 * Explanation: Given three friends living at (0,0), (0,4), and (2,2).
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 is minimal.
 * So return 6.
 */
public class BestMeetingPoint {
    //根据https://leetcode.com/problems/best-meeting-point/solution/        Finding the best meeting point in a 2D grid seems difficult. Let us take a step back and solve the 1D case which is much simpler. Notice that the Manhattan distance is the sum of two independent variables. Therefore, once we solve the 1D case, we can solve the 2D case as two independent 1D problems.
    //先考虑1d的情况，在一条直线上，假设有两个点，那他俩之间的直线的任一点都可以是最短路径。如果有三个点，那三个点的最短距离也是最远两点之间的距离，相遇的点就睡中间那个点。这样推理开来，如果有多个点，best meeting point就是最中间的那个或者两个，最短的距离和就是最远两点的距离+次远两点距离 +.... 根据这个表述，best meeting point就是这些点的median。(中位数，注意不是平均数)。
    //然后考虑2d的情况，同理，我们先按照cols排序(但不用对row排序，因为我们扫描矩阵的时候是按照row 0 -> 1进行的)，然后按照1d的情况分别把cols的最短路径和加上rows的最短路径和加起来即可。
//     public int minTotalDistance(int[][] grid) {
//         if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
//         List<Integer> rows = new ArrayList<>();
//         List<Integer> cols = new ArrayList<>();
//         for(int i = 0; i < grid.length; i++) {
//             for(int j = 0; j < grid[0].length; j++) {
//                 if(grid[i][j] == 1) {
//                     rows.add(i);
//                     cols.add(j);
//                 }
//             }
//         }
//         Collections.sort(cols);//we dont need to sort rows, because we are adding elements in order from row 0 to row grid.length - 1;

//         int res = 0;
//         for(int i = 0, j = rows.size() - 1; i < j; i++, j--) {
//             res += rows.get(j) - rows.get(i) + cols.get(j) - cols.get(i);
//         }

    //         return res;
//     }
    //以下是直接可以按照col的排序来添加，这样就不用额外排序了。更快一点。
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        List<Integer> I = new ArrayList<Integer>();
        List<Integer> J = new ArrayList<Integer>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    I.add(i);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i ++) {
                if (grid[i][j] == 1) {
                    J.add(j);
                }
            }
        }
        return minTotalDistance1d(I) + minTotalDistance1d(J);
    }

    public int minTotalDistance1d(List<Integer> grid) {
        int i = 0, j = grid.size() - 1, sum = 0;
        while (i < j) {
            sum += grid.get(j--) - grid.get(i++);
        }
        return sum;
    }
}
