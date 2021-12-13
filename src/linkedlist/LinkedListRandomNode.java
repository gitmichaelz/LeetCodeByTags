package linkedlist;

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 *
 * Implement the Solution class:
 *
 *     Solution(ListNode head) Initializes the object with the integer array nums.
 *     int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be choosen.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 */
public class LinkedListRandomNode {
    //reservoir sampling: https://gregable.com/2007/10/reservoir-sampling.html
    //https://leetcode.com/problems/linked-list-random-node/discuss/85662/Java-Solution-with-cases-explain
    ListNode head;
    Random random;
    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        random = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {
        ListNode cur = head;
        int res = cur.val;
        for(int i = 1; cur.next != null; i++){
            cur = cur.next;
            if(random.nextInt(i + 1) == i) {
                res = cur.val;
            }
        }
        return res;
    }
}
