package math;

/**
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, return the Hamming distance between them.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 */
public class HammingDistance {
    //The problem is basically the same as counting the 1 bits in an integer, and the useful trick to do that is :
    // xor & (xor - 1) will eliminate the last 1 bit in a integer.
    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        int count = 0;
        while(n != 0){
            count++;
            n &= (n - 1);
        }
        return count;
    }
}
