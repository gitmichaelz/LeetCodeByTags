package design;

import java.util.TreeMap;

/**
 * A Range Module is a module that tracks ranges of numbers. Design a data structure to track the ranges represented as half-open intervals and query about them.
 *
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 *
 * Implement the RangeModule class:
 *
 *     RangeModule() Initializes the object of the data structure.
 *     void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval. Adding an interval that partially overlaps with currently tracked numbers should add any numbers in the interval [left, right) that are not already tracked.
 *     boolean queryRange(int left, int right) Returns true if every real number in the interval [left, right) is currently being tracked, and false otherwise.
 *     void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RangeModule", "addRange", "removeRange", "queryRange", "queryRange", "queryRange"]
 * [[], [10, 20], [14, 16], [10, 14], [13, 15], [16, 17]]
 * Output
 * [null, null, null, true, false, true]
 */
public class RangeModule {
    TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    //这个主要就是把left和right之间的已存在的range从map中删掉，然后加入新插入的区间
    public void addRange(int left, int right) {
        if(left >= right) return;
        Integer start = map.floorKey(left);//the greatest key which is <= left
        if(start == null) start = map.ceilingKey(left);//the least key which is >= left
        while(start != null && start <= right) {//注意，这里是<=right,因为我们要把left 和 right之间的range去掉
            Integer end = map.get(start);
            if(end >= left) {
                map.remove(start);
                if(start < left) left = start;
                if(end > right) right = end;
            }
            start = map.ceilingKey(end);//the least key which is >= end
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer floor = map.floorKey(left);
        return floor != null && map.get(floor) >= right;
    }

    public void removeRange(int left, int right) {
        if(left >= right) return;
        Integer start = map.floorKey(left);
        if(start == null) start = map.ceilingKey(left);
        while(start != null && start < right) {//注意这里不带=,因为我们要移除的range是[)
            Integer end = map.get(start);
            if(end >= left) {
                map.remove(start);
                if(start < left) map.put(start, left);
                if(end > right) map.put(right, end);
            }
            start = map.ceilingKey(end);
        }
    }
}
