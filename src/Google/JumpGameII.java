package Google;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGameII {
    //the main idea is based on greedy, let's say the range of cur jump is [curBegin, curEnd], curFarthest is the farthest
    //point that all points within [curBegin, curEnd] can reach, (and of course curFarthest will be counted as next jump).
    //Once the cur point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the
    //above steps.
    //implicit BFS.
    //when i == curEnd, means we visited all the items on the current level, incrementing jumps++ is like incrementing the level you are on.
    //curEnd = curFarthest is like getting the queue size(level size) for the next level you are traversing
    public int jump(int[] nums) {
        int jumps = 0;
        int curFarthest = 0;
        int curEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, nums[i] + i);
            if (i == curEnd) {
                jumps++;
                curEnd = curFarthest;
            }
            if (curEnd >= nums.length - 1) {
                break;
            }
        }
        return jumps;
    }

    //面试要求不是跳到最后一个index是跳出array，输出从最小的次数变成了一个list，记录每次跳了几步，其他都一样，也是要求minimum jump
    public List<Integer> jump2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int curFarthest = 0;
        int curEnd = 0;
        int preEnd = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, nums[i] + i);
            if (i == curEnd) {
                if (curEnd != 0) {//exclude the initial position
                    res.add(curEnd - preEnd);
                }
                preEnd = curEnd;
                curEnd = curFarthest;
            }
            if (curEnd > nums.length - 1) {
                res.add(nums.length - preEnd);
                break;
            }
        }
        return res;
    }
}
