package math;

/**
 * Given an integer n, return the nth digit of the infinite integer sequence [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 3
 *
 * Example 2:
 *
 * Input: n = 11
 * Output: 0
 * Explanation: The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 */
public class NthDigit {
    //find the length of the number where the nth digit is from
    //find the actual number where the nth digit is from
    //find the nth digit and return

    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
    /*
    sequence 1 2 3 4 5 6 7 8 9 1 0 1 1 1 2 1 3 1 4 1 5 1 6
Nth digital 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23

I list sequence 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
blow the sequence is the Nth digital, like the 11th digital is 0, 12 is 1, 13 is 1, 14 is 1, 15 is 2, 16 is 1, 17 is 3.........

Sot ehe regular is very oberviously now:
1-------9 9*1 = 9 digits
10-----99 90 *2 = 180 digits
100---999 900 * 3 = 2700 digits

Now, for example gave N = 1000, then 1000-9-180 = 811, it means the 811th digit local in [100, 999], and we know each number like 100 has three digit, so 811 / 3 = 270,

Then, we know the 270th number in [100, 999], is 270th + 100 (start from 100) = 370.

370 still has three digit, which one is the answer? 3, 7, 0

811 % 3 = 1, so the first one is the answer, so return 3.
     */
}
