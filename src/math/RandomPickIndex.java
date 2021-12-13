package math;

import java.util.Random;

/**
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 *
 *     Solution(int[] nums) Initializes the object with the array nums.
 *     int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 */
public class RandomPickIndex {
    //https://www.cnblogs.com/strugglion/p/6424874.html水塘抽样
    //Reservoir sampling is usually used to randomly pick up k elements in an array S with very big size N or in a data stream S with the same probability. The idea of the algorithm is:
    //
    //(1) Get the first k elements from S and put them into an array result[]
    //(2) for j >= k && j < N:
    //generate a random number r [0, j]
    //if r < k: result[r] = S[j]
    //
    //For this question, we can take k = 1, and we only care about the number whose value equals target. First, keep an array of size 1 (just a variable V works).Then assign the first index of the target to it. We can loop every element in input, and at the meantime, use a count to record how many target we've seen now (same role as j). Then generate a random number [0, count). If the number == 0, we can replace the V's value with the new index. After visiting every element in S, the V's value is what we want.
    //
    //You may wonder, why Reservoir Sampling can guarantee the equal possibility. Let's take a look at the original problem and solution. When we see element S[j], the possibility of choosing it into the reservoir is k/j, and the possibility of its being replaced by ONE elements(t, t > j) later is (k/t)/k = 1/t. Therefore, the probability of S[j] not being replaced by ANY OTHER elements later in S is:
    //(1 - 1/(j + 1)) * (1 - 1/(j + 2)) * ......* (1 - 1/N) = j/N
    //Therefore, the posibiility of S[j] is in the Reservoir is
    //k/j * j/N = k/N
    int[] nums;
    Random random;
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int pick(int target) {
        int res = -1;
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == target){
                int x = random.nextInt(++count);
                if(x == 0){
                    res = i;
                }
            }
        }
        return res;
    }
}
