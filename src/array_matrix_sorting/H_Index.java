package array_matrix_sorting;

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: A scientist has an index h if h of their n papers have at least h citations each, and the other n − h papers have no more than h citations each.
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 */
public class H_Index {
    //h index means there are h papers with at least h references.
    //so h cannot exceed the total number n, so we use an array with length n + 1, indexed from 0 to n
    //once we have a paper with citation c, we increase arr[c]; then we sum arr[c] backwards from n to 0, once
    //we have total sum >= c, we have the h index and return it, and it is the maximum citation
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int n = citations.length;
        int[] arr = new int[n + 1];//arr[i]表示被引用i次的论文数量是arr[i]篇，
        for(int c : citations) {
            if(c > n) {
                arr[n]++;//c cannot exceed the total number of papers!
            } else {
                arr[c]++;
            }
        }
        int count = 0;
        for(int i  = n; i >= 0; i--) {
            count += arr[i];
            if(count >= i) return i;
        }
        return 0;
    }
}
