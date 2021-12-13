package array_matrix_sorting;

/**
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,4,2,2]
 * Output: 2
 *
 * Example 2:
 *
 * Input: nums = [3,1,3,4,2]
 * Output: 3
 */
public class FindDuplicateNumber {
    //如果我们构造一个序列，nums[0], nums[nums[0]], nums[nums[nums[0]]]。。。。这些序列中的数字都是nums数组中的数字。最终会有一个cycle在里面，为什么?因为每个数在[1, n]中，并且总共有n + 1个数字，所以里面一定有duplicates, 即序列中肯定会有相同的点。所以这题就类似于链表找环ii.
    //https://leetcode.com/articles/find-the-duplicate-number/#
    //龟兔赛跑找环，类似于LinkedList CycleII
    /*Intuition

    If we interpret nums such that for each pair of index i and value vi, the "next" value vj is at index vi,
    we can reduce this problem to cycle detection. See the solution to Linked List Cycle II for more details.
    Algorithm
    First off, we can easily show that the constraints of the problem imply that a cycle must exist. Because each number in nums is between 1 and n, it will
    necessarily point to an index that exists. Therefore, the list can be traversed infinitely, which implies that there is a cycle. Additionally, because 0
    cannot appear as a value in nums, nums[0] cannot be part of the cycle. Therefore, traversing the array in this manner from nums[0] is equivalent to traversing
    a cyclic linked list. Given this, the problem can be solved just like Linked List Cycle II.
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length < 2)
            return -1;
        int slow = 0, fast = 0;
        while (true) {//一定有环，所以直接while true，不用判断nums[fast] < nums.length && nums[nums[fast]] < nums.length，因为根据题意一定成立。
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
