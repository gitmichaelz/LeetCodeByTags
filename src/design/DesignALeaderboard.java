package design;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Design a Leaderboard class, which has 3 functions:
 *
 *     addScore(playerId, score): Update the leaderboard by adding score to the given player's score. If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
 *     top(K): Return the score sum of the top K players.
 *     reset(playerId): Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard). It is guaranteed that the player was added to the leaderboard before calling this function.
 *
 * Initially, the leaderboard is empty.
 *
 *
 *
 * Example 1:
 *
 * Input:
 * ["Leaderboard","addScore","addScore","addScore","addScore","addScore","top","reset","reset","addScore","top"]
 * [[],[1,73],[2,56],[3,39],[4,51],[5,4],[1],[1],[2],[2,51],[3]]
 * Output:
 * [null,null,null,null,null,null,73,null,null,null,141]
 */
public class DesignALeaderboard {
    Map<Integer, Integer> map;
    public DesignALeaderboard(){
        map = new HashMap<>();
    }

    public void addScore(int playerId, int score) {
        map.put(playerId, map.getOrDefault(playerId, 0) + score);
    }

    public int top(int K) {
        int[] A = map.values().stream().mapToInt(i -> i).toArray();

        return topKSum(A, 0, A.length - 1, K);
    }

    public void reset(int playerId) {
        map.put(playerId, 0);
    }

    private int topKSum(int[] nums, int left, int right, int K) {
        int random = new Random().nextInt(right - left + 1) + left;
        swap(nums, random, left);
        int pivot = nums[left];
        int start = left + 1, end = right;
        while(start <= end) {
            if(nums[end] < pivot) {
                swap(nums, start, end);
                start++;
            } else {
                end--;
            }
        }
        swap(nums, end, left);
        int m = right - end + 1;
        if(m == K) {
            int sum = 0;
            for(int i = end; i <= nums.length - 1; i++) {
                sum += nums[i];
            }
            return sum;
        } else if(m < K) {
            return topKSum(nums, left, end - 1, K - m);
        } else {
            return topKSum(nums, end + 1, right, K);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
