package array_matrix_sorting;

/**
 * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,1]
 * Output: 1
 * Explanation:
 * The first distinct maximum is 3.
 * The second distinct maximum is 2.
 * The third distinct maximum is 1.
 */
public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE, secMax = Long.MIN_VALUE, thirdMax = Long.MIN_VALUE;
        for(int num : nums){
            if(num == max || num == secMax || num == thirdMax) continue;
            if(num > max){
                thirdMax = secMax;
                secMax = max;
                max = num;
            } else if(num > secMax){
                thirdMax = secMax;
                secMax = num;
            } else if(num > thirdMax){
                thirdMax = num;
            }
        }
        return thirdMax == Long.MIN_VALUE? (int)max : (int)thirdMax;
    }
    // public int thirdMax(int[] nums) {
    //     PriorityQueue<Integer> pq = new PriorityQueue<>();
    //     for (int i : nums) {
    //         if (!pq.contains(i)) {
    //             pq.offer(i);
    //             if (pq.size() > 3) {
    //                 pq.poll();
    //             }
    //         }
    //     }
    //     if (pq.size() < 3) {
    //         while (pq.size() > 1) {
    //             pq.poll();
    //         }
    //     }
    //     return pq.peek();
    // }
}
