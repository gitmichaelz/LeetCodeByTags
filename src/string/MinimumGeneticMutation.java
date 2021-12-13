package string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string start to a gene string end where one mutation is defined as one single character changed in the gene string.
 *
 *     For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 *
 * There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings start and end and the gene bank bank, return the minimum number of mutations needed to mutate from start to end. If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 *
 *
 *
 * Example 1:
 *
 * Input: start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
 * Output: 1
 */
public class MinimumGeneticMutation {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> validGene = new HashSet<>();
        for (String gene: bank){
            validGene.add(gene);
        }
        if (!validGene.contains(end)) return -1;
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        int step = 0;
        char[] arr = new char[]{'A', 'C', 'G', 'T'};
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size -- > 0){
                String curGene = queue.poll();
                char[] curArr = curGene.toCharArray();
                for (int i = 0; i < 8; i ++){
                    char oldCh = curArr[i];
                    for (char cc: arr){
                        if (cc == oldCh) continue;
                        curArr[i] = cc;
                        String newGene = String.valueOf(curArr);
                        if (newGene.equals(end)) return step + 1;
                        if (!validGene.contains(newGene)) continue;
                        queue.offer(newGene);
                        validGene.remove(newGene);
                    }
                    curArr[i] = oldCh;
                }
            }
            step++;
        }
        return -1;
    }
}
