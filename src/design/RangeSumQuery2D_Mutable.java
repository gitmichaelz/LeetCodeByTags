package design;

/**
 * Given a 2D matrix matrix, handle multiple queries of the following types:
 *
 *     Update the value of a cell in matrix.
 *     Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Implement the NumMatrix class:
 *
 *     NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
 *     void update(int row, int col, int val) Updates the value of matrix[row][col] to be val.
 *     int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["NumMatrix", "sumRegion", "update", "sumRegion"]
 * [[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [3, 2, 2], [2, 1, 4, 3]]
 * Output
 * [null, 8, null, 10]
 *
 * Explanation
 * NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
 * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e. sum of the left red rectangle)
 * numMatrix.update(3, 2, 2);       // matrix changes from left image to right image
 * numMatrix.sumRegion(2, 1, 4, 3); // return 10 (i.e. sum of the right red rectangle)
 *
 * Constraints:
 *
 *     m == matrix.length
 *     n == matrix[i].length
 *     1 <= m, n <= 200
 *     -105 <= matrix[i][j] <= 105
 *     0 <= row < m
 *     0 <= col < n
 *     -105 <= val <= 105
 *     0 <= row1 <= row2 < m
 *     0 <= col1 <= col2 < n
 *     At most 104 calls will be made to sumRegion and update.
 */
public class RangeSumQuery2D_Mutable {
    class NumMatrix {//time: initalize bit, mn * logm* logn,  update(): logm * logn, rangeSum() logm * log n
        int[][] matrix;
        int[][] tree;
        public NumMatrix(int[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;//一定要判断，否则过不了test case
            this.matrix = matrix;
            tree = new int[matrix.length + 1][matrix[0].length + 1];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix[0].length; j++){
                    updateTree(i + 1, j + 1, matrix[i][j]);
                }
            }
        }
        private void updateTree(int row, int col, int delta){
            for(int i = row; i < tree.length; i += lowbit(i)){
                for(int j = col; j < tree[0].length; j+= lowbit(j)){
                    tree[i][j] += delta;
                }
            }
        }

        private int lowbit(int i){
            return i & (-i);
        }
        public void update(int row, int col, int val) {
            updateTree(row + 1, col + 1, val - matrix[row][col]);//注意：delta = val - matrix[row][col]
            matrix[row][col] = val;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return query(row2 + 1, col2 + 1) - query(row1, col2 + 1) - query(row2 + 1, col1) + query(row1, col1);
        }

        public int query(int row, int col){
            int sum = 0;
            for(int i = row; i > 0; i -= lowbit(i)){
                for(int j = col; j > 0; j -= lowbit(j)){
                    sum += tree[i][j];
                }
            }
            return sum;
        }

    }
}
