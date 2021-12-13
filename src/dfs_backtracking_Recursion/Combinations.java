package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 *
 * You may return the answer in any order.
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, res, new ArrayList<>(), 1);
        return res;
    }
    //k means how many left need to be added to list
    private void backtrack(int n, int k, List<List<Integer>> res, List<Integer> list, int start){
        if(k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        //一点优化，其实i 不用循环到 i <= n; 因为还剩k个，所以让i 满足 i <= n - k + 1 即可
        //for example, n = 7, current k = 3, i只需要从1 取到 7 - 3 + 1 = 5即可
        for(int i = start; i <= n - k + 1; i++){
            list.add(i);
            backtrack(n, k - 1, res, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
