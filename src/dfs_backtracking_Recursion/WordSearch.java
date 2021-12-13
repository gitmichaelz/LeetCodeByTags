package dfs_backtracking_Recursion;

/**
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or
 * vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example 1:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 */
public class WordSearch {
    //time: (m * n * 4^s), s is the len of word
    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++){
                if(search(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    //another way to mark visited cell can be done by board[i][j] ^= 256; after recursion, board[i][j] ^= 256;
    private boolean search(char[][] board, String word, int i, int j, int idx){
        if(idx == word.length()) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(idx)) return false;
        board[i][j] = '$';//to mark cur cell as visited by set cell value = '$'
        boolean res = search(board, word, i + 1, j, idx + 1) || search(board, word, i - 1, j, idx + 1)
                || search(board, word, i, j + 1, idx + 1) || search(board, word, i, j - 1, idx + 1);
        board[i][j] = word.charAt(idx);//restore cell
        return res;
    }
}
