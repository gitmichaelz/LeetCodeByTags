package array_matrix_sorting;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 *     Each of the digits 1-9 must occur exactly once in each row.
 *     Each of the digits 1-9 must occur exactly once in each column.
 *     Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *
 * The '.' character indicates empty cells.
 *
 * Constraints:
 *
 *     board.length == 9
 *     board[i].length == 9
 *     board[i][j] is a digit or '.'.
 *     It is guaranteed that the input board has only one solution.
 */
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        //经典的backtracking！！！
        //use 3 2d boolean array to denote whether the number is used in each row/col/small box
        //for each unfilled position, we check rowVisited[rowIdx][num] colVisited[colIdx][num] and boxVisited[boxIdx][num] visited
        //if yes, then continue; else, we make them as visited(set true), and board[i][j] = num; then try next position, we recursively calculate
        //the next position until all position can be filled correctly, if such case, we return true; else, we pruning/restore the previous state and
        //try next available num from 1 - 9
        //note, for position, here we use 0 - 80 which are total 81 celss. if position number is larger than 80 (0 - based), we return true
        boolean[][] rowCheck = new boolean[9][9];
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] boxCheck = new boolean[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int charIdx = board[i][j] - '0' - 1;
                int boxIdx = (i / 3) * 3 + j / 3;
                rowCheck[i][charIdx] = colCheck[j][charIdx] = boxCheck[boxIdx][charIdx] = true;
            }
        }
        solve(0, board, rowCheck, colCheck, boxCheck);
    }

    private boolean solve(int pos, char[][] board, boolean[][] rowCheck, boolean[][] colCheck, boolean[][] boxCheck) {
        if(pos > 80) return true;//we've already filled all positions
        int i = pos / 9;
        int j = pos % 9;
        int boxIdx = (i / 3) * 3 + j / 3;
        if(board[i][j] != '.') {//if cur cell no need to be filled, skip to next position
            return solve(pos + 1, board, rowCheck, colCheck, boxCheck);
        }

        for(int num = 0; num < 9; num++) {
            if(rowCheck[i][num] || colCheck[j][num] || boxCheck[boxIdx][num]) continue;//already visited,
            rowCheck[i][num] = colCheck[j][num] = boxCheck[boxIdx][num] = true;
            board[i][j] = (char)(num + '1');
            if(solve(pos + 1,  board, rowCheck, colCheck, boxCheck)) {//recursively solve next position until 80
                return true;
            }
            rowCheck[i][num] = colCheck[j][num] = boxCheck[boxIdx][num] = false;
            board[i][j] = '.';//restore to previous state and try next available num from 0 - 9
        }
        return false;
    }
}
