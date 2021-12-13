package math;

/**
 * Given a non-negative integer x, compute and return the square root of x.
 *
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 *
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 */
public class Sqrt_x {
    //I think this problem's key point is that we want to find the largest number, which num*num <= x, therefore we should use
    // the binary search to find the upper bound within the range(1,x).
    public int mySqrt(int x) {
        if(x == 0) return 0;
        int left = 1, right = x;
        while(left < right) {
            int mid = right - (right - left) / 2;
            if(mid <= x/mid) {//mid * mid <= x, 符合条件, 这样写的目的是为了防止overflow
                left = mid;
            } else {//mid * mid > x,不符合条件
                right = mid - 1;
            }
        }
        return left;
    }

    public static float mySqrtWithPrecision(int x, int precision) {
        int left = 0, right = x;
        while(left < right) {
            int mid = right - (right - left) / 2;
            if(mid <= x/mid) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        double res = left;
        //next, compute fractional part of square root upto given precision
        double increment = 0.1;
        for(int i = 0; i < precision; i++) {
            while(res * res <= x) {
                res += increment;
            }
            //while loop terminates when left * left > x
            res -= increment;
            increment /= 10;
        }
        return (float) res;
    }
}
