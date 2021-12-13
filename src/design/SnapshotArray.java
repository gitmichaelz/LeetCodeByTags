package design;

import java.util.TreeMap;

/**
 * Implement a SnapshotArray that supports the following interface:
 *
 *     SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
 *     void set(index, val) sets the element at the given index to be equal to val.
 *     int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
 *     int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
 *
 *
 *
 * Example 1:
 *
 * Input: ["SnapshotArray","set","snap","set","get"]
 * [[3],[0,5],[],[0,6],[0,0]]
 * Output: [null,null,0,null,5]
 */
public class SnapshotArray {
    //这种方法更简洁，但时间稍慢点 40ms
    TreeMap<Integer, Integer>[] A;
    int snap_id = 0;
    public SnapshotArray(int length) {
        A = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            A[i] = new TreeMap<Integer, Integer>();
            A[i].put(0, 0);
        }
    }

    public void set(int index, int val) {
        A[index].put(snap_id, val);
    }

    public int snap() {
        return snap_id++;
    }

    public int get(int index, int snap_id) {
        return A[index].floorEntry(snap_id).getValue();
    }
    //这个方法更快一点， 24ms
//     ArrayList<ArrayList<Integer>> snapshotArray;
//     int snapCount = 0;

//     public SnapshotArray(int length) {
//         snapshotArray = new ArrayList<ArrayList<Integer>>(length);
//         for (int i = 0; i < length; i++) {
//             snapshotArray.add(new ArrayList<Integer>());
//         }
//         snapCount = 0;
//     }

//     public void set(int index, int val) {
//         ArrayList<Integer> subAry = snapshotArray.get(index);
//         Integer recentValue = (subAry.size() == 0) ? 0 : subAry.get(subAry.size()-1);
//         for (int i = subAry.size()-1; i < snapCount; i++) {
//             subAry.add(recentValue);
//         }
//         subAry.set(snapCount, val);
//     }

//     public int snap() {
//         return snapCount++;
//     }

//     public int get(int index, int snap_id) {
//         ArrayList<Integer> subAry = snapshotArray.get(index);
//         if (subAry.size() == 0)
//             return 0;
//         if (snap_id >= subAry.size())
//             return subAry.get(subAry.size()-1);
//         return subAry.get(snap_id);
//     }
}
