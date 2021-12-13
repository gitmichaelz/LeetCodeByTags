package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr. Since the answer may be large, return the answer modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,1,2,4]
 * Output: 17
 * Explanation:
 * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
 * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
 * Sum is 17.
 */
public class SumOfSubarrayMinimums {
    //1, 3 1,       1, 1 2, 1 2 4
    //1, 1 2, 1 2 4, 3 1, 3 1 2,  3 1 2 4
    //res = sum(A[i] * f(i)), where f(i) is the number of subarrays, A[i] is the minimum in each subarray
    //to get f(i), we need to find left[i] and right[i], where left[i] is length of strict bigger numbers on the left of A[i]
    //and right[i] is length of bigger numbers on the right of A[i]
    //f(i) = left(i) * right(i)
    //use a monotonic stack to store all the elements in a way that, for each A[i], all elements before A[i] is less than A[i], and the length of subarray that with minimum of A[i]
    //after we calculate left(i), we can get right(i) using the same way. And for dedupe purpose, we set non-strict and strict subarray for left and right subarray.
    //为什么用stack,因为stack的LIFO性质，最前面的是刚刚加进去的，也就是所求的包含的当前元素的subarray，所以会用stack.
    //单调栈的意思就是，出栈顺序单调递增或递减的。
    //单调递增栈是出栈顺序单调递增的。栈顶最小，栈底最大。
    //单调递减栈是出栈顺序单调递减的。栈顶最大，栈底最小。
    //本题用到的是单调递减栈。即要保证当前元素为止，栈内元素从底到顶递增(这样才能出栈递减)。这样我们默认以当前元素为最小值的subarray的只包含它自己，(因为他左边的都比他小)。如果出现当前元素比栈顶的小。这样需要退栈，即需要扩展subarray的范围。因为有更小的元素出现，其subarray的范围会变大。
    // the monotonic stack helps us maintain maximum and and minimum elements in the range and keeps the order of elements in the range.
    //注意单调递减栈和遍历arr是的反直觉。我们遍历arr时是维持到当前元素的一个递增顺序。在栈里的话，从栈顶到栈底是递减，即出栈顺序递减，而从栈底到栈顶是递增。(扫描数组的顺序递增)

    public int sumSubarrayMins1(int[] arr) {
        long res = 0, mod = 1000000000 + 7;
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<int[]> s1 = new ArrayDeque<>();
        Deque<int[]> s2 = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int count = 1;//nums[i]本身算一个
            while (!s1.isEmpty() && s1.peek()[0] > arr[i]) {
                count += s1.pop()[1];
            }
            s1.push(new int[] {arr[i], count});
            left[i] = count;
        }
        for (int i = n - 1; i >= 0; i--) {
            int count = 1;
            while (!s2.isEmpty() && s2.peek()[0] >= arr[i]) {//to handle duplicates, we need to set non-strict and strict subarray
                count += s2.pop()[1];
            }
            s2.push(new int[] {arr[i], count});
            right[i] = count;
        }
        for (int i = 0; i < n; i++) {
            res = (res + (long)arr[i] * left[i] *  right[i]) % mod;
        }
        return (int)res;
    }

    //one pass
    //相同思路。只是单调栈存的是index
    //单调递减栈(维护一个递增序列，见上面定义). 如果碰到当前访问元素小于栈顶元素。则扩展subarray。以每个可能的位置来计算加进结果集。
    //比如A[stack.peek()] > A[right], 则说明[stack.peek(), right - 1]范围内的最小值是A[stack.peek()],而这时上面思路中的right部分。因为维护的单调递增顺序。即i = stack.pop()之后，下一个栈顶元素满足A[stack.peek()] < A[i],那么我们知道[stack.peek() + 1, i]范围内的最小值也是A[i],即left 部分。这样可以求出左边右边的subarry长度然后计算当前A[i]被当做min用了多少次。
    public int sumSubarrayMins(int[] A) {
        Deque<Integer> stack = new ArrayDeque<>();//store the index of element
        int len = A.length;
        long res = 0, mod = 1000000000 + 7;
        for (int left = 0, right = 0, i = 0; right <= len; right++) {
            while (!stack.isEmpty() && A[stack.peek()] > (right == len? 0 : A[right])) {//A[i] >= 1;
                i = stack.pop();
                left = stack.isEmpty()? -1 : stack.peek();
                res = (res + (long)A[i] * (right - i) * (i - left)) % mod;
            }
            stack.push(right);
        }
        return (int) res;
    }
}
