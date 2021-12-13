package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
 *
 *     Only numbers 1 through 9 are used.
 *     Each number is used at most once.
 *
 * Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.
 *
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 */
public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), k, 1, n);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> list, int k,  int start, int target) {
        if (list.size() == k && target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= 9 && i <= target; i++) {
            list.add(i);
            backtrack(res, list, k, i + 1, target - i);
            list.remove(list.size() - 1);
        }
    }
}
