package string;

import java.util.Deque;
import java.util.LinkedList;

public class LongestWordInDictionary {
    //1. Sort the words alphabetically, therefore shorter words always comes before longer words;
    //2. Along the sorted list, populate the words that can be built;
    //3. Any prefix of a word must comes before that word.

    // public String longestWord(String[] words){
    //     Arrays.sort(words);
    //     Set<String> set = new HashSet<>();
    //     String res = "";
    //     for(String word : words){
    //         if(word.length() == 1 || set.contains(word.substring(0, word.length() - 1))) {
    //             set.add(word);
    //             res = word.length() > res.length()? word: res;
    //         }
    //     }
    //     return res;
    // }


    public  String longestWord(String[] words){
        Trie trie = new Trie();
        for(String word : words){
            trie.insert(word);
        }
        return trie.findTheLongestWordDFS(trie.root, "");
        // return trie.findTheLongestWord();
    }
    class TrieNode{
        TrieNode[] children;
        boolean isWord;
        String word;
        TrieNode(){
            children = new TrieNode[26];
        }
    }
    class Trie{
        TrieNode root;
        Trie(){
            root = new TrieNode();
            root.isWord = true;//根据这题的要求，需要对root进行特殊处理，勇于DFS
        }

        public void insert(String word){
            TrieNode node = root;
            for(char w : word.toCharArray()){
                int pos = w - 'a';
                if(node.children[pos] == null){
                    node.children[pos] = new TrieNode();
                }
                node = node.children[pos];
            }
            node.isWord = true;
            node.word = word;
        }

        public String findTheLongestWord(){
            TrieNode node = root;
            Deque<TrieNode> queue = new LinkedList<>();
            queue.offer(root);
            String res = "";
            while(!queue.isEmpty()){
                TrieNode cur = queue.poll();
                for(int i = 25; i >= 0; i--){
                    TrieNode child = cur.children[i];
                    if(child != null && child.isWord){
                        res = child.word;
                        queue.offer(child);
                    }
                }
            }
            return res;
        }

        //DFS
        //s: parent's word
        public String findTheLongestWordDFS(TrieNode node, String s){
            if(node == null || !node.isWord) return s;//we need
            String res = "";
            s = node.word == null? "" : node.word;//只有一开始root.word == null,我们只是在处理这个special case.
            for(TrieNode child : node.children){
                String word = findTheLongestWordDFS(child, s);
                if(res.length() < word.length()){//只有在res的len不符合时才更新，这样能保证符合的res一定是smallest lexicographical order.
                    res = word;
                }
            }
            return res;
        }
    }
}
