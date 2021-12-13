package dfs_backtracking_Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space, respectively.
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
 *
 * Example 2:
 *
 * Input: n = 1
 * Output: [["Q"]]
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 9
 */
public class N_Queens {
    //用一个循环递归处理子问题。在每一层递归函数中，我们用一个循环把一个皇后填入对应行的某一列中，如果当前棋盘合法，我们就递归处理下一行，
    //找到正确的棋盘我们就存到结果集里
    //这种题目都是一个套路。就是用一个循环去枚举当前所有情况，然后把元素加入，递归，再把元素移除。
    //这道题不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，如果二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的。
    //这题最后一个细节就是怎么检查当前棋盘合法性的问题，因为除了刚加进来的那个皇后，前面都是合法的，我们只需要检查当前行和前面的所有行是否冲突即可。
    //检查是否同列很简单，检查对角线就是行的差和列的差的绝对值不要相等就可以。
    //n*n!
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] colForRow = new int[n];//colForRow[i] means the Q is positioned in ith row and colForRow[i]th col
        solve(n, 0, res, colForRow);
        return res;
    }
    private void solve(int n, int row, List<List<String>> res, int[] colForRow){
        if(row == n) {//if row == n, means we already have a fully legal chessboard
            List<String> list = new ArrayList<>();
            for(int i = 0; i < n; i++){
                char[] tmp = new char[n];//构造第ith行
                Arrays.fill(tmp, '.');
                tmp[colForRow[i]] = 'Q';
                list.add(new String(tmp));
            }
            res.add(list);
            return;//dont forget return
        }
        for(int i = 0; i < n; i++) {
            colForRow[row] = i;//try each col from 0 to n - 1 for each row
            if(check(row, colForRow)) {
                solve(n, row + 1, res, colForRow);
            }
        }
    }
    //这里check的是前row行的棋盘的合法性，而不是整个棋盘的合法性，所以再考虑这句话“这道题不用移除的原因是我们用一个一维数组去存皇后在对应行的哪一列，因为一行只能有一个皇后，如果二维数组，那么就需要把那一行那一列在递归结束后设回没有皇后，所以道理是一样的。”就好理解了
    //这就是我们用一维数组不用reset的原因，如果是二维数组，则需要restore当前行当前列，再试下一个available cell
    private boolean check(int row, int[] colForRow){
        for(int i = 0; i < row; i++) {
            if(colForRow[row] == colForRow[i] || Math.abs(colForRow[row] - colForRow[i]) == row - i) {//检查是否同行同列以及两个对角线
                return false;
            }
        }
        return true;
    }
}
