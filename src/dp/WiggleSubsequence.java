package dp;

/**
 * A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
 *
 *     For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
 *     In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive, and the second is not because its last difference is zero.
 *
 * A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.
 *
 * Given an integer array nums, return the length of the longest wiggle subsequence of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,7,4,9,2,5]
 * Output: 6
 * Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
 */
public class WiggleSubsequence {
    //we use two variable, up and down, up means the max len of wiggle subsequence so far if nums[i] > nums[j], and up = pre down + 1
    //down means the max len of wiggle subsequence so far if nums[i] < nums[j], and down = pre up + 1
    public int wiggleMaxLength(int[] nums) {
        if(nums.length == 0) return 0;
        int down = 1, up = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if(nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(down, up);
    }
}
