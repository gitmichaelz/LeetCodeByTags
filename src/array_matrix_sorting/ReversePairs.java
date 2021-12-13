package array_matrix_sorting;

/**
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 */
public class ReversePairs {
    //same ieda as count of range sum
//一点总结，只要牵扯到角标顺序问题(因为merge sort left and right are all sorted, and index in left sort always less than that in right)， i < j, 并求某种可能性时，可以考虑merge sort思路, 并要考虑数据类型
//例如nums[i] > 2 * nums[j]，最好先将他们都转成long 类型，否则nums[j] == Integer.MAX_VALUE 就会得出错误的结果
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        return countWithMergeSort(nums, 0, nums.length - 1);
    }

    public int countWithMergeSort(int[] nums, int start, int end) {
        if(start == end) return 0;
        int mid = (start + end)/2;
        int count = countWithMergeSort(nums, start, mid) + countWithMergeSort(nums, mid + 1, end);
        int[] tmp = new int[end - start + 1];
        int right = mid + 1, index = 0;//right is for right part of num, index is for tmp, to finish the mergesort
        int p = mid + 1;//to mark the index that satisfies nums[i] > 2 * nums[p]
        for(int i = start; i <= mid; i++){
            while(p <= end && (long)nums[i] > (long)nums[p] * 2) p++;//important!!! dont forget (long)
            while(right <= end && nums[i] > nums[right]) tmp[index++] = nums[right++];

            tmp[index++] = nums[i];
            count += p - (mid + 1);//dont mess up the index!!!
        }
        while(right <= end) tmp[index++] = nums[right++];//copy the remaining in right part to tmp
        for(int i = start, j = 0; i <= end; ) {//finish merge sort for nums[];
            nums[i++] = tmp[j++];
        }

        return count;
    }
}
