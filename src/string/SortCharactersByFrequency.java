package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "tree"
 * Output: "eert"
 */
public class SortCharactersByFrequency {
    //use a map a count and according to the frequency to put it into the right bucket. Then we go through the bucket to get the most frequently character and append that to the final stringbuilder.
    public String frequencySort(String s) {
        StringBuilder sb = new StringBuilder();
        int[] count = new int[128];
        for(char c : s.toCharArray()){
            count[c]++;
        }
        List<Character>[] buckets = new List[s.length() + 1];
        for(int i = 0; i < count.length; i++){
            if(count[i] > 0){
                if(buckets[count[i]] == null) buckets[count[i]] = new ArrayList<>();
                buckets[count[i]].add((char)i);
            }
        }
        for(int i = buckets.length - 1; i >= 0; i--){
            if(buckets[i] != null){
                for(char c : buckets[i]){
                    for(int j = 0; j < i; j++){
                        sb.append(c);
                    }
                }
            }
        }
        return sb.toString();
    }
}
