package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 *
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 *
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 *
 *
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 *
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        if(candidates == null || candidates.length == 0) return res;
        Arrays.sort(candidates);//important!!!
        backtrack(candidates, target, res, new ArrayList<Integer>(), 0);
        return res;
    }
    //dont forget the start!!!, it used to mark from which element we are choosing from the candidates
    //
    private void backtrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> list, int start) {
        if(target == 0){
            res.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = start; i < candidates.length && candidates[i] <= target; i++){//i from start, not from 0!!!否则如果我们已经访问一个数的情况下，会出现访问它之前的数，这样会出现重复结果，例如2，3，6，7 当我们访问6的时候，如果i每次从0开始，则下一次会继续从头访问，这样是不对的

            list.add(candidates[i]);
            backtrack(candidates, target - candidates[i], res, list, i);
            list.remove(list.size() - 1);
        }
    }
}
