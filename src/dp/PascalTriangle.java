package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 */
public class PascalTriangle {
    //time O(1+2+3+...+n)=O(n^2)
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if(numRows < 1) return res;

        for(int i = 0; i < numRows; i++) {
            List<Integer> newList = new ArrayList<>();
            newList.add(1);
            if(i > 0) {
                List<Integer> lastList = res.get(i - 1);
                for(int j = 1; j < lastList.size(); j++) {
                    newList.add(lastList.get(j) + lastList.get(j - 1));
                }
                newList.add(1);
            }
            res.add(newList);
        }
        return res;
    }
}
