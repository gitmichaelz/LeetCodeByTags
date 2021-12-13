package array_matrix_sorting;

import java.util.Arrays;

/**
 * Given an integer array nums and an integer k, return true if it is possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,3,2,3,5,2,1], k = 4
 * Output: true
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 */
public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k){
        if(k > nums.length) return false;
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        if(sum % k != 0) return false;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];//to mark which elements are used, why need this? for example, see the case[1, 2, 2, 3, 3, 4, 5], we need to distinguish 2, 2, 3, 3
        return dfs(nums, nums.length - 1, visited, k, 0, sum/k);
    }

    private boolean dfs(int[] nums, int pos, boolean[] visited, int k, int sum, int target){
        if(k == 0) return true;
        if(sum == target && dfs(nums, nums.length - 1, visited, k - 1, 0, target)) return true;//两个坑。1: pos 重置为 nums.length - 1,  2.这里sum 重置为0，

        for(int i = pos; i >= 0; i--){
            if(!visited[i] && sum + nums[i] <= target){
                visited[i] = true;
                if(dfs(nums, i - 1, visited, k, sum + nums[i], target)) return true;//坑三，这里是i - 1， 不是pos - 1
                visited[i] = false;
            }
        }
        return false;
    }
}
