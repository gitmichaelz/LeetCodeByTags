package string;

import java.util.*;

/**
 * Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 */
public class ConcatenatedWords {
    //43ms, beat 95.37%
    //用trie差不多的思路，但是会稍微慢一些，因为要建trie
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        List<String> res = new ArrayList<>();
        for (String s : words) {
            if (isConcat(s, dict)) {
                res.add(s);
            }
        }
        return res;
    }
    private boolean isConcat(String s, Set<String> set) {
        for (int i = 1; i < s.length(); i++) {
            if (set.contains(s.substring(0, i)) && (set.contains(s.substring(i)) || isConcat(s.substring(i), set))) {
                return true;
            }
        }
        return false;
    }

//     class TrieNode{
//         TrieNode[] children;
//         boolean isEnd;
//         TrieNode(){
//             children = new TrieNode[26];
//         }
//     }
//     TrieNode root;
//     public List<String> findAllConcatenatedWordsInADict(String[] words){
//         List<String> res = new ArrayList<>();
//         root = new TrieNode();
//         for(String word : words){
//             insertTrieNode(word);
//         }
//         for(String word : words){
//             if(concatenated(word, 0, 0)) {
//                 res.add(word);
//             }
//         }
//         return res;
//     }

//     private boolean concatenated(String word, int index, int count){
//         TrieNode cur = root;
//         for(int i = index; i < word.length(); i++){
//             int pos = word.charAt(i) - 'a';
//             if(cur.children[pos] == null) return false;
//             if(cur.children[pos].isEnd) {
//                 if(i == word.length() - 1) return count >= 1;
//                 if(concatenated(word, i + 1, count + 1)) return true;
//             }
//             cur = cur.children[pos];
//         }
//         return false;
//     }

//     private void insertTrieNode(String word){
//         TrieNode cur = root;
//         for(char c : word.toCharArray()){
//             int pos = c - 'a';
//             if(cur.children[pos] == null){
//                 cur.children[pos] = new TrieNode();
//             }
//             cur = cur.children[pos];
//         }
//         cur.isEnd = true;
//     }
}
