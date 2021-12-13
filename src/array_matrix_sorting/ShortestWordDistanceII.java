package array_matrix_sorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.
 *
 * Implement the WordDistance class:
 *
 *     WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
 *     int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict.
 *
 * Example 1:
 *
 * Input
 * ["WordDistance", "shortest", "shortest"]
 * [[["practice", "makes", "perfect", "coding", "makes"]], ["coding", "practice"], ["makes", "coding"]]
 * Output
 * [null, 3, 1]
 */
public class ShortestWordDistanceII {
    class WordDistance {
        Map<String, List<Integer>> map = new HashMap<>();
        public WordDistance(String[] words) {
            map = new HashMap<>();
            int idx = 0;
            for(String s : words){
                if(!map.containsKey(s))
                    map.put(s, new ArrayList<>());
                map.get(s).add(idx++);
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int min =Integer.MAX_VALUE;
            for(int i = 0, j = 0, len1 = list1.size(), len2 = list2.size(); i < len1 && j < len2; ){
                int index1 = list1.get(i);
                int index2 = list2.get(j);
                min = Math.min(min, Math.abs(index1 - index2));
                if(index1 < index2){
                    i++;
                } else {
                    j++;
                }
            }
            return min;
        }

        //以下方法是Shortest Word Distance I的思路，会TLE
        // String[] words;
        // public WordDistance(String[] words) {
        //     this.words = words;
        // }
        // public int shortest(String word1, String word2) {
        //     int index = -1, minDistance = Integer.MAX_VALUE;
        //     for (int i = 0; i < words.length; i++) {
        //         if (words[i].equals(word1) || words[i].equals(word2)) {
        //             if (index != -1 && !words[index].equals(words[i])) {
        //                 minDistance = Math.min(minDistance, i - index);
        //             }
        //             index = i;
        //         }
        //     }
        //     return minDistance;
        // }
    }
}
