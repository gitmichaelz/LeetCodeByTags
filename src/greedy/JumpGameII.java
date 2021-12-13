package greedy;

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
 *
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 104
 *     0 <= nums[i] <= 1000
 */
public class JumpGameII {
    //The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd], curFarthest is the farthest point that all points in [curBegin, curEnd] can reach,(and of course curFarthest will be counted as next jump). Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps
    //implicit BFS.
    //when i == curEnd, means we visited all the items on the current level, incrementing jumps++ is like incrementing the level you are on.
    //curEnd = curFarthest is like getting the queue size(level size) for the next level you are traversing
    public int jump(int[] nums) {
        int jumps = 0;
        int curFarthest = 0;
        int curEnd = 0;
        for(int i = 0; i < nums.length - 1; i++){
            curFarthest = Math.max(curFarthest, nums[i] + i);
            if(i == curEnd){
                jumps++;
                curEnd = curFarthest;
            }
            if (curEnd >= nums.length - 1) {//if curEnd can reach to the last element, we can just break the loop
                break;
            }
        }
        return jumps;
    }
}
