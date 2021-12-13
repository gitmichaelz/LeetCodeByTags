package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubstringWithConcatenationOfAllWords {
    /**
     * You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
     * <p>
     * You can return the answer in any order.
     * <p>
     * <p>
     * <p>
     * Example 1:
     * <p>
     * Input: s = "barfoothefoobarman", words = ["foo","bar"]
     * Output: [0,9]
     * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
     * The output order does not matter, returning [9,0] is fine too.
     * <p>
     * Example 2:
     * <p>
     * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
     * Output: []
     * <p>
     * Example 3:
     * <p>
     * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * Output: [6,9,12]
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.isEmpty() || words == null || words.length == 0) return res;
        Map<String, Integer> target = new HashMap<>();
        for (String word : words) {
            target.put(word, target.getOrDefault(word, 0) + 1);
        }
        int s_len = s.length(), words_count = words.length, word_len = words[0].length();
        int window = words_count * word_len;
        for (int i = 0; i < word_len; i++) {
            Map<String, Integer> found = new HashMap<>();//注意这里found 和 matchedCount的初始化位置，因为我们要update他们， 而不是每次从新的起点匹配时初始化，所以在for循环之外。
            int matchedCount = 0;
            for (int start = i; start <= s_len - window; start += word_len) {
                while (matchedCount < words_count) {//比较words_count次
                    String word = s.substring(start + matchedCount * word_len, start + (matchedCount + 1) * word_len);
                    if (!target.containsKey(word)) {
                        found.clear();//cur word not valid, all its leading words are not valid either, so we can clear the found map
                        start += matchedCount * word_len;//考虑到for 循环里的start += word_len, 这里我们只需把start放到这个invalid word的开头
                        matchedCount = 0;//dont forget to reset matchedCount to 0
                        break;
                    } else {
                        matchedCount++;
                        found.put(word, found.getOrDefault(word, 0) + 1);
                        if (found.get(word) > target.get(word)) {
                            int deletedCount = 0;
                            while (found.get(word) > target.get(word)) {//remove all leading words until found.get(sub) == target.get(sub)
                                String head = s.substring(start + deletedCount * word_len, start + (deletedCount + 1) * word_len);
                                found.put(head, found.get(head) - 1);
                                deletedCount++;
                            }
                            matchedCount -= deletedCount;
                            start += (deletedCount - 1) * word_len;//把start放到最后一个delete的开头
                            break;//break while and move start to new position for next round comparation, and matchedCount also get updated
                        }
                    }
                }
                if (matchedCount == words_count) {//对于已经匹配好的，我们只需要cut掉第一个单词，然后匹配尾部后面的一个单词即可。此时for循环里start会move forward一个单词的距离。即新的起点
                    res.add(start);
                    String head = s.substring(start, start + word_len);
                    found.put(head, found.get(head) - 1);
                    matchedCount--;
                }
            }
        }
        return res;
    }
//在一个string window内()
//     public List<Integer> findSubstring(String s, String[] words){
//         List<Integer> res = new ArrayList<>();
//         if(s == null || words == null || s.isEmpty() || words.length == 0) return res;
//         Map<String, Integer> target = new HashMap<>();
//         for(String word : words){
//             target.put(word, target.getOrDefault(word, 0) + 1);
//         }
//         int s_len = s.length(), arr_len = words.length, w_len = words[0].length();
//         int window = arr_len * w_len;
//         for(int i = 0; i < w_len; i++) {
//             int start = i;
//             while(start <= s_len - window){
//                 String sub = s.substring(start, start + window);
//                 Map<String, Integer> found = new HashMap<>();
//                 int j = arr_len;
//                 while(j > 0){
//                     String word = sub.substring((j - 1) * w_len, j * w_len);
//                     if(!target.containsKey(word)) break;
//                     found.put(word, found.getOrDefault(word, 0) + 1);
//                     if(found.get(word) > target.get(word)) break;
//                     j--;
//                 }
//                 if(j == 0) res.add(start);
//                 start += Math.max(j, 1) * w_len;
//             }
//         }
//         return res;
//     }
}
