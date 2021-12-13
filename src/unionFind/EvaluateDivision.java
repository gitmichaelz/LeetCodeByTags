package unionFind;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 *
 *
 *
 * Example 1:
 *
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 */
public class EvaluateDivision {
    //this problem we dont use the genral UnionFind class, instead, we implement it separately to suit this problem
    Map<String, String> parent;//<node, parent of node>, particularly, if a/b = 2. we make parent.put(a, b); i.e. b is a's parent
    Map<String, Double> ratio;//<node, node/parent>
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        parent = new HashMap<>();
        ratio = new HashMap<>();
        for(int i = 0; i < equations.size(); i++) {
            union(equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }
        double[] res = new double[queries.size()];
        for(int i = 0; i < queries.size(); i++) {
            String s1 = queries.get(i).get(0), s2 = queries.get(i).get(1);
            if(!parent.containsKey(s1) || !parent.containsKey(s2)
                    || !find(s1).equals(find(s2))) {
                res[i] = -1.0;
            } else {
                res[i] = ratio.get(s1) / ratio.get(s2);
            }
        }
        return res;
    }

    private void union(String s1, String s2, double val) {
        if(!parent.containsKey(s1)){
            parent.put(s1, s1);
            ratio.put(s1, 1.0);
        }
        if(!parent.containsKey(s2)){
            parent.put(s2, s2);
            ratio.put(s2, 1.0);
        }
        String p1 = find(s1);
        String p2 = find(s2);
        parent.put(p1, p2);
        ratio.put(p1, val * ratio.get(s2) / ratio.get(s1));
    }

    //这里我们必须要用一个递归 bottom up来更新，因为我们需要找到最top的grandpa, 然后隔层更新到当前节点。ratio 的更新注意， 是ratio.get(s)(s/father) * ratio.get(father)(father/grandpa), 因为我们需要存s/grandpa,
    private String find(String s) {
        if(s.equals(parent.get(s))) {
            return s;
        }
        String father = parent.get(s);
        String grandpa = find(father);
        parent.put(s, grandpa);
        ratio.put(s, ratio.get(s) * ratio.get(father));
        return grandpa;
    }
}
