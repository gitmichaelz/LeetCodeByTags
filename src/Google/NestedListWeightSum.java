package Google;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

//time: O(N), N is total number of, helper() is called exactly once for each nested list. N also includes nested integers,
//the number of recursive calls has to be less than N.
//space:O(N), at most O(D) recursive calls are placed on the stack, where D is the maximum depth, in the worst case, D = N
public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedIntegerList) {
        return helper(nestedIntegerList, 1);
    }

    private int helper(List<NestedInteger> nestedIntegerList, int depth) {
        int sum = 0;
        for (NestedInteger i : nestedIntegerList) {
            if (i.isInteger()) {
                sum += i.getInteger() * depth;
            } else {
                sum += helper(i.getList(), depth + 1);
            }
        }
        return sum;
    }

    //BFS
    //TIME: O(N)
    //SPACE: O(N)
    public int depthSumBFS(List<NestedInteger> nestedIntegerList) {
        if (nestedIntegerList == null) return 0;
        int sum = 0;
        int depth = 0;
        Deque<NestedInteger> queue = new ArrayDeque<>(nestedIntegerList);
        while (!queue.isEmpty()) {
            depth++;
            for (int size = queue.size(); size > 0; size--) {
                NestedInteger i = queue.poll();
                if (i.isInteger()) {
                    sum += i.getInteger() * depth;
                } else {
                    for (NestedInteger nested : i.getList()) {
                        queue.offer(nested);
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 错误做法，如果depth是全局的，这样其他层的深度会改变当前层的depth,导致计算错误
    int depth = 1;
    public int depthSum(List<NestedInteger> nestedIntegerList) {
        int sum = 0;
        for (NestedInteger ni : nestedIntegerList) {
            if (ni.isInteger()) {
                sum += depth * ni.getInteger();
            } else {
                sum += depthSum(ni.getList());
            }
        }
        depth++;
        return sum;
    }
     */
}
