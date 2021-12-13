package heap_priorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration duration, return the earliest time slot that works for both of them and is of duration duration.
 *
 * If there is no common time slot that satisfies the requirements, return an empty array.
 *
 * The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
 *
 * It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
 *
 *
 *
 * Example 1:
 *
 * Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
 * Output: [60,68]
 */
public class MeetingScheduler {
    //pq, time: (m + n)* log(m + n), space : m + n
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for(int[] slot : slots1) {
            if(slot[1] - slot[0] >= duration) {//ensure all slots >= duration
                pq.offer(slot);
            }
        }

        for(int[] slot : slots2) {
            if(slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }

        while(pq.size() > 1) {//这里是 > 1, 即确保有两个元素在里面
            if(pq.poll()[1] - pq.peek()[0] >= duration) {
                return Arrays.asList(pq.peek()[0], pq.peek()[0] + duration);
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
