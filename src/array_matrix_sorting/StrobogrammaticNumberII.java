package array_matrix_sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * //same idea, different implementation
 *     //虽然思路一样，但这个实现更快一点 2ms 100%
 *     private char[][] pairs = new char[][] {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'9', '6'}, {'8', '8'}};
 *     public List<String> findStrobogrammatic(int n){
 *         List<String> res = new ArrayList<>();
 *         dfs(res, new char[n], 0, n-1);
 *         return res;
 *     }
 *
 *     private void dfs(List<String> res, char[] num, int left, int right){
 *         if(left > right){
 *             res.add(new String(num));
 *             return;
 *         }
 *
 *         for(char[] pair : pairs){
 *             num[left] = pair[0];
 *             num[right] = pair[1];
 *             if(num.length > 1 && num[0] == '0') continue;//0x0非法
 *             if(left == right && pair[0] != pair[1]) continue;//只有00， 11， 88合法
 *             dfs(res, num, left + 1, right - 1);
 *         }
 *     }
 */

public class StrobogrammaticNumberII {
    //same idea, different implementation
    //虽然思路一样，但这个实现更快一点 2ms 100%
    private char[][] pairs = new char[][] {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'9', '6'}, {'8', '8'}};
    public List<String> findStrobogrammatic(int n){
        List<String> res = new ArrayList<>();
        dfs(res, new char[n], 0, n-1);
        return res;
    }

    private void dfs(List<String> res, char[] num, int left, int right){
        if(left > right){
            res.add(new String(num));
            return;
        }

        for(char[] pair : pairs){
            num[left] = pair[0];
            num[right] = pair[1];
            if(num.length > 1 && num[0] == '0') continue;//0x0非法
            if(left == right && pair[0] != pair[1]) continue;//只有00， 11， 88合法
            dfs(res, num, left + 1, right - 1);
        }
    }

//     public List<String> findStrobogrammatic(int n) {
//         return helper(n, n);
//     }
//     //递归的两个base case: n = 0, 为"". n = 1, 则有"0" "1" "8"
//     private List<String> helper(int cur, int max) {
//         if (cur == 0) return new ArrayList<>(Arrays.asList(""));
//         if (cur == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

//         List<String> lower = helper(cur - 2, max);

//         List<String> res = new ArrayList<>();

//         for (int i = 0; i < lower.size(); i++) {
//             String s = lower.get(i);

//             if (cur != max) res.add("0" + s + "0");//注意，只有n!= m时，我们才可以有00的形式，即1001是合法的但 00或者010是不合法的

//             res.add("1" + s + "1");
//             res.add("6" + s + "9");
//             res.add("8" + s + "8");
//             res.add("9" + s + "6");
//         }

//         return res;
//     }
}
