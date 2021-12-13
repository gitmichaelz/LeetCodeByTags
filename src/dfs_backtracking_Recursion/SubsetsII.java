package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 *
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 */
public class SubsetsII {
    //如果有重复元素， 我们需要做的一定是 排序，去重
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        backtrack(nums, res, new ArrayList<>(), 0);
        return res;
    }
    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> list, int start) {
        res.add(new ArrayList<>(list));
        for(int i = start; i < nums.length; i++) {
            if(i != start && nums[i] == nums[i - 1]) continue;//在同一层递归里面，不能选取同一个元素，不同层可以选取。
            list.add(nums[i]);
            backtrack(nums, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
