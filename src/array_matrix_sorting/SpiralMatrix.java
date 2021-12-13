package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int row = matrix.length;
        int col = row == 0? 0: matrix[0].length;
        int x = 0, y = 0;//the element idx when we do spiral traversal
        while(row > 0 && col > 0){
            if(row == 1) {//注意下循环结束点，若行或列数为1，最后一层可能为一行或一列，特殊处理下。
                for(int i = 0; i < col; i++) res.add(matrix[x][y++]);
                return res;
            }
            if(col == 1) {
                for(int i = 0; i < row; i++) res.add(matrix[x++][y]);
                return res;
            }
            for(int i = 0; i < col - 1; i++) res.add(matrix[x][y++]);
            for(int i = 0; i < row - 1; i++) res.add(matrix[x++][y]);
            for(int i = 0; i < col - 1; i++) res.add(matrix[x][y--]);
            for(int i = 0; i < row - 1; i++) res.add(matrix[x--][y]);

            x++;//x++是因为上一个步骤结束后，x--了一次，所以这里需要x++
            y++;
            row -= 2;
            col -= 2;
        }
        return res;
    }
}
