package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as the product of their factors.
 *
 *     For example, 8 = 2 x 2 x 2 = 2 x 4.
 *
 * Given an integer n, return all possible combinations of its factors. You may return the answer in any order.
 *
 * Note that the factors should be in the range [2, n - 1].
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: []
 *
 * Example 2:
 *
 * Input: n = 12
 * Output: [[2,6],[3,4],[2,2,3]]
 */
public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfsFactor(res, path, n, 2);
        return res;
    }

    public void dfsFactor(List<List<Integer>> res, List<Integer> path, int n, int start){
        //we only consider i until i*i<=n, the idea is we do two steps of backtracking.We first add 2,6 in the array for 12, and remove 6 for further caculation on 6. Then remove 2 for further combination
        for(int i = start; i * i <= n; i++){
            if(n % i == 0){
                path.add(i);
                path.add(n / i);
                res.add(new ArrayList<>(path));
                path.remove(path.size() - 1);
                dfsFactor(res, path, n / i, i);
                path.remove(path.size() - 1);//正是因为这些remove操作，才使得本层加入path的元素最终都被移除。restore path，and try next available combination
            }
        }
    }

}
