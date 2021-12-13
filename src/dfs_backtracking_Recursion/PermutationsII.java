package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums.length <= 8
 *     -10 <= nums[i] <= 10
 */
public class PermutationsII {
    //we need to dedupe, first we need to sort the array
    //then we need a boolean array to mark which position is visited
    //we only recurse the first unvisited element, for example 1,1,2 we only recurse from first 1, if (!visited[0] && nums[i] == nums[i - 1], we continue
    //also, if current element is visited, we continue
    //when a number has the same value with its previous, we can use this number only if his previous is used
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        boolean[] visited = new boolean[nums.length];//因为有重复元素，不能用list.contains()来判断是否已经访问某元素了
        permute(nums, res, visited, new ArrayList<>());
        return res;
    }

    private static void permute(int[] nums, List<List<Integer>> res, boolean[] visited, List<Integer> path) {
        if(path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i] || (i != 0 && nums[i] == nums[i - 1] && !visited[i - 1])) continue;//一定要弄明白这里的条件，错了好多次了。
            path.add(nums[i]);
            visited[i] = true;
            permute(nums, res, visited, path);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
