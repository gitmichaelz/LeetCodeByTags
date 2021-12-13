package math;

import java.util.Random;

/**
 * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 *
 *     Solution(int[] nums) Initializes the object with the integer array nums.
 *     int[] reset() Resets the array to its original configuration and returns it.
 *     int[] shuffle() Returns a random shuffling of the array.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 */
public class ShuffleAnArray {
    int[] nums;
    Random random;
    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            int idx = random.nextInt(i + 1);
            res[i] = res[idx];
            res[idx] = nums[i];
        }
        return res;
    }
}
