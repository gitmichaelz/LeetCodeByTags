package divideAndConquer;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Example 3:
 *
 * Input: nums1 = [0,0], nums2 = [0,0]
 * Output: 0.00000
 */
public class MedianOfTwoSortedArrays {
    //things to consider: 1> don't forget (double) 2> left, right is the cut position, there are totally m + 1 cuts in first array and n + 1 in second array
    //3> j = n - (n - m)/2 - i;//WHY? since i = (left + right)/2 = (0 + m)/2; and i is rounded to lower bound, when we compute j,
    //we need to rounded it to upper bound, so j = n - n/2; => i + j = n - (n - m)/2 => j = n - (n - m)/2 - i;
    //4> len(leftA + leftB) >= len(rightA + rightB), so first compute the maxLeft, if odd, we can just return, if even, then continue to
    // compute minRight, and return (double)(maxLeft + minRight)/2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if(m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int left = 0, right = m;
        while(left <= right){
            int i = left + (right - left)/2;//向左逼近
            int j = n - (n - m)/2 - i;//向右逼近。
            if(i > 0 && nums1[i - 1] > nums2[j]){//
                right = i - 1;
            } else if(i < m && nums2[j - 1] > nums1[i]){
                left = i + 1;
            } else {//found correct i
                int leftMax = i == 0 ? nums2[j - 1] : (j == 0 ? nums1[i - 1] : Math.max(nums1[i - 1], nums2[j - 1]));
                if((m + n) % 2 == 1){//odd, the whole left part num is greater than right part
                    return leftMax;
                }

                int rightMin = i == m ? nums2[j] : (j == n ? nums1[i] : Math.min(nums1[i], nums2[j]));
                return 0.5 * (leftMax + rightMin);//注意这里的处理，如果直接(leftMax + right)/2则会直接返回一个近似整数
            }
        }
        return 0;
    }
}
