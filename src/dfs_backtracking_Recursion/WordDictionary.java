package dfs_backtracking_Recursion;


/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 *     WordDictionary() Initializes the object.
 *     void addWord(word) Adds word to the data structure, it can be matched later.
 *     bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 */
public class WordDictionary {
    //该题非常经典，考察trie + dfs
    //为什么这里要用dfs,因为要搜索的字符串含有'.'， 要对所有可能情况搜索，如果字符串没有'.'那么就直接用普通的trie search方法
    class TrieNode{
        TrieNode[] children;
        boolean isLeaf;//the end of word
        TrieNode(){
            children = new TrieNode[26];
            isLeaf = false;
        }
    }

    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for(char c : word.toCharArray()){
            int pos = c - 'a';
            if(cur.children[pos] == null){
                cur.children[pos] = new TrieNode();
            }
            cur = cur.children[pos];
        }
        cur.isLeaf = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return dfs(root, word.toCharArray(), 0);
    }

    private boolean dfs(TrieNode cur, char[] word, int idx){
        if(idx == word.length){
            return cur.isLeaf;
        }
        if(word[idx] != '.') {
            return cur.children[word[idx] - 'a'] != null && dfs(cur.children[word[idx] - 'a'], word, idx + 1);
        }
        //else word[idx] == '.', we will try every possible path from cur.children[]
        for(int i = 0; i < cur.children.length; i++){
            if(cur.children[i] != null && dfs(cur.children[i], word, idx + 1)) {//坑。注意这里是idx + 1, 跟i没关系
                return true;
            }
        }
        return false;
    }
}
