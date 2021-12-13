package math;
/**
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
 *
 *     "123"
 *     "132"
 *     "213"
 *     "231"
 *     "312"
 *     "321"
 *
 * Given n and k, return the kth permutation sequence.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 3
 * Output: "213"
 *
 * Example 2:
 *
 * Input: n = 4, k = 9
 * Output: "2314"
 */

import java.util.ArrayList;

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        ArrayList<Integer> numberList = new ArrayList<Integer>();
        int fac=1;
        for(int i=1; i<=n; i++){
            numberList.add(i);
            fac = fac * i; //fac初始化为n!
        }
        k--;//start from 1 in the question, start from 0 in our code,因为我们要计算numberList的index,第一个相当于index=0
        StringBuilder sb = new StringBuilder();
        for(int i= n; i > 0; i--){
            fac = fac/i;//若计算第一位，则后面有n-1位，后面n-1位共（n-1）!个序列，fac更新为(n-1)!
            int index = k / fac; //计算index
            k = k % fac;//这一步要理解。k用于下一次迭代，即在(n-1)!个序列中处于啥位置（第几个），所以用模运算
            sb.append(numberList.get(index));
            numberList.remove(index);//!!不要忘记除去已经使用过的数字
        }
        return sb.toString();
    }
}
