package string;
/**
 * he DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.
 *
 *     For example, "ACGAATTCCG" is a DNA sequence.
 *
 * When studying DNA, it is useful to identify repeated sequences within the DNA.
 *
 * Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 *
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> repeated = new HashSet<>();
        List<String> res = new ArrayList<>();
        int[] map = new int[26];
        //map['A' - 'A'] = 0;
        map['C' - 'A'] = 1;
        map['G' - 'A'] = 2;
        map['T' - 'A'] = 3;

        for(int i = 0; i <= s.length() - 10; i++) {
            int bitmap = 0;
            for(int j = i; j < i + 10; j++) {
                bitmap = (bitmap << 2) | map[s.charAt(j) - 'A'];//为什么左移2位，因为每个字符最大表示3，二进制中2个bit有效位
            }
            if(!seen.add(bitmap) && repeated.add(bitmap)) {
                res.add(s.substring(i, i + 10));
            }
        }
        return res;
    }

    //time: 10*n, which is O(N);
    //  public List<String> findRepeatedDnaSequences(String s) {
    //     Set seen = new HashSet(), repeated = new HashSet();
    //     for (int i = 0; i <= s.length() - 10; i++) {
    //         String ten = s.substring(i, i + 10);
    //         if (!seen.add(ten))
    //             repeated.add(ten);
    //     }
    //     return new ArrayList<>(repeated);
    // }
}
