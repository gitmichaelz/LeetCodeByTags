package twoPointers;

/**
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 *
 *
 * Example 1:
 *
 * nput: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of
 * water (blue section) the container can contain is 49.
 *
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Example 3:
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 * Example 4:
 *
 * Input: height = [1,2,1]
 * Output: 2
 *
 */
public class ContainerWithMostWater {
    /**
     * idea: two pointer, i,j at the two ends of array
     * maintain a max area
     * if height[i] > height[j], move j
     * else move i
     *
     * ime: O(N) space: O(1)
     */
    public int maxArea(int[] height) {
        int max = 0;
        for(int i = 0, j = height.length - 1; i < j; ){
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if(height[i] > height[j]) {
                int tmp = height[j];
                while(i < j && height[j] <= tmp){//一点小优化，如果height[j]比tmp还小，可以直接移动指针
                    j--;
                }
            } else {
                int tmp = height[i];
                while(i < j && height[i] <= tmp){
                    i++;
                }
            }
        }
        return max;
    }
}
