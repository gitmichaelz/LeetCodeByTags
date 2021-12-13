package array_matrix_sorting;

/**
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper and citations is sorted in an ascending order, return compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: A scientist has an index h if h of their n papers have at least h citations each, and the other n − h papers have no more than h citations each.
 *
 * If there are several possible values for h, the maximum one is taken as the h-index.
 *
 * You must write an algorithm that runs in logarithmic time.
 *
 *
 *
 * Example 1:
 *
 * Input: citations = [0,1,3,5,6]
 * Output: 3
 * Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had received 0, 1, 3, 5, 6 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 */
public class H_IndexII {
    //二分，找下界
    public int hIndex(int[] citations) {
        if(citations == null || citations.length == 0) return 0;
        int left = 0, right = citations.length - 1;
        while(left < right){
            int mid = left + (right - left)/2;
            if(citations[mid] >= (citations.length - mid)){//满足条件，继续向左边逼近
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return citations[left] >= citations.length - left? citations.length - left : 0;//坑，一定要判断，例如【0】，我们没有进入while循环，所以需要额外判断
    }
}
