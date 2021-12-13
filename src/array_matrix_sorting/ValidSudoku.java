package array_matrix_sorting;

/**
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 *     Each row must contain the digits 1-9 without repetition.
 *     Each column must contain the digits 1-9 without repetition.
 *     Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * Note:
 *
 *     A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 *     Only the filled cells need to be validated according to the mentioned rules.
 */
public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowCheck = new boolean[9][9];//row index and char index
        boolean[][] colCheck = new boolean[9][9];
        boolean[][] boxCheck = new boolean[9][9];// I * 3 + J => (i / 3) * 3 + j / 3;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.') continue;
                int charIdx = board[i][j] - '1';//because idx is 0 based, so we need 1 shift by -1
                int boxIdx = (i / 3) * 3 + j / 3;
                if(rowCheck[i][charIdx] || colCheck[j][charIdx] || boxCheck[boxIdx][charIdx]) return false;
                rowCheck[i][charIdx] = colCheck[j][charIdx] = boxCheck[boxIdx][charIdx] = true;
            }
        }
        return true;
    }
}
