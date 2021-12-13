package array_matrix_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.
 *
 * Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).
 *
 *
 *
 * Example 1:
 *
 * Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));//如果两人身高相同，按前面的的人数多少排，否则按高的在前面，
        List<int[]> res = new ArrayList<>();
        for (int[] p : people) {
            res.add(p[1], p);//插入的时候，按照前面几个人来插，比如7前面有0个，则先add(7, 0)在0位置, 后面是(5，0)的话，则add(5, 0)在0位置
        }
        return res.toArray(new int[res.size()][]);
    }
}
