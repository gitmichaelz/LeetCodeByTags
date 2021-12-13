package array_matrix_sorting;

/**
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.
 *
 * Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 21
 */
public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] str = String.valueOf(n).toCharArray();
        int N = str.length;
        int i = N - 1, j = N - 1;
        for (; i > 0; i--) {
            if (str[i - 1] < str[i]) break;
        }

        if (i == 0) return -1; //说明数组原本就是单调递减的（已经是最大值了），可直接返回-1
        for (; j >= i; j--) {  //如果不是最大值，则找到右侧只大一点的数，swap
            if (str[j] > str[i - 1]) break;
        }
        swap(str, i - 1, j);

        reverse(str, i, N - 1);

        //返回的时候，考虑超出32bit int边界的情况
        try {
            return Integer.parseInt(new String(str));
        } catch (Exception e) {
            return -1;
        }

    }

    private void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }
    private void reverse(char[] str, int l, int r) {
        while (l < r) {
            swap(str, l, r);
            l++;
            r--;
        }
    }
}
