package array_matrix_sorting;

/**
 * Given an integer array nums, return the number of longest increasing subsequences.
 *
 * Notice that the sequence has to be strictly increasing.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int[] cache = new int[nums.length];
        int[] freq = new int[nums.length];

        int resSize = 0;
        int resFreq = 0;

        for (int i = 0; i < nums.length; i++){
            cache[i] = 1;
            freq[i] = 1;

            for (int j = i - 1; j >= 0; j--){
                if (nums[j] < nums[i]){
                    if (cache[j] + 1 > cache[i]){
                        cache[i] = cache[j] + 1;
                        freq[i] = freq[j];
                    } else if (cache[j] + 1 == cache[i]){
                        freq[i] += freq[j];
                    }
                }
            }

            if (cache[i] > resSize){
                resSize = cache[i];
                resFreq = freq[i];
            } else if (cache[i] == resSize){
                resFreq += freq[i];
            }
        }

        return resFreq;
    }
}
