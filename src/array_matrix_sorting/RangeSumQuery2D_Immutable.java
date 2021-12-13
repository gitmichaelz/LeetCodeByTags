package array_matrix_sorting;

/**
 * Given a 2D matrix matrix, handle multiple queries of the following type:
 *
 *     Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 *
 *     NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 *     int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
 * Output
 * [null, 8, 11, 12]
 */
public class RangeSumQuery2D_Immutable {
    class NumMatrix {
        int[][] sum;
        public NumMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0) return;
            int m = matrix.length, n = matrix[0].length;
            sum = new int[m + 1][n + 1];
            for(int i = 1; i <= m; i++) {
                for(int j = 1; j <= n; j++) {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i -  1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }
        //p1 p2 p3 P4 是矩形的四个点 矩形内的和为 p4 - p2 - p3 + p1
        public int sumRegion(int row1, int col1, int row2, int col2) {
            return sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
        }
    }
}
