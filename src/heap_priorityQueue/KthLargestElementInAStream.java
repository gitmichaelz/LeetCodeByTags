package heap_priorityQueue;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    PriorityQueue<Integer> pq;
    int k;
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>(k);
        for(int n: nums){
            add(n);
        }
    }

    public int add(int val) {
        if(pq.size() < k){
            pq.offer(val);
        } else if(pq.peek() < val) {//或者比较懒的方式， else {pq.offer(), pq.offer(val)}
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }
}
