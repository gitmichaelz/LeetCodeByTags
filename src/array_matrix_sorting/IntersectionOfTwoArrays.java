package array_matrix_sorting;

import java.util.Arrays;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 */
public class IntersectionOfTwoArrays {


    //facebook interview O(n) time, O(1) space
    //arrays are already sorted
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {return intersection(nums2, nums1);}
        Arrays.sort(nums1); Arrays.sort(nums2);

        int k = 0;
        for(int i = 0, j = 0; i < nums1.length && j < nums2.length; ){
            if(nums1[i] > nums2[j]) {j++;}
            else if(nums2[j] > nums1[i]) {i++;}
            else {
                int index = k - 1;
                if(k == 0 || nums1[index] != nums1[i] || nums1[index] != nums2[j]){
                    nums1[k] = nums1[i]; k++; j++; i++;
                } else {
                    while(j < nums2.length && nums2[j] == nums1[index]) {j++;}
                    while(i < nums1.length && nums1[i] == nums1[index]) {i++;}
                }
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }

    /*
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for(int i : nums1) {
            set.add(i);
        }
        for(int i : nums2) {
            if(set.contains(i)) {
                intersect.add(i);
            }
        }
        int[] res = new int[intersect.size()];
        int i = 0;
        for(int num : intersect){
            res[i++] = num;
        }
        return res;
    }
    */

}
