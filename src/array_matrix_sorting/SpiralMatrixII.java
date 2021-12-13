package array_matrix_sorting;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 */
public class SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int row = n, col = n;
        int x = 0, y = 0, val = 1;
        while(row > 0 && col > 0){
            if(row == 1){
                for(int i = 0; i < col; i++) matrix[x][y++] = val++;
                return matrix;
            }
            if(col == 1){
                for(int i = 0; i < row; i++) matrix[x++][y] = val++;
                return matrix;
            }
            for(int i = 0; i < col - 1; i++) matrix[x][y++] = val++;;//at the end of loop, y = col-1
            for(int i = 0; i < row - 1; i++) matrix[x++][y] = val++;// at the end of loop, x = row-1;
            for(int i = col - 1; i > 0; i--) matrix[x][y--] = val++;//at the end of loop, y=0;
            for(int i = row - 1; i > 0; i--) matrix[x--][y] = val++;//at the end of loop, x=0;

            x++;
            y++;
            row -= 2;
            col -= 2;
        }
        return matrix;
    }
}
