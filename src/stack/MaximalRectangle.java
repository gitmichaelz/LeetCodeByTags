package stack;
/**
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 *
 * Example 1:
 *
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above
 */

import java.util.Deque;
import java.util.LinkedList;

public class MaximalRectangle {
    //这个题如果对每一行的heights更新完毕，则可以转换为从立方图中求最大矩形面积 https://leetcode.com/problems/largest-rectangle-in-histogram/submissions/
    public int maximalRectangle(char[][] matrix){
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, updateMaxArea(heights));
        }
        return maxArea;
    }

    private int updateMaxArea(int[] heights){
        Deque<Integer> stack = new LinkedList<>();
        int max = 0;
        int len = heights.length;
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                max = Math.max(max, heights[stack.pop()] * (i - (stack.isEmpty()? 0 : stack.peek()  + 1)));
            }
            stack.push(i);
        }

        while(!stack.isEmpty()){
            max = Math.max(max, heights[stack.pop()] * (len - (stack.isEmpty()? 0: stack.peek() + 1)));
        }
        return max;
    }
    //以下这个用数组代替stack的方法也可以参考，更快点
    /*
    private int updateArea(int[] heights) {
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
    }
*/



    //https://leetcode.com/articles/maximal-rectangle/#
//     public int maximalRectangle(char[][] matrix) {
//         if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
//         int m = matrix.length;
//         int n = matrix[0].length;
//         //at each row, for each point(i, j), we can construct a max rectangle by finding the proper leftBound[j], rightBound[j] and height[j]
//         int[] leftBound = new int[n];
//         int[] rightBound = new int[n];
//         int[] height = new int[n];
//         int max = 0;
//         Arrays.fill(rightBound, n - 1);
//         for(int i = 0; i < m; i++) {
//             int curLeft = 0, curRight = n - 1;
//             for(int j = 0; j < n; j++) {
//                 if(matrix[i][j] == '1') {
//                     height[j]++;
//                 } else {
//                     height[j] = 0;
//                 }
//             }

//             for(int j = 0; j < n; j++) {
//                 if(matrix[i][j] == '1') {
//                     leftBound[j] = Math.max(leftBound[j], curLeft);
//                 } else {
//                     curLeft = j + 1;
//                     leftBound[j] = 0;
//                 }
//             }

//             for(int j = n - 1; j >= 0; j--) {
//                 if(matrix[i][j] == '1') {
//                     rightBound[j] = Math.min(rightBound[j], curRight);
//                 } else {
//                     curRight = j - 1;
//                     rightBound[j] = n - 1;
//                 }
//             }
//             for(int j = 0; j < n; j++) {
//                 max = Math.max(max, (rightBound[j] - leftBound[j] + 1) * height[j]);
//             }
//         }
//         return max;
//     }
}
