package unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].
 *
 * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
 *
 * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
 *
 * Two sentences are similar if:
 *
 *     They have the same length (i.e., the same number of words)
 *     sentence1[i] and sentence2[i] are similar.
 *
 * Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"], similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
 * Output: true
 */
public class SentenceSimilarityII {
    Map<String, String> father;
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) return false;
        father = new HashMap<>();
        for(List<String> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }
        for(int i = 0; i < words1.length; i++) {
            String root1 = find(words1[i]);
            String root2 = find(words2[i]);
            if(!root1.equals(root2)) {
                return false;
            }
        }
        return true;
    }

    private void union(String s1, String s2) {
        if(!father.containsKey(s1)) {
            father.put(s1, s1);
        }
        if(!father.containsKey(s2)) {
            father.put(s2, s2);
        }
        String root1 = find(s1);
        String root2 = find(s2);
        if(root1 != root2) {
            father.put(root1, root2);
        }
    }

    private String find(String s1) {
        if(!father.containsKey(s1)){//这个一定要加，因为我们只是对pairs里的词做了union, father里不一定包含words1 和words2里面的词
            return s1;
        }
        while(!s1.equals(father.get(s1))) {
            father.put(s1, father.get(father.get(s1)));
            s1 = father.get(s1);
        }
        return s1;
    }
}
