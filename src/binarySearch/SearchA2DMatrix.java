package binarySearch;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 *     Integers in each row are sorted from left to right.
 *     The first integer of each row is greater than the last integer of the previous row.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 */
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int low = 0, hi = m * n - 1;
        while(low <= hi){
            int mid = low + (hi - low)/2;
            int x = mid / n;
            int y = mid % n;
            if(matrix[x][y] == target) {
                return true;
            } else if(matrix[x][y] < target) {
                low = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
