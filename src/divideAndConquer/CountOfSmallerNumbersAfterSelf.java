package divideAndConquer;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class CountOfSmallerNumbersAfterSelf {
    class Pair{//This class defines the element in an array and its index
        int index;
        int val;
        Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
    //merge sort idea. we can count the number of smaller elements to the right of nums[i] during merge sort.
    //for example left[2, 5], right[1, 6]  left and right are already sorted separately, when we merge these two arrays,
    //we can easily know how many elements in the right array jump in front of certain element in left, for example,
    //when we compare 5 and 6, which are left[1] and right[1], we already there are j(j is pointer in right array) steps, that is ,
    //1 step in front of 5, so we can track how many elements are smaller than left[i] during merge sort.
    //and also, we need to remember the original index for each element so we dont mess up their orders.
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Pair[] arr = new Pair[nums.length];
        for(int i = 0; i < nums.length; i++) {
            arr[i] = new Pair(i, nums[i]);
        }
        int[] count = new int[nums.length];
        mergeSort(arr, count, 0, arr.length - 1);
        for(int i : count) {
            res.add(i);
        }
        return res;
    }

    public Pair[] mergeSort(Pair[] arr, int[] count, int start, int end) {
        if(start == end) return new Pair[] {arr[start]};//dont forget []
        int mid = (start + end)/2;
        Pair[] left = mergeSort(arr, count, start, mid);
        Pair[] right = mergeSort(arr, count, mid + 1, end);
        Pair[] rst = new Pair[end - start + 1];
        for(int i = 0, j = 0; i < left.length || j < right.length; ){
            if(j == right.length || i < left.length && left[i].val <= right[j].val) {//dont forget its <= in case there are duplicates like [2, 5] and [1, 5]
                rst[i + j] = left[i];
                count[left[i].index] += j;//trace the original element's index and count the number of smaller elements to the right of it
                i++;
            } else {//i == left.length || left[i].val > right[i].val
                rst[i + j] = right[j];
                j++;
            }
        }
        return rst;
    }



    //bruteforce n^2
    // public List<Integer> countSmaller(int[] nums) {
    //     List<Integer> res = new ArrayList<>();
    //     if(nums == null || nums.length == 0) return res;
    //     for(int i = 0; i < nums.length -1; i++) {
    //         int count = 0;
    //         for(int j = i + 1; j < nums.length; j++) {
    //             if(nums[i] > nums[j]) {
    //                 count++;
    //             }
    //         }
    //         res.add(count);
    //     }
    //     res.add(0);
    //     return res;
    // }
}
