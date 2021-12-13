package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.
 */
public class PascalTriangleII {
    //in 2d: res[i][j] = res[i - 1][j] + res[i - 1][j - 1]
    //only need pre row, 2d => 1d, for any i > 0. res[i] = res[i] + res[i - 1],
    //since we will use left cell, scanning from left to right will erase left cell, so we need to scan from right to left
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if(rowIndex < 0) return res;
        for(int i = 0; i <= rowIndex; i++ ){
            res.add(1);//每一行，现在尾部加1
            if(i > 0) {//不要忘记判断i > 0
                for(int j = i - 1; j > 0; j--) {//注意是j = i - 1开始，第i行，共有i + 1个元素。
                    res.set(j, res.get(j) + res.get(j - 1));
                }
            }

        }
        return res;
    }
}
