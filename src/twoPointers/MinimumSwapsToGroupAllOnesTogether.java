package twoPointers;

/**
 * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: data = [1,0,1,0,1]
 * Output: 1
 * Explanation:
 * There are 3 ways to group all 1's together:
 * [1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0] using 2 swaps.
 * [0,0,1,1,1] using 1 swap.
 * The minimum is 1.
 */
public class MinimumSwapsToGroupAllOnesTogether {
    public int minSwaps(int[] data) {
        int cnt = 0;
        for (int num : data) if (num == 1) cnt++;
        if (cnt == 0) return 0;
        int left = 0, zeros = 0, min = data.length;
        for (int right = 0; right < data.length; right++){
            if (data[right] == 0) zeros++;
            //if window size larger than number of 1s move left pointer
            if (right - left + 1 > cnt){
                if (data[left] == 0) zeros--;
                left++;
            }
            //for every correct window size we record the number of zeros which is the number of switch
            if (right - left + 1 == cnt) min = Math.min(min, zeros);
        }
        return min;
    }
}
