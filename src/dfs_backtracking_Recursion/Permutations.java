package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 *
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 6
 *     -10 <= nums[i] <= 10
 *     All the integers of nums are unique.
 */
public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, res, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, List<Integer> list){
        if(list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int num : nums){
            if(list.contains(num)) continue;
            list.add(num);
            backtrack(nums, res, list);
            list.remove(list.size() - 1);//注意。Integer泛型的List, remove操作如果是list.remove(2)这种的，是删除角标对应的数。如果想删除元素2.则要用//list.remove(Integer.valueOf(2))
        }
    }
}
