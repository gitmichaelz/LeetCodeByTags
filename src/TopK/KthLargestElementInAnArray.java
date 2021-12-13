package TopK;

import java.util.Random;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 *
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 */
public class KthLargestElementInAnArray {
//top k problem, find the kth smallest/largest element in an unsorted array
    //using quick selection algorithm, find a pivot, and find all elements that larger than or equal to pivot
    //and we know pivot is the mth largest element in the array, compare m and k, if m = k, return pivot,
    //if m > k, means the kth largest element on the right side, otherwise, it's on the left side
    //Kth smallest element is the same idea, we need to compare the ones smaller on the left
    //while Kth largest, we need to compare the ones larerger than pivot on the right

    //runtime: n+n/2+n/4+... = 2n

    //总结一下第K大第K小的问题
    //如果是第K大， 我们需要对右边排序， 从右往左扫，然后pivot的选取是找出随机数index,然后和最左边的swap
    //如果是第K小则反之。即对左边排序，从左往右扫，随机数index和最右边的swap
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }
    //pivot的选取非常非常非常重要！！！！把速度从20%提高到99.62%！！！！！
    //之前是只选择第一个或最后一个，这里我们生成一个介于（left, right）之间的一个随机数，注意该随机数是一个角标，然后swap该角
    //标所代表的数与第一个角标所代表的数，
    private int findKthLargest(int[] nums, int left, int right, int k) {
        int random = new Random().nextInt(right - left + 1) + left;// use random index for pivot
        swap(nums, random, left);//为什么要swap,因为我们要从左右扫，为了不妨碍，所以先把pivot拿到边上。
        int pivot = nums[left];
        int start = left + 1; //exclude pivot
        int end = right;
        while(start <= end) { //dont forget "=", we need to compare every element includinng start and end
            if(nums[end] >= pivot) {
                end--;
            } else {
                swap(nums, start, end);
                start++;
            }
        }
        //after above step, all elements after end is larger than or equal to pivot, 注意，不包括end,
        //因为跳出循环的时候，要么end--,要么start++,无论哪种个情况，最后end指向的数肯定比pivot小，
        swap(nums, end, left); //put pivot to the final place so that all element larger than it is on its right
        int len = right - end + 1;//len is the number of those greater or equal to pivot
        if (len == k) {
            return nums[end];
        } else if (len > k) {
            return findKthLargest(nums, end + 1, right, k);
        } else {
            return findKthLargest(nums, left, end - 1, k - len);
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
