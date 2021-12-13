package stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.
 *
 * Example 1:
 *
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 */
public class LargestRectangleHistogram {
    //单调栈：monotonous stack
    //https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/217539/O(n)-stack-c%2B%2B-solution-12ms
    //idea: for a specific bar i, the largest rectangle with heights[i] must be all NEIGHBORING bars which are taller than heights[i], and the left boundary and right boundary of such rectangle should be bars left,right so that heights[left] < heights[i] and heights[right] < heights[i]; the maxArea with heights[i] should be heights[i] * (right - left - 1)
//think one step futher, the ith bar is the right boundary of all pre neghboring bars whhich are taller than height[i]
//so how to find the left bounday, we can use a stack to store the index of incresing sorted elements, before we push an element index, we check if heights[stack.peek()] > heights[i], if yes, we pop out the bar index and compute the maxArea that formed by the popped bar, since i is the first bar which is shorter than popped bar on its right side. the top of the stack is first bar which is shorter than popped bar on its left side.
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;
        Deque<Integer> stack = new LinkedList<>();//存递增元素的index,why?因为我们要计算宽度width.
        int len = heights.length;
        int maxArea = 0;
        for(int i = 0; i < len; i++){
            //这里为避免不必要的坑，先判断是不是可以push 如果不能，我们先把pop的都pop了，再做后面的push
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - (stack.isEmpty()? 0 : stack.peek() + 1)));//注意！！！：stack为空意味着刚pop出去的为当前最小的bar, 则长度为i
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            maxArea = Math.max(maxArea, heights[stack.pop()] * (len - (stack.isEmpty()? 0 : stack.peek() + 1)));//注意这里i已经变成了len，栈的最后一个元素就是全局最小的，这时只需要len * heights[stack.pop()]即可
        }
        return maxArea;
    }
    //以下方法也要掌握，用数组模拟stack，更快
    /*
    public int largestRectangleArea(int[] heights) {
        int res = -1;
        int[] stack = new int[heights.length];
        int index = -1;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? -1 : heights[i]);
            while (index != -1 && h < heights[stack[index]]) {
                int curH = heights[stack[index--]];
                res = Math.max(res, curH * (index == -1 ? i : i - stack[index] - 1));
            }
            stack[++index] = i;
        }
        return res;
    }*/
}
