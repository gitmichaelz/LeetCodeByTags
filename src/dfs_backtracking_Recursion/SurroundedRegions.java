package dfs_backtracking_Recursion;

/**
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 */
public class SurroundedRegions {
    //find all 'O's on 4 edges, put their position in an queue. becuase all of them cannot be captured. and any 'O' connected to them can not be captured too
    //similar to bfs, we find all the adjacent 'O' s of edge'O' s and replace them with '$', then we scan the board replace all 'O's to X, all '$' to 'O's
   // int row, col;
//     public void solve(char[][] board) {
//         Deque<Integer> queue = new LinkedList<>();
//         row = board.length;
//         col = row > 0? board[0].length : 0;
//         for(int i = 0; i < row; i++) {
//             enqueue(queue, board, i, 0);
//             enqueue(queue, board, i, col - 1);
//         }
//         for(int j = 1; j < col - 1; j++) {
//             enqueue(queue, board, 0, j);
//             enqueue(queue, board, row - 1, j);
//         }
//         while(!queue.isEmpty()) {
//             int p = queue.poll();
//             int x = p/col;
//             int y = p%col;
//             enqueue(queue, board, x + 1, y);
//             enqueue(queue, board, x - 1, y);
//             enqueue(queue, board, x, y + 1);
//             enqueue(queue, board, x, y - 1);
//         }
//         for(int i = 0; i < row; i++) {
//             for(int j = 0; j < col; j++) {
//                 if(board[i][j] == 'O') {
//                     board[i][j] = 'X';
//                 } else if(board[i][j] == '$') {
//                     board[i][j] = 'O';
//                 }
//             }
//         }
//     }

//     private void enqueue(Deque<Integer> queue, char[][] board, int i, int j) {
//         if(i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 'O') {
//             queue.offer(i * col + j);//transfer 2d to 1d
//             board[i][j] = '$';
//         }
//     }


    //dfs
    //similar to above, process the 4 edges first
    int row, col;
    public void solve(char[][] board){
        if(board == null || board.length == 0 || board[0].length == 0) return;
        row = board.length;
        col = board[0].length;
        for(int i = 0; i < row; i++) {
            process(board, i, 0);
            process(board, i, col - 1);
        }
        for(int j = 1; j < col - 1; j++) {
            process(board, 0, j);
            process(board, row - 1, j);
        }
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '$') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void process(char[][] board, int i, int j) {
        if(i >= 0 && i < row && j >=0 & j < col && board[i][j] == 'O') {
            board[i][j] = '$';
            process(board, i + 1, j);
            process(board, i - 1, j);
            process(board, i, j + 1);
            process(board, i, j - 1);
        }
    }
}
