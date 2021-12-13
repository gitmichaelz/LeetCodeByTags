package array_matrix_sorting;

import java.util.*;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 */
public class FindKPairsWithSmallestSums {
    //https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
    //Use min_heap to keep track on next minimum pair sum, and we only need to maintain K possible candidates in the data structure.
    //Some observations: For every numbers in nums1, its best partner(yields min sum) always strats from nums2[0] since arrays are all sorted;
    //And for a specific number in nums1, its next candidate sould be [this specific number] + nums2[current_associated_index + 1], unless out of boundary;)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k){
        List<List<Integer>> res = new ArrayList<>();
        if(nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a ->a[0] + a[1]));
        for(int i = 0; i < nums1.length && i < k; i++){
            pq.offer(new int[]{nums1[i], nums2[0], 0});//pq initialized with k numbers from nums1 and 1 number from nums2
        }

        while(k-- > 0 && !pq.isEmpty()){
            int[] cur = pq.poll();
            res.add(Arrays.asList(cur[0], cur[1]));
            if(cur[2] == nums2.length - 1) continue;//we already hit the end of nums2
            pq.offer(new int[]{cur[0], nums2[cur[2] + 1], cur[2] + 1});
        }
        return res;
    }



    // class Triple{
    //     int first;
    //     int second;
    //     int index;//the index in nums2
    //     Triple(int first, int second, int index){
    //         this.first = first;
    //         this.second = second;
    //         this.index = index;
    //     }
    // }
    // public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
    //     List<List<Integer>> res = new ArrayList<>();
    //     if(nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0 || k == 0) return res;
    //     //pq is storing the index from nums1, nums2
    //     PriorityQueue<Triple> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.first + a.second));//等同于(a,b) -> (a.first + a.second) - (b.first + b.second])
    //     for(int i = 0; i < nums1.length && i < k; i++){
    //         pq.offer(new Triple(nums1[i], nums2[0], 0));//pq initialized with k numbers from nums1 and 1 number from nums2
    //     }
    //     while(k-- > 0&& !pq.isEmpty()){
    //         Triple cur = pq.poll();
    //         res.add(Arrays.asList(cur.first, cur.second));
    //         if(cur.index == nums2.length - 1) continue;//we already hit the end of nums2
    //         pq.offer(new Triple(cur.first, nums2[cur.index + 1], cur.index + 1));
    //     }
    //     return res;
    // }
}
