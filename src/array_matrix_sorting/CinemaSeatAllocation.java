package array_matrix_sorting;

import java.util.HashMap;
import java.util.Map;

/**
 * A cinema has n rows of seats, numbered from 1 to n and there are ten seats in each row, labelled from 1 to 10 as shown in the figure above.
 *
 * Given the array reservedSeats containing the numbers of seats already reserved, for example, reservedSeats[i] = [3,8] means the seat located in row 3 and labelled with 8 is already reserved.
 *
 * Return the maximum number of four-person groups you can assign on the cinema seats. A four-person group occupies four adjacent seats in one single row. Seats across an aisle (such as [3,3] and [3,4]) are not considered to be adjacent, but there is an exceptional case on which an aisle split a four-person group, in that case, the aisle split a four-person group in the middle, which means to have two people on each side.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, reservedSeats = [[1,2],[1,3],[1,8],[2,6],[3,1],[3,10]]
 * Output: 4
 * Explanation: The figure above shows the optimal allocation for four groups, where seats mark with blue are already reserved and contiguous seats mark with orange are for one group.
 */
public class CinemaSeatAllocation {
    //https://leetcode.com/problems/cinema-seat-allocation/discuss/546451/Java-Straightforward-solution-(bitwise)
    int LEFT_AISLE_CROSSING = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
    int MIDDLE = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
    int RIGHT_AISLE_CROSSING = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int[] seat: reservedSeats){
            map.put(seat[0], map.getOrDefault(seat[0], 0) | (1 << seat[1]));
        }
        int max = 0;
        for(int row : map.keySet()){
            int count = 0;
            int reserved = map.get(row);
            if((reserved & LEFT_AISLE_CROSSING) == 0) count++;
            if((reserved & RIGHT_AISLE_CROSSING) == 0) count++;
            if((reserved & MIDDLE) == 0 && count == 0) count = 1;
            max += count;
        }
        return max + 2 * (n - map.size());//别忘了剩下的row,每行最多可以坐两个四人家庭
    }
}
