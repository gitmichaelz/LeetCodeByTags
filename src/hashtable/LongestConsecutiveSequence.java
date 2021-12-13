package hashtable;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
 *
 * You must write an algorithm that runs in O(n) time.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [100,4,200,1,3,2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 * Example 2:
 *
 * Input: nums = [0,3,7,2,5,8,4,6,0,1]
 * Output: 9
 */
public class LongestConsecutiveSequence {
    //approach 1>
    //use a map to store elements and their counts
    //once we meet an element in array, we check the counts of (elements - 1) and counts of (elements + 1),
    //so current consecutive sequence including element's length would be count down + count up + 1, also we update our
    //global best during the process
    // public int longestConsecutive(int[] nums) {
    //     if(nums == null || nums.length == 0) return 0;
    //     Map<Integer, Integer> counts = new HashMap<>();
    //     int res = 0;
    //     for(int num: nums) {
    //         if(counts.containsKey(num)) continue;//必须dedupe,因为我们前面如果处理过的话，边界已经计算一次了，如果再来一次，边界会再次改变
    //         int countUp = counts.getOrDefault(num + 1, 0);
    //         int countDown = counts.getOrDefault(num - 1, 0);
    //         int count = countDown + countUp + 1;
    //         counts.put(num, count);
    //         counts.put(num + countUp, count);//update upper bound
    //         counts.put(num - countDown, count);//update lower bound
    //         res = Math.max(res, count);
    //     }
    //     return res;
    // }

    // approach 2>
    // or we can use a set, we put all elements into a set, and for each elements,
    // we check if element + (1...N) is in the set, update the max Length,
    // note we only check one direction, if set contains(element - 1), we just skip it
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums) {
            set.add(num);
        }
        int res = 0;
        for(int num : set){
            if(set.contains(num - 1)) continue;
            int count = 1;
            while(set.contains(num + 1)) {
                count++;
                num++;
            }
            res = Math.max(res, count);
        }
        return res;
    }



    //这题用前两种方法即可，Union Find有点杀鸡用牛刀，而且不如set方法快
    //approach 3> Union find
    //用一个map存nums[i], i, 对于某个nums[i], 我们判断是否nums[i] - 1 或者nums[i] + 1在map里存在，如果存在，就把他们的坐标union一下，为什么不把数字nums[i]union一下？因为元素有可能太大，数组长度不确定，所以对坐标进行操作
//     class UF{
//         int[] parents;
//         public UF(int n){
//             parents = new int[n];
//             for(int i = 0; i < n; i++) {
//                 parents[i] = i;
//             }
//         }
//         private int getRoot(int i) {
//             while(i != parents[i]) {
//                 parents[i] = parents[parents[i]];//path compression 路径压缩
//                 i = parents[i];
//             }
//             return i;
//         }

//         public void union(int i, int j) {
//             int root1 = getRoot(i);
//             int root2 = getRoot(j);
//             parents[root1] = root2;
//         }

//         public boolean isConnected(int i, int j) {
//             return getRoot(i) == getRoot(j);
//         }

//         public int getMaxUnion() {//O(n) return the maximum size of union set
//             int[] count = new int[parents.length];
//             int max = 0;
//             for(int i = 0; i < parents.length; i++) {
//                 int root = getRoot(i);
//                 count[root]++;
//                 max = Math.max(max, count[root]);
//             }
//             return max;
//         }
//     }
//     public int longestConsecutive(int[] nums) {
//         if(nums == null || nums.length == 0) return 0;
//         UF uf = new UF(nums.length);
//         Map<Integer, Integer> map = new HashMap<>();//store elements and their indices
//         for(int i = 0; i < nums.length; i++) {
//             if(map.containsKey(nums[i])) continue;//必须dedup,否则会把该出的index视为一个序列的新元素，让整个序列变长，事实上是错误的
//             map.put(nums[i], i);
//             Integer left = map.get(nums[i] - 1);
//             if(left != null) {
//                 uf.union(i, left);
//             }
//             Integer right = map.get(nums[i] + 1);
//             if(right != null) {
//                 uf.union(i, right);
//             }
//         }
//         return uf.getMaxUnion();
//     }

}
