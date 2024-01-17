package array_matrix_sorting;

/**
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 *
 *
 * Example 1:
 *
 * Input: mat = [[1,2,3],
 *              [4,5,6],
 *              [7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 */
public class DiagonalTraversal {
    public int[] findDiagonalOrder(int[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] res = new int[m * n];
        for(int i = 0, x = 0, y = 0; i < res.length; i++){//x,y are coordinates in matrix, indicating where we are at matrix
            res[i] = matrix[x][y];
            if((x + y) % 2 == 0){//moving up and right, x--, y++
                if(y == n - 1){//once hit row 0, y++, 坑！！！一定要先判断y == n - 1,否则如果先判断x == 0,而这时正好处于右上角，则y++会越界
                    x++;
                } else if(x == 0){//once hit col n - 1, x++;
                    y++;
                } else {
                    x--;
                    y++;
                }
            } else {//moving down and left, x++, y--
                if(x == m - 1){//坑，必须先判断x,原因同上。
                    y++;
                } else if(y == 0){
                    x++;
                } else {
                    x++;
                    y--;
                }
            }
        }
        return res;
    }
}
