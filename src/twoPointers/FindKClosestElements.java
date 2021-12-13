package twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 *     |a - x| < |b - x|, or
 *     |a - x| == |b - x| and a < b
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 */
public class FindKClosestElements {
    // we use binary search to minimize the range we're searching, and use the arr[mid] and arr[mid+k] to decide to move left or right
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //  using binary search

        int left = 0;
        // 注意此时的e不是下标，而是总的长度
        int right = arr.length-k;

        while (left < right){
            int mid = left + (right-left)/2;
            // 不取等且right = mid，这里是取lower bound，符合题意tie取numeber小的
            if (Math.abs(arr[mid]-x) > Math.abs(arr[mid+k]-x))
                left = mid+1;
            else
                right = mid;
        }

        List<Integer> res = new ArrayList();

        for (int i=left;i<left+k;i++){
            res.add(arr[i]);
        }

        return res;
    }
}
