package hashtable;

import java.util.*;

/**
 * Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.
 *
 * The words in paragraph are case-insensitive and the answer should be returned in lowercase.
 *
 *
 *
 * Example 1:
 *
 * Input: paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 * Output: "ball"
 */
public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        //replace all non word characters to space, 注意。“          ”会变为“ ”
        String[] words = paragraph.replaceAll("\\W+", " ").toLowerCase().split(" ");
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            if(set.contains(word)) continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int max = 0;
        String res = "";
        for(String key : map.keySet()) {
            if(map.get(key) > max) {
                res = key;
                max = map.get(key);
            }
        }
        return res;
    }
}
