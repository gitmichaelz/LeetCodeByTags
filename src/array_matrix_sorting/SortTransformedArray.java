package array_matrix_sorting;

/**
 * Given a sorted integer array nums and three integers a, b and c, apply a quadratic function of the form f(x) = ax2 + bx + c to each element nums[i] in the array, and return the array in a sorted order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
 * Output: [3,9,15,33]
 */
public class SortTransformedArray {
    //The idea is simple:
    //For a parabola,
    //if a >= 0, the min value is at its vertex. So our our sorting should goes from two end points towards the vertex, high to low.
    //if a < 0, the max value is at its vertex. So our sort goes the opposite way.
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        int start = 0, end = nums.length - 1;
        int i = a >= 0? end : start;// if a >=0, we get the numbers from high to low
        while(start <= end){
            int startNum = getNum(nums[start], a, b, c);
            int endNum = getNum(nums[end], a, b, c);
            if(a >= 0){
                if(startNum >= endNum){
                    res[i--] = startNum;
                    start++;
                } else {
                    res[i--] = endNum;
                    end--;
                }
            } else {
                if(startNum <= endNum){
                    res[i++] = startNum;
                    start++;
                } else {
                    res[i++] = endNum;
                    end--;
                }
            }
        }
        return res;
    }
    private int getNum(int num, int a, int b, int c){
        return a * num * num + b * num + c;
    }
}
