package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of unique strings words, return all the word squares you can build from words. The same word from words can be used multiple times. You can return the answer in any order.
 *
 * A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
 *
 *     For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["area","lead","wall","lady","ball"]
 * Output: [["ball","area","lead","lady"],["wall","area","lead","lady"]]
 * Explanation:
 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
 */
public class WordSquares {
    class Node{
        Node[] nodes;
        String word;
        Node(){
            this.nodes = new Node[26];
            this.word = null;
        }
    }
    void add(Node root, String word){
        Node node = root;
        for (char c : word.toCharArray() ) {
            int idx = c-'a';
            if (node.nodes[idx] == null) node.nodes[idx] = new Node();
            node = node.nodes[idx];
        }
        node.word = word;
    }
    void helper(int row, int col, int len, Node[] rows, List<List<String>> ret) {
        if ( (col == row) && (row == len) ) { // last char
            List<String> res = new ArrayList<String>();
            for (int i=0; i<len; i++) {
                res.add(new String(rows[i].word) );
            }
            ret.add( res );
        } else { // from left to right and then go down to the next row
            if ( col < len  ) { // left to right first
                Node pre_row = rows[row];
                Node pre_col = rows[col];
                for (int i=0; i<26; i++) { // find all the possible next char
                    if ( (rows[row].nodes[i] != null) && (rows[col].nodes[i] != null) ) {
                        rows[row] = rows[row].nodes[i];
                        if (col != row) rows[col] = rows[col].nodes[i];
                        helper(row, col+1, len, rows, ret);
                        rows[row] = pre_row;
                        if (col != row) rows[col] = pre_col;
                    }
                }
            } else { // reach the end of column, go to the next row
                helper(row+1, row+1, len, rows, ret);
            }
        }
    }
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ret = new ArrayList();
        if (words==null || words.length==0) return ret;
        Node root = new Node();
        int len = words[0].length();
        for (String word: words) add(root, word);
        Node[] rows = new Node[len];
        for (int i=0; i<len; i++) rows[i]=root;
        helper(0, 0, len, rows, ret);
        return ret;
    }
}
