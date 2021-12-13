package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 */
public class TopKFrequentElements {
    //approach 1> bucket sort O(N)
    //build an array of list to be buckets with length 1 to sort
    //arr[2] means the elements with frequency 2
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        List<Integer>[] buckets = new List[nums.length + 1];//坑，注意buckets长度，因为nums里可能全是同一元素，这是他的frequency是nums.length
        for(int key : map.keySet()){
            int frequency = map.get(key);
            if(buckets[frequency] == null){
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(key);
        }
        int[] res = new int[k];
        for(int i = buckets.length - 1, p = 0; i >= 0 && p < k; i--){//p is the pointer in res
            if(buckets[i] == null) continue;
            for(int num : buckets[i]) {
                res[p++] = num;
                if(p == k) break;
            }
        }

        return res;
    }

    //approach 2: map + priorityqueue nlogk
    //approach 2: map + heap
//     public int[] topKFrequent(int[] nums, int k) {
//         Map<Integer, Integer> map = new HashMap<>();
//         for(int num : nums) {
//             map.put(num, map.getOrDefault(num, 0) + 1);
//         }

//         PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.getValue()));
//         for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
//             pq.offer(entry);
//             if(pq.size() > k) {
//                 pq.poll();
//             }
//         }
//         int[] res = new int[k];
//         int idx = k - 1;
//         while(!pq.isEmpty()){
//             res[idx--] = pq.poll().getKey();
//         }
//         return res;
//     }
}
