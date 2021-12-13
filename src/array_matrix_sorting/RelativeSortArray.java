package array_matrix_sorting;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 *
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2. Elements that do not appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 */
public class RelativeSortArray {
    /**
     *
     Because 0 <= arr1[i], arr2[i] <= 1000, we use an array to count every element.
     Go through every element in the second array, and update values of the first array based on the order in the second array.

     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int[1001];//counting sort
        for(int n : arr1) {
            count[n]++;
        }
        int i = 0;
        for(int n : arr2) {
            while(count[n]-- > 0) {
                arr1[i++] = n;
            }
        }
        for(int n = 0; n < count.length; n++) {
            while(count[n]-- > 0) {
                arr1[i++] = n;
            }

        }
        return arr1;
    }
}
