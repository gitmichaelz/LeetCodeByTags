package string;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class RearrangeStringKDistanceApart {
    //The greedy algorithm is that in each step, select the char with highest remaining count if possible (if it is not in the waiting queue). PQ is used to achieve the greedy. A regular queue waitQueue is used to "freeze" previous appeared char in the period of k.

    //In each iteration, we need to add current char to the waitQueue and also release the char at front of the queue, put back to maxHeap. The "impossible" case happens when the maxHeap is empty but there is still some char in the waitQueue.
    public String rearrangeString(String s, int k) {
        if(k <= 1) return s;
        int[] count = new int[26];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        Deque<int[]> queue = new LinkedList<>();
        for(char c : s.toCharArray()){
            count[c - 'a']++;
        }
        for(int i = 0; i < count.length; i++){
            if(count[i] != 0){
                pq.offer(new int[]{i, count[i]});
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            sb.append((char)(cur[0] + 'a'));
            cur[1]--;
            queue.offer(cur);
            while(queue.size() == k){//when the waitQueue has previous K chars, release the one from  head
                int[] head = queue.poll();
                if(head[1] > 0) pq.offer(head);
            }
        }
        return sb.length() == s.length()? sb.toString() : "";
    }
}
