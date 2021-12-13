package design;

/**
 * Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 *
 * Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 *
 *     HitCounter() Initializes the object of the hit counter system.
 *     void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
 *     int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
 * [[], [1], [2], [3], [4], [300], [300], [301]]
 * Output
 * [null, null, null, null, 3, null, 4, 3]
 */
public class DesignHitCounter {
    class HitCounter {
        private int[] times;//circular array to store timestamp
        private int[] hits;//number of hits at i, where i = timestamp % 300
        /** Initialize your data structure here. */
        public HitCounter() {
            times = new int[300];
            hits = new int[300];
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int idx = timestamp % 300;
            if(times[idx] != timestamp) {//例如 ： 3 和 303，要落在同一个位置，这是要对hits[idx]重置为1. times[idx]更新为新的timestamp
                times[idx] = timestamp;
                hits[idx] = 1;
            } else {//同一时间来若干个hit
                hits[idx]++;
            }
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int count = 0;
            for(int i = 0; i < 300; i++){
                if(timestamp - times[i] < 300){
                    count += hits[i];
                }
            }
            return count;
        }
    }
}
