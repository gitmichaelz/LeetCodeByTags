package amz;
//无重复的数组把最小的移到最左边，最大的移到最右边，每次只能swap相邻的两个，求最少的swap次数。
public class MinSwaps_Min_Max {
    public static void main(String[] args) {
        int[] arr = {5, 2, 6, 9, 1, 7, 0, 3};

    }

    public static int min_Swaps(int[] arr) {
        int maxIdx = 0, minIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIdx]) {
                minIdx = i;
            } else {
                maxIdx = i;
            }
        }
        int len = arr.length;
        return minIdx < maxIdx ? minIdx + len - maxIdx - 1 : minIdx + len - maxIdx - 2;
    }
}
