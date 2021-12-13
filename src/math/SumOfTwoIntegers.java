package math;

/**
 * Given two integers a and b, return the sum of the two integers without using the operators + and -.
 *
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 */
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        if(b == 0) return a;
        int carry = (a & b) << 1;//carry can be calculated by (a & b) << 1;
        int sum = a ^ b;//xor can hanlde the a + b without carry
        return getSum(sum, carry);
    }
    //or one line version
    // public int getSum(int a, int b){
    //     return b == 0? a : getSum(a^b, (a & b) << 1);
    // }
}
