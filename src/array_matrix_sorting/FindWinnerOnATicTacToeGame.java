package array_matrix_sorting;

/**
 * Tic-tac-toe is played by two players A and B on a 3 x 3 grid. The rules of Tic-Tac-Toe are:
 *
 *     Players take turns placing characters into empty squares ' '.
 *     The first player A always places 'X' characters, while the second player B always places 'O' characters.
 *     'X' and 'O' characters are always placed into empty squares, never on filled ones.
 *     The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 *     The game also ends if all squares are non-empty.
 *     No more moves can be played if the game is over.
 *
 * Given a 2D integer array moves where moves[i] = [rowi, coli] indicates that the ith move will be played on grid[rowi][coli]. return the winner of the game if it exists (A or B). In case the game ends in a draw return "Draw". If there are still movements to play return "Pending".
 *
 * You can assume that moves is valid (i.e., it follows the rules of Tic-Tac-Toe), the grid is initially empty, and A will play first.
 *
 *
 *
 * Example 1:
 *
 * Input: moves = [[0,0],[2,0],[1,1],[2,1],[2,2]]
 * Output: "A"
 * Explanation: A wins, they always play first.
 */
public class FindWinnerOnATicTacToeGame {
    /*
id: 0 for A, 1 for B;
row[id][r]: the current number of characters that player id places on row r;
col[id][c]: the current number of characters that player id places on column c;
d1[id]: the current number of characters that player id places on main diagonal;
d2[id]: the current number of characters that player id places on skew diagonal.
*/
    public String tictactoe(int[][] moves) {
        int[][] row = new int[2][3], col = new int[2][3];
        int[] d1 = new int[2], d2 = new int[2];
        for (int i = 0; i < moves.length; ++i) {
            int r = moves[i][0], c = moves[i][1], id = i % 2;
            if (++row[id][r] == 3 || ++col[id][c] == 3 || r == c && ++d1[id] == 3 || r + c == 2 && ++d2[id] == 3)
                return id == 0 ? "A" : "B";
        }
        return moves.length == 9 ? "Draw" : "Pending";
    }
}
