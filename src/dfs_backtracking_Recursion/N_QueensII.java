package dfs_backtracking_Recursion;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 */
public class N_QueensII {
    int count = 0;
    public int totalNQueens(int n){
        int[] colForRow = new int[n];
        solve(colForRow, 0, n);
        return count;
    }

    private void solve(int[] colForRow, int row, int n){
        if(row == n){
            count++;
            return;
        }
        for(int i = 0; i < n; i++){
            colForRow[row] = i;
            if(check(colForRow, row)){
                solve(colForRow, row + 1, n);
            }
        }
    }

    private boolean check(int[] colForRow, int row){
        for(int i = 0; i < row; i++){
            if(colForRow[row] == colForRow[i] || Math.abs(colForRow[row] - colForRow[i]) == row - i) return false;
        }
        return true;
    }
}
