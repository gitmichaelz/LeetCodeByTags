package math;

import java.util.Arrays;
import java.util.Random;

/**
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 *
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 *
 *     For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 */
public class RandomPickWithWeight {
    class Solution {
        Random r = new Random();
        int[] accSum;

        public Solution(int[] w) {
            if (w.length == 0) accSum = new int[0];

            accSum = new int[w.length];
            accSum[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                accSum[i] = accSum[i - 1] + w[i];
            }
        }

        public int pickIndex() {
            int rand = r.nextInt(accSum[accSum.length - 1]) + 1;
            int rIdx = Arrays.binarySearch(accSum, rand);
            return rIdx < 0 ? -rIdx - 1 : rIdx;
        }
    }
}
