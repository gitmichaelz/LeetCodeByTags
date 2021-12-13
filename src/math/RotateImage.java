package math;
/**
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 *
 *DO NOT allocate another 2D matrix and do the rotation.
 */
public class RotateImage {
    //该题记住一点。对任意x, y,他的顺时针90度变为 (y, n - x - 1), 而(x, y)的位置则由(n - y - 1, x)顺时针90度变成的
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i = 0; i < n/2; i++) {
            for(int j = i; j < n - 1 - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];//可以先找出中线对称点，再找对角线对称点, 对任意x, y,他的顺时针90度变为 (n - x - 1, y)
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = tmp;
            }
        }
    }
}
