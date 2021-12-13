package array_matrix_sorting;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 */
public class MajorityElement {
    //moore vote, sweep from left index, use a counter to mark the currence of cur element, if next element is same,     increase counter, if not, decrease it
    //if counter == 0, then make the cur elemnt as cur, and counter = 1
    public int majorityElement(int[] nums) {
        int count = 1;
        int cur = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(cur == nums[i]) {
                count++;
            } else {
                count--;
                if(count == 0) {
                    cur = nums[i];
                    count = 1;
                }
            }
        }
        return cur;
    }



    //using hashmap
    // public int majorityElement(int[] nums) {
    //     Map<Integer, Integer> map = new HashMap<>();
    //     for (int i : nums){
    //         if (!map.containsKey(i)) {
    //             map.put(i, 1);
    //         } else {
    //             map.put(i, map.get(i) + 1);
    //         }
    //         if (map.get(i) > nums.length/2) {
    //             return i;
    //         }
    //     }
    //     return -1;
    // }
}
