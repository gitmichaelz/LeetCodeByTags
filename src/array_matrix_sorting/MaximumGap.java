package array_matrix_sorting;

/**
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 * Example 1:
 *
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 */
public class MaximumGap {
    //bucket Sort
    //所有元素的平均的gap = (max - min) / (len - 1), 那么我们需要多少个gap呢？
    //考虑到gap idx = (n - min)/gap, 如果n = min, 那么idx = 0, 那么n = max， 那么idx = (max - min)/gap
    //则总的size必须包含最大值所在的bucket,则共需要max idx + 1个
    //我们用两组bucket, bucketMin/bucketMax来分别存放对应bucket的最大值和最小值，这样扫一遍就能得出最大相邻gap


    //average gap = (max - min)/(n - 1), and maximum gap must >= average gap,
    //so if we make the bucket size smaller than average gap, the max gap must lies  in adjacent buckets, not in the same bucket
    //so how many buckets do we need? (max - min), it is total gap, everage gap is = (max - min)/(n - 1), which is rounded to lower value, so we need at most (max - min)/gap + 1, which is rouneded to upper value. Or, we can think this way, in the line 21, we need to calculate the idx, for the max element, we do idx = (max - min)/gap, in order to put the max element, the total buckets we need should be (max idx) + 1

    //see case 0, 9, 18, 27, 36, 45, 54, 63, 72, 81
    public int maximumGap(int[] nums) {
        if(nums == null || nums.length < 2) return 0;
        int min = nums[0], max = nums[0];
        for(int n : nums) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }
        int gap = Math.max(1, (max - min)/(nums.length - 1));//bucket size, range of values, not number of values, 其size至少为1
        int len = (max - min)/gap + 1;//the number of buckets
        int[] bucketMin = new int[len];//to store min values of each bucket
        int[] bucketMax = new int[len];//to store max values of each bucet

        for(int n: nums) {
            int idx = (n - min)/gap;
            bucketMax[idx] = Math.max(bucketMax[idx], n);
            if(bucketMin[idx] == 0 || bucketMin[idx] > n) bucketMin[idx] = n;//这里要理解，因为我们要取较小的那个
        }
        int maxGap = 0, pre = min;//注意，这里必须需要一个pre变量，而不是仅仅通过bucketMin[i] - bucketMax[i - 1]来计算maxGap, 因为bucktMax[i - 1]有可能是空的bucket
        for(int i = 0; i < len; i++) {
            if(bucketMin[i] == 0 && bucketMax[i] == 0) continue;//empty bucket
            maxGap = Math.max(maxGap, bucketMin[i] - pre);
            pre = bucketMax[i];
        }
        return maxGap;
    }
}
