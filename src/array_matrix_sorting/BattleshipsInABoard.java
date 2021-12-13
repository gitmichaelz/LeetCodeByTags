package array_matrix_sorting;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 *
 *
 * Example 1:
 *
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 */
public class BattleshipsInABoard {
    //since the input is always valid, so we can go over all cells, we can count only those that are the "first" cell of the battleship. First cell will be defined
    // as the most top-left cell. We can check for first cells by only counting cells that do not have an 'X' to the left and do not have an 'X' above them.
    public int countBattleships(char[][] board) {
        int count = 0;
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.') continue;
                if(i > 0 && board[i - 1][j] == 'X') continue;
                if(j > 0 && board[i][j - 1] == 'X') continue;
                count++;
            }
        }
        return count;
    }
}
