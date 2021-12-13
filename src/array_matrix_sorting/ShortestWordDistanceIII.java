package array_matrix_sorting;

/**
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.
 *
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 *
 * Example 1:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 */
public class ShortestWordDistanceIII {
    //注意对比 1 ： https://leetcode.com/problems/shortest-word-distance/
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index = -1;
        boolean sameWords = word1.equals(word2);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1) || words[i].equals(word2)){
                if(index != -1 && (sameWords || !words[index].equals(words[i]))){
                    min = Math.min(min, i - index);
                }
                index = i;
            }
        }
        return min;
    }
}
