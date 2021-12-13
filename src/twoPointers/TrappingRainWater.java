package twoPointers;
/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 *
 *
 * Constraints:
 *
 *     n == height.length
 *     1 <= n <= 2 * 104
 *     0 <= height[i] <= 105
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {
    //这两种方法都要掌握

    //keep tracking of the maximum height from both forward directions and backward directions. think about they are 2 walls
    //and we pick the smaller one from them, and for each position, we calculate how many rain water can trap based on the
    //height we pick
    public int trap_1(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int res = 0;
        while(left <= right){
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if(leftMax < rightMax){
                res += (leftMax - height[left]);
                left++;
            } else {
                res += (rightMax - height[right]);
                right--;
            }
        }
        return res;
    }

    //stack 来存储decreasing height 的index, 如果当前高度height[i]大于height[stack.peek()], 则找到右墙，然后需要找左墙，然后求以
    //当前底 curHeight = height[stack.pop()] 为底， 左右墙围起来的空间的容量。
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();//store the index of decreasing heights
        int res = 0;
        for(int i = 0; i < height.length; ) {
            if(stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {//height[i] is cur right wall
                int curHeight = height[stack.pop()];
                int leftWall = stack.isEmpty()? 0 : height[stack.peek()];
                res += leftWall == 0? 0 : (Math.min(leftWall, height[i]) - curHeight) * (i - stack.peek() - 1);
            }
        }
        return res;
    }
}
