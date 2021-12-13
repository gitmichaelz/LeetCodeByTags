package design;

import java.util.*;

/**
 * RandomizedCollection is a data structure that contains a collection of numbers, possibly duplicates (i.e., a multiset). It should support inserting and removing specific elements and also removing a random element.
 *
 * Implement the RandomizedCollection class:
 *
 *     RandomizedCollection() Initializes the empty RandomizedCollection object.
 *     bool insert(int val) Inserts an item val into the multiset, even if the item is already present. Returns true if the item is not present, false otherwise.
 *     bool remove(int val) Removes an item val from the multiset if present. Returns true if the item is present, false otherwise. Note that if val has multiple occurrences in the multiset, we only remove one of them.
 *     int getRandom() Returns a random element from the current multiset of elements. The probability of each element being returned is linearly related to the number of same values the multiset contains.
 *
 * You must implement the functions of the class such that each function works on average O(1) time complexity.
 *
 * Note: The test cases are generated such that getRandom will only be called if there is at least one item in the RandomizedCollection.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
 * [[], [1], [1], [2], [], [1], []]
 * Output
 * [null, true, false, true, 2, true, 1]
 */
public class InsertDeleteGetRandom_O1_DuplicatesAllowed {
    class RandomizedCollection {
        Random random;
        Map<Integer, LinkedHashSet<Integer>> map;//we use a linkedHashSet which have predictable iteration order(insertion order)
        List<Integer> list;
        /** Initialize your data structure here. */
        public RandomizedCollection() {
            random = new Random();
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
        public boolean insert(int val) {
            boolean inserted = map.containsKey(val);
            if(!inserted) {
                map.put(val, new LinkedHashSet<>());
            }
            map.get(val).add(list.size());
            list.add(val);
            return !inserted;//注意这里是 !inserted
        }

        /** Removes a value from the collection. Returns true if the collection contained the specified element. */
        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }
            int index = map.get(val).iterator().next();
            map.get(val).remove(index);

            if(index < list.size() - 1){
                int lastElement = list.get(list.size() - 1);
                list.set(index, lastElement);
                map.get(lastElement).add(index);//最后这两个一定不要忘记，是更新map里的lastElement所对应的角标
                map.get(lastElement).remove(list.size() - 1);
            }
            list.remove(list.size() - 1);
            if(map.get(val).isEmpty()) {
                map.remove(val);
            }
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }
}
