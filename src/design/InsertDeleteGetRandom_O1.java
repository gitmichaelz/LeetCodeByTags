package design;

import java.util.*;

/**
 * Implement the RandomizedSet class:
 *
 *     RandomizedSet() Initializes the RandomizedSet object.
 *     bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
 *     bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
 *     int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
 *
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 */
public class InsertDeleteGetRandom_O1 {
    class RandomizedSet {
        Random random;
        Map<Integer, Integer> map;
        List<Integer> list;
        /** Initialize your data structure here. */
        public RandomizedSet() {
            random = new Random();
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }
            map.put(val, list.size());
            return list.add(val);//list.add() 返回值是boolean, 如果没有duplicate, return true
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }
            int index = map.remove(val);
            if(index < list.size() - 1){//not the last one
                int lastElement = list.get(list.size() - 1);
                list.set(index, lastElement);
                map.put(lastElement, index);//update map
            }
            list.remove(list.size() - 1);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
