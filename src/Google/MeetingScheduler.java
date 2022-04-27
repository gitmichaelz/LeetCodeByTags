package Google;

import java.util.*;

public class MeetingScheduler {
    //因为每个人自己的slots不会有over lap, 必须两人之间的slots才有可能overlap, 所以可以把他们所有的slots都放进pq里找出符合的slot
    //why pq?因为要找最早的slot,为了满足duration需求，可以在加入的时候额外判断一下
    //TIME: O(M + N)*log(M + N)
    //SPACE: O(M + N)
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {//ensure all slots >= duration
                pq.offer(slot);
            }
        }

        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }

        while (pq.size() > 1) {//make sure at least 2 slots in pq
            if (pq.poll()[1] - pq.peek()[0] >= duration) {//这题的精髓在这里，因为pq.peek()slot的duration肯定大于给定的，所以只需要判断它和另外一个
                return Arrays.asList(pq.peek()[0], pq.peek()[0] + duration);//slot即可

            }
        }
        return new ArrayList<>();
    }
    //two pointer.
    // public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    //     Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
    //     Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
    //     for(int p1 = 0, p2 = 0; p1 < slots1.length && p2 < slots2.length; ) {
    //         int intersectLeft = Math.max(slots1[p1][0], slots2[p2][0]);
    //         int intersectRight = Math.min(slots1[p1][1], slots2[p2][1]);
    //         if(intersectRight - intersectLeft >= duration) {
    //             return Arrays.asList(intersectLeft, intersectLeft + duration);
    //         }
    //         if(slots1[p1][1] < slots2[p2][1]) {//always move the one that ends earlier
    //             p1++;
    //         } else {
    //             p2++;
    //         }
    //     }
    //     return new ArrayList<>();
    // }
}
