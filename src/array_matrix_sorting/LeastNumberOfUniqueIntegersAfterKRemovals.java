package array_matrix_sorting;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers arr and an integer k. Find the least number of unique integers after removing exactly k elements.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,5,4], k = 1
 * Output: 1
 * Explanation: Remove the single 4, only 5 is left.
 */
public class LeastNumberOfUniqueIntegersAfterKRemovals {
    //O(N)
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] count = new int[arr.length + 1];
        for(int val : map.values()) {
            count[val]++;
        }
        int uniqNums = map.size();
        int i = 1;
        while(k > 0) {
            if(k >= count[i] * i) {//there are count[i] element which has count i
                k -= count[i] * i;
                uniqNums -= count[i];
                i++;
            } else {
                return uniqNums - k / i;//k / i : the num of uniq elements to be removed:
            }
        }
        return uniqNums;
    }


    // public int findLeastNumOfUniqueInts(int[] arr, int k) {
    //      Map<Integer, Integer> map = new HashMap<>();
    //      for(int num : arr) {
    //          map.put(num, map.getOrDefault(num, 0) + 1);
    //      }
    //      PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
    //      for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //          pq.offer(entry);
    //      }
    //      while(!pq.isEmpty() && k > 0) {
    //          if(pq.peek().getValue() > k) {
    //              break;
    //          }
    //          k -= pq.poll().getValue();
    //      }
    //      return pq.size();
    // }
}
