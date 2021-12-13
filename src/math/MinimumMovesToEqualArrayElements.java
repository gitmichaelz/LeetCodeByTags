package math;

/**
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 *
 * In one move, you can increment n - 1 elements of the array by 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 * Explanation: Only three moves are needed (remember each move increments two elements):
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */
public class MinimumMovesToEqualArrayElements {
    //n-1个数加1，选择相当于一个数减1. adding 1 to n - 1 elements is the same as subtracting 1 from one element,
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for(int num : nums) {
            min = Math.min(min, num);
        }
        int res = 0;
        for(int num : nums) {
            res += (num - min);
        }
        return res;
    }

    //https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/183193/topic
    // public int minMoves(int[] nums) {
    //     int min = Integer.MAX_VALUE, n = nums.length;
    //     int sum = 0;
    //     for(int num : nums) {
    //         sum += num;
    //         min = Math.min(min, num);
    //     }
    //     return sum - min * n;
    // }
}
