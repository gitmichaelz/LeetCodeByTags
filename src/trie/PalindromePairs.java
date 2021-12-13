package trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 */
public class PalindromePairs {
    //naive solution: try every pair of words and check if is palindrome pairs. which takes O(N^2 * K)， N is number of words, K is average length of words
    //a more efficient way is to use a trie.
    //这个题坑比较多。所以需要对trie 的节点做一番修改
    class TrieNode{
        TrieNode[] children;
        int isWord;//坑。这里一定要用word在原数组中的index来表示， 因为我们是比较两个不同的word,比如'a', 他的reverse和本身一样，当我们hit a leaf(并不是真的leaf,也许下面还有，只是到当前节点是一个word)需要check
        //是不是这个word本身，如果是，要排除
        List<Integer> wordsWithPalindromeSuffix;//比如我们有【"oooba", "xyxba", "ab"】, 则第2个字符串"ab"在trie里搜索到节点b时就停了，我们需要判断该节点后面的部分"ooo" "xyx"是否是回文，如果时，把他们所在
        //字符串的index加上，这里在b节点处，我们有两个子字符串是回文，所在字符串的index分别是0， 1，所以我们要把[2, 0][2, 1]加入结果集
        TrieNode(){
            children = new TrieNode[26];
            isWord = -1;
            wordsWithPalindromeSuffix = new ArrayList<>();
        }
    }
    TrieNode root;
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> palindromePairs(String[] words) {
        if(words == null || words.length < 2) return res;
        root = new TrieNode();
        for(int i = 0; i < words.length; i++){
            insertTrieNode(words[i], i);
        }
        for(int i = 0; i < words.length; i++){
            search(words[i], i);
        }
        return res;
    }
    //注意需要word的index信息
    private void insertTrieNode(String word, int i){
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        for(int j = arr.length - 1; j >= 0; j--){//从后往前扫，逆序建trie
            char c = arr[j];
            if(cur.children[c - 'a'] == null){
                cur.children[c - 'a'] = new TrieNode();
            }
            if(isPalindrome(word, 0, j)){
                cur.wordsWithPalindromeSuffix.add(i);
            }
            cur = cur.children[c - 'a'];
        }
        cur.wordsWithPalindromeSuffix.add(i);//坑. 一定要在最后一个节点加上这个信息。(其意义是，一个字符串后面的可以接空串，这个空串也被认为是一个回文串)
        cur.isWord = i;//isword用该字符串在数组中的index来表示，因为后面搜索的时候要排除自己和自己的比较。'a' 和reverse 'a'一样， 需要skip掉
    }
    /*
    How could AB be palindrome?
    If B ends with x, then A must starts with x. If the second character of B is y, then the second last character of A is y...
    That is,
      Case 1. A must be prefix of reversed B, and the rest of reversed B should be palindrome. For example,
        (B:oooabc - cbaooo,    A:cba       AB:cba|oooabc)
      Case 2. Or, reversed B must be prefix of A, and the rest of A should be palindrome. For example,
        (B:abc - cba           A:cbaooo,   AB:cbaooo|abc)

    Each word in words can be B. We put all reversed words in a trie.
    Each word in words can be A. So we search A in trie,
    In this way,
      Case 1. if we found A in trie, and the branch under the end node is a palindrome, we found it!
      Case 2. if we reach a leaf of trie, and the rest of A is palindrome, we found it!

      For Case 1., we modify TrieNode data structure by adding belowPalindromeWordIds - list of word indices such that nodes below can construct a palindrome.
      For Case 2., we create a method isPalindrome(str, start, end) .

    Please take care of corner cases of empty string. Both ("", self-palindrome) and (self-palindrome, "") are still palindrome.
     */
    private void search(String word, int i){
        TrieNode cur = root;
        char[] arr = word.toCharArray();
        for(int j = 0; j < arr.length; j++){
            char c = arr[j];
            if(cur.isWord >= 0 && cur.isWord != i && isPalindrome(word, j, word.length() - 1)){//case 2
                res.add(Arrays.asList(i, cur.isWord));
            }
            cur = cur.children[c - 'a'];
            if(cur == null) return;//坑。check cur node whether null or not before going to next layer
        }
        for(int j : cur.wordsWithPalindromeSuffix){
            if(i == j) continue;//坑。skip掉'a' 和 reverse 'a' 相等这种情况
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j){
        while(i < j){
            if(word.charAt(i++) != word.charAt(j--)) return false;
        }
        return true;
    }
}
