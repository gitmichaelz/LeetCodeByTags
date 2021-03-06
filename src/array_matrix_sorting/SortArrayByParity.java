package array_matrix_sorting;

/**
 * Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
 *
 * Return any array that satisfies this condition.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,2,4]
 * Output: [2,4,3,1]
 * Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
 */
public class SortArrayByParity {
    public int[] sortArrayByParity(int[] A) {
        for(int i = 0, j = A.length - 1; i < j; ) {
            if(A[i] % 2 == 1 && A[j] % 2 == 0) {
                swap(A, i, j);
                i++;
                j--;
            } else if(A[i] % 2 == 0) {
                i++;
            } else {
                j--;
            }
        }
        return A;
    }

    private void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}
