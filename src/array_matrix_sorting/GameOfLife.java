package array_matrix_sorting;

/**
 * According to Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 *     Any live cell with fewer than two live neighbors dies as if caused by under-population.
 *     Any live cell with two or three live neighbors lives on to the next generation.
 *     Any live cell with more than three live neighbors dies, as if by over-population.
 *     Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 *
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously. Given the current state of the m x n grid board, return the next state.
 */
public class GameOfLife {
    //从这题的四个条件得知：
    // 1 -> 0 if live neightbor count < 2;
    // 1 -> 1 if live neighbor count in [2, 3];
    // 1 -> 0 if live neighbor count > 3;
    // 0 -> 1 if live neighbor count = 3
    //同时，我们改完cell的值后还不能影响后续判断，即不影响判断其他cell的live neighbor的数量，怎么办？
    //这里用到的技巧是，如果当前cell = 1, 它的live neighbor双良是[2, 3]， 我们让cell = 3(11), 如果当前cell = 0, 他的live neighbor数量 = 3
    //那让cell = 2(11), 最后,我们让所有的cell的值右移一位，即 1 >> 1 = 0, 2 >> 1 = 0, 3 >> 1 = 1, 不会改变cell最终的应该所在的状态。
    //同时，在求neighbor数量时，我们用 count += board[i][j] & 1， 这样， 那些被改为3(11)的cell 不会影响判断， 而改为2(10)的也不会。因为11 & 1 = 1, 10 & 1 = 0;
    public void gameOfLife(int[][] board){
        int m = board.length, n = board[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int neighbors = getNeighborsCount(board, i, j);
                if(board[i][j] == 1){
                    if(neighbors == 2 || neighbors == 3){
                        board[i][j] = 3;
                    }
                } else {
                    if(neighbors == 3){
                        board[i][j] = 2;
                    }
                }
            }
        }

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                board[i][j] >>= 1;
            }
        }
    }

    private int getNeighborsCount(int[][] board, int row, int col){
        int count = 0;
        for(int i = row - 1; i <= row + 1; i++){
            for(int j = col - 1; j <= col + 1; j++){
                if(i >= 0 && i < board.length && j >= 0 && j < board[0].length){
                    count += board[i][j] & 1;
                }
            }
        }
        count -= board[row][col] & 1;//上面把board[row][col]自己也加上了，需要减去自身这个cell
        return count;
    }
}
