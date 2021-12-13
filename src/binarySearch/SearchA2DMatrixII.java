package binarySearch;

/**
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
 *
 *     Integers in each row are sorted in ascending from left to right.
 *     Integers in each column are sorted in ascending from top to bottom.
 *
 * Example 1:
 *
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 */
public class SearchA2DMatrixII {
    //start with top right, at each point, we either go down or go left, or return true if find
    //we can imagine a BST, if we stand on the top right, left child is less than root, and right child is greater than root
    //so that we go through the matrix like how we traverse the BST
    //time complexity: O(m + n)
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        for(int m = 0, n = matrix[0].length - 1; m < matrix.length && n >= 0; ) {
            if(matrix[m][n] == target) {
                return true;
            } else if (matrix[m][n] < target) {
                m++;
            } else {
                n--;
            }
        }
        return false;
    }
}
