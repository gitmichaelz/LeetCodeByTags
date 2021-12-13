package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 *
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 13
 * Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
 */
public class LexicographicalNumbers {
    //1ms
    public List<Integer> lexicalOrder(int n) {

        List<Integer> res = new ArrayList<>();

        for(int i = 1;i<=9 && i<=n;i++) {
            res.add(i);
            helper(i, n, res);
        }
        return res;
    }
    public void helper(int num,int n, List<Integer> list) {
        int curr = num*10;
        for(int i = curr;i<=curr+9 && i<=n;i++) {
            list.add(i);
            helper(i, n, list);
        }
    }
    //3ms
    // public List<Integer> lexicalOrder(int n) {
    //     List<Integer> list = new ArrayList<>(n);
    //     int curr = 1;
    //     for (int i = 1; i <= n; i++) {
    //         list.add(curr);
    //         if (curr * 10 <= n) {
    //             curr *= 10;
    //         } else if (curr % 10 != 9 && curr + 1 <= n) {
    //             curr++;
    //         } else {
    //             while ((curr / 10) % 10 == 9) {
    //                 curr /= 10;
    //             }
    //             curr = curr / 10 + 1;
    //         }
    //     }
    //     return list;
    // }
}
