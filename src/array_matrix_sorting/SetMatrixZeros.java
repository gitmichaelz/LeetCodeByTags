package array_matrix_sorting;

/**
 * Given an m x n integer matrix matrix, if an element is 0, set its entire row and column to 0's, and return the matrix.
 *
 * You must do it in place.
 *
 * Example 1:
 *
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 */
public class SetMatrixZeros {
    //use first cell of each row/col to store the state, let the cell[0][0] to store the row0's state, let's use a variable col0 to store the col0's state
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int col0 = -1;//mark the col0 if needs to be set to 0
        for(int i = 0; i < m; i++) {
            if(matrix[i][0] == 0) col0 = 0;
            for(int j = 1; j < n; j++) {//note we are starting from col1 since we already check col0 above line
                if(matrix[i][j] == 0){
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        // bottom up, 一定是bottom up! 因为第一列第一行是标志行/列。从上到下的方式会把他们覆盖
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 1; j--){//col 到j = 1
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
            if(col0 == 0){
                matrix[i][0] = 0;
            }
        }
    }
}
