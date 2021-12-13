package array_matrix_sorting;

/**
 * Given an integer array nums and two integers lower and upper, return the number of range sums that lie in [lower, upper] inclusive.
 *
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j inclusive, where i <= j.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,5,-1], lower = -2, upper = 2
 * Output: 3
 * Explanation: The three ranges are: [0,0], [2,2], and [0,2] and their respective sums are: -2, -1, 2.
 */
public class CountOfRangeSum {
    //divide and conquer, merge sort idea.
//still need preprocess to get sum[i], then we can merge sort sum[i]; say we already have a sorted left and right array, for each element in left, we scan right array and find the proper index(i, j) that right[i] - left[index] >= lower and right[i] - left[index] <= upper, right[j] - left[index] >= lower and right[j] - left[index] <= upper, then count += j - i + 1;
    //note after merge sort we return the count of range sum, not the sorted array
    public int countRangeSum(int[] nums, int lower, int upper) {
        if(nums == null || nums.length == 0) return 0;
        long[] sum = new long[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }

        return countWithMergeSort(sum, 0, sum.length - 1, lower, upper);
    }
    public int countWithMergeSort(long[] sum, int start, int end, int lower, int upper) {
        if(start == end) return 0;
        int mid = (start + end)/2;
        int count = countWithMergeSort(sum, start, mid, lower, upper) + countWithMergeSort(sum, mid + 1, end, lower, upper);
        long[] tmp = new long[end - start + 1];//use this tmp[] to finish merge sort for sum[]
        int low = mid + 1, high = mid + 1, right = mid + 1;

        int index = 0;//index for tmp[]
        for(int i = start; i <= mid; i++) {//for every sum[i] in left part, we need to find sum[j] - s[i] >= lower && <= upper
            while(low <= end && sum[low] - sum[i] < lower) low++; //low++ until we hit the first one such that sum[low] - sum[i] = lower;
            while(high <= end && sum[high] - sum[i] <= upper) high++; //high++ until we hit the first one such that sum[high] - sum[i] > upper, because we need to count high - low
            while(right <= end && sum[right] < sum[i]) {
                tmp[index++] = sum[right++];
            }

            count += high - low;
            tmp[index++] = sum[i];
        }
        while(right <= end) tmp[index++] = sum[right++];
        for(int i = start, j = 0; i <= end; i++, j++) {//dont mess up with array's index
            sum[i] = tmp[j];
        }

        return count;
    }
}
