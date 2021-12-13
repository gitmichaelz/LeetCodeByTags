package hashtable;

import java.util.*;

/**
 * Given an array of strings words and an integer k, return the k most frequent strings.
 *
 * Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["i","love","leetcode","i","love","coding"], k = 2
 * Output: ["i","love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 */
public class TopKFrequentWords {
    //因为需要知道每个词的frequency,所以需要一个map
    //因为需要排序求前K个最频繁的，所以用一个pq，存储top k个
    //这个题最重要的就是这个pq,关于如何排序的问题。因为按照题目要求，需要找出TOP K最频繁的。所以用最小堆按频率来存。边存边删掉超过K的单词
    // (删掉的是pq里最小的，即频率最小的)，我们最后的结果需要最频繁的在前面，所以需要倒序加到结果集。
    //同时还有相同频率的单词，在堆里应该是字典序大的在前面，这样加到结果集里字典序小的在前面。
    // 综上分析。首先如果两个词频率相同的话，按字典顺序大的在前排序，否则就按频率小的在前排序。
    public List<String> topKFrequent1(String[] words, int k) {
        LinkedList<String> res = new LinkedList<>();
        if(words == null || words.length == 0 || k < 1) return res;
        Map<String, Integer> map = new HashMap<>();
        for(String word : words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue()? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            minHeap.offer(entry);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }
        while(!minHeap.isEmpty()) {
            res.addFirst(minHeap.poll().getKey());
        }
        return res;
    }


    //bucketsort time: O(N log m)m为每个bucket size
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if(words == null || words.length == 0 || k < 1) return res;
        Map<String, Integer> map = new HashMap<>();
        int maxCount = 0;
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
            maxCount = Math.max(maxCount, map.get(word));
        }
        List<String>[] bucket = new List[maxCount + 1];
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            int fre = entry.getValue();
            if(bucket[fre] == null) {
                bucket[fre] = new ArrayList<>();
            }
            bucket[fre].add(entry.getKey());
        }
        for(int i = maxCount, p = 0; i >= 0 && p < k; i--) {
            if(bucket[i] == null) continue;
            Collections.sort(bucket[i]);
            for(String w : bucket[i]) {
                res.add(w);
                if(++p == k) {
                    break;
                }
            }
        }
        return res;
    }
}
