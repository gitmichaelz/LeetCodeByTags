package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 *
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSumII {
    //the only difference with I is each element may only be used once.
    //for example, int[] candidates = {1, 1, 2, 5, 6, 7, 10}; if we dont dedup, the result would be
    //{{1, 1, 6}, {1, 2, 5}, {1, 7}, {1, 2, 5}, {1, 7}, {2, 6}}
    //{1, 2, 5}, {1, 7} occur 2 times because we are using two same "1" as a start.
    //how to avoid this? after first 1, 2, 5, when i is still = start (0), before we go to i = 1, when first remove candi[start(0)] = 1 from
    //our list, then go to next iteration. now, start = 0, i = 1, and we check (i > start && candi[i] == candi[i - 1]), so we skip the second 1 which
    //is candi[i(1)] = 1
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);//this one also needs sort. because we need to find all unique combinations,
        backtrack(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }

    private static void backtrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> list, int start){
        if(target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
            //this check is important! if i is not the start and candi[i] == candi[i - 1], we skip it, because we already processed i - 1
            if(i > start && candidates[i] == candidates[i - 1]) continue;//dedupe, if we already have 1, 2(first), 5, we dont need 1, 2(second), 5
            if(candidates[i] <= target){
                list.add(candidates[i]);
                backtrack(candidates, target - candidates[i], res, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
