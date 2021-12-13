package array_matrix_sorting;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
 *
 * Return true if there is a 132 pattern in nums, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: false
 * Explanation: There is no 132 pattern in the sequence.
 */
public class _132Pattern {
    //use a stack to maintain a 32 pattern by scanning backwards. we can make sure secondhigh always comes after first High
    //while(!stack.isEmpty() && nums[i] > stack.peek()), we let secondHigh = stack.poll()
    //if we get any nums[i] < secondHigh, return true;
    //each element at most visited twice, O(N)_
    public boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        int secondHigh = Integer.MIN_VALUE;
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < secondHigh) return true;
            while(!stack.isEmpty() && nums[i] > stack.peek()) {
                secondHigh = stack.pop();
            }
            stack.push(nums[i]);//so basically, stack is monotonous increasing
        }
        return false;
    }
}
