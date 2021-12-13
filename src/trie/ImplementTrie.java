package trie;

/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 *     Trie() Initializes the trie object.
 *     void insert(String word) Inserts the string word into the trie.
 *     boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 *     boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 */
public class ImplementTrie {
    class Trie{
        class TrieNode{
            TrieNode[] children;
            boolean isEnd;
            TrieNode(){
                children = new TrieNode[26];
                isEnd = false;
            }
        }
        /** Initialize your data structure here. */
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode cur = root;
            for(char c : word.toCharArray()){
                int pos = c - 'a';
                if(cur.children[pos] == null){
                    cur.children[pos] = new TrieNode();
                }
                cur = cur.children[pos];
            }
            cur.isEnd = true;//dont forget isEnd = true
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode cur = root;
            for(char c : word.toCharArray()){
                int pos = c - 'a';
                if(cur.children[pos] == null) {
                    return false;
                }
                cur = cur.children[pos];
            }
            return cur.isEnd;//坑，要返回cur.isEnd, 而不是直接返回true，以为到这里word很可能是一个prefix
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for(char c : prefix.toCharArray()){
                int pos = c - 'a';
                if(cur.children[pos] == null) {
                    return false;
                }
                cur = cur.children[pos];
            }
            return true;
        }
    }
}
