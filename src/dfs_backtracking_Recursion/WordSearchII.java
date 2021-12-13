package dfs_backtracking_Recursion;

/**
 * Given an m x n board of characters and a list of strings words, return all words on the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Example 1:
 *
 * Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * Output: ["eat","oath"]
 */

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {
    //利用dictionary里的words来建trie. 这里建trie有一点跟以前不一样的是，我们把isEnd换成Sring word, 这样我们可以直接在搜到单词时将起加入结果集合。
    //然后以矩阵的每一个位置为起始点dfs搜索，并标记已经搜索过的路径，不要忘记最后restore矩阵。注意：当搜索到一个单词时，我们并不是马上返回，而是继续从当前位置搜索，因为如果我们搜索完abc后，字典里还有abcde,这样从当前位置继续搜索的话，才能得到abcde
    class TrieNode {
        TrieNode[] children;
        String word;//use this word to denote if cur TrieNode is a leaf which can form a word
        TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                int p = c - 'a';
                if(node.children[p] == null) {
                    node.children[p] = new TrieNode();
                }
                node = node.children[p];
            }
            node.word = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if(board == null || board.length == 0 || board[0].length == 0) return res;
        TrieNode root = buildTrie(words);
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                backtrack(board, root, i, j, res);
            }
        }
        return res;
    }

    private void backtrack(char[][] board, TrieNode root, int i, int j, List<String> res) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#' || root.children[board[i][j] - 'a'] == null) return;
        char c = board[i][j];
        root = root.children[c - 'a'];
        if(root.word != null) {
            res.add(root.word);
            root.word = null;//its a one time search, once we find one word, we add it to the result, de-duplicates
        }
        //we wont return/stop here because once we find a word "abc", there might be "abcde", so we continue searching next adjacent position
        board[i][j] = '#';//mark board[i][j] is visited
        backtrack(board, root, i - 1, j, res);
        backtrack(board, root, i + 1, j, res);
        backtrack(board, root, i, j + 1, res);
        backtrack(board, root, i, j - 1, res);
        board[i][j] = c;//restore board[i][j] after this level recursion
    }
}
