package math;
/**
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 *     Every integer is in the inclusive range [0, 2n - 1],
 *     The first integer is 0,
 *     An integer appears no more than once in the sequence,
 *     The binary representation of every pair of adjacent integers differs by exactly one bit, and
 *     The binary representation of the first and last integers differs by exactly one bit.
 *
 * Given an integer n, return any valid n-bit gray code sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: [0,1,3,2]
 * Explanation:
 * The binary representation of [0,1,3,2] is [00,01,11,10].
 * - 00 and 01 differ by one bit
 * - 01 and 11 differ by one bit
 * - 11 and 10 differ by one bit
 * - 10 and 00 differ by one bit
 * [0,2,3,1] is also a valid gray code sequence, whose binary representation is [00,10,11,01].
 * - 00 and 10 differ by one bit
 * - 10 and 11 differ by one bit
 * - 11 and 01 differ by one bit
 * - 01 and 00 differ by one bit
 */

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    //My idea is to generate the sequence iteratively. For example, when n=3, we can get the result based on n=2
    //(00, 01, 11, 10) -> (110, 111, 101, 100)
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        for(int i=0; i<n; i++){
            int differ = 1 << i; //最左边添加1.
            for(int j = res.size()-1; j>=0; j--){//在已有集合的基础上，添加最高位，但注意要逆序进行。
                res.add(differ | res.get(j));
            }
        }
        return res;
    }
}
