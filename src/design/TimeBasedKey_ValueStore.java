package design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 *
 *     TimeMap() Initializes the object of the data structure.
 *     void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
 *     String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["TimeMap", "set", "get", "get", "set", "get", "get"]
 * [[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
 * Output
 * [null, null, "bar", "bar", null, "bar2", "bar2"]
 */
public class TimeBasedKey_ValueStore {
    class Data {
        String val;
        int time;
        Data(String val, int time) {
            this.val = val;
            this.time = time;
        }
    }
    class TimeMap {

        /** Initialize your data structure here. */
        Map<String, List<Data>> map;
        public TimeMap() {
            map = new HashMap<String, List<Data>>();
        }

        public void set(String key, String value, int timestamp) {
            if (!map.containsKey(key)) map.put(key, new ArrayList<Data>());
            map.get(key).add(new Data(value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            return binarySearch(map.get(key), timestamp);
        }

        protected String binarySearch(List<Data> list, int time) {
            int low = 0, high = list.size() - 1;
            while (low < high) {
                int mid = (low + high) >> 1;
                if (list.get(mid).time == time) return list.get(mid).val;
                if (list.get(mid).time < time) {
                    if (list.get(mid+1).time > time) return list.get(mid).val;
                    low = mid + 1;
                }
                else high = mid -1;
            }
            return list.get(low).time <= time ? list.get(low).val : "";
        }
    }
}
