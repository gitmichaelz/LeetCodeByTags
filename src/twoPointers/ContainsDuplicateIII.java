package twoPointers;


import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and two integers k and t, return true if there are two distinct indices i and j in the array such that abs(nums[i] - nums[j]) <= t and abs(i - j) <= k.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 *
 * Example 2:
 *
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 */
public class ContainsDuplicateIII {
    //similar to bucket sort, bucket size is t + 1(because if b - a = t, if bucket holdes a and b then bucket must be at lest t + 1, 2 - 1 = 1, size >= 1 + 1 = 2)
    //Suppose we have consecutive buckets covering the range of nums with each bucket a width of (t+1). If there are two item with difference <= t, one of the two will happen:

    //(1) the two in the same bucket
    //(2) the two in neighbor buckets
    //we use a map, key = num/width, if two element has same key, means they fall into same bucket, return true, or if they fall into neighbori buckets, return true.
    //we make sure the map size is always <= k during the iteration
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t){
        if(nums == null || nums.length < 2 || k < 1 || t < 0) return false;
        Map<Long, Long> bucket = new HashMap<>();//with size k, if size >= k, remove the i - kth element
        long width = (long)t + 1;
        for(int i = 0; i < nums.length; i++){
            long remappedNum = (long)nums[i] - Integer.MIN_VALUE;//we can assure remappedNum > 0 by doing this way
            long id = remappedNum / width;
            if(bucket.containsKey(id)) return true;
            if(bucket.containsKey(id - 1) && Math.abs(remappedNum - bucket.get(id - 1)) <= t) return true;
            if(bucket.containsKey(id + 1) && Math.abs(remappedNum - bucket.get(id + 1)) <= t) return true;

            bucket.put(id, remappedNum);
            if(i >= k) bucket.remove(((long)nums[i - k] - Integer.MIN_VALUE)/width);//remove(id),
        }
        return false;
    }

    // private long getId(long num, long width){
    //     return num < 0? (num + 1)/width - 1 : num/width;
    // }
    // public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t){
    //     if(nums == null || nums.length < 2 || k < 1 || t < 0) return false;
    //     Map<Long, Long> bucket = new HashMap<>();//with size k, if size >= k, remove the i - kth elements
    //     long width = (long)t + 1;
    //     for(int i = 0; i < nums.length; i++){
    //         long id = getId(nums[i], width);
    //         if(bucket.containsKey(id)) return true;
    //         if(bucket.containsKey(id - 1) && Math.abs(nums[i] - bucket.get(id - 1)) <= t){
    //             return true;
    //         }
    //         if(bucket.containsKey(id + 1) && Math.abs(nums[i] - bucket.get(id + 1)) <= t){
    //             return true;
    //         }
    //         bucket.put(id, (long)nums[i]);
    //         if(i >= k) bucket.remove(getId(nums[i - k], width));
    //     }
    //     return false;
    // }





    //maintain a window
//     public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//         if(nums == null || nums.length < 2 || k < 1) return false;
//         TreeSet<Long> set = new TreeSet<>();//long 可以避免证书溢出问题，例如[-1, 2147483647], 1, 2147483647, 应该返回false,如果不用long,则会返回true
//         for(int i = 0; i < nums.length; i++) {
//             long num = nums[i];
//             Long floor = set.floor(num);//Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
//             Long ceil = set.ceiling(num);//Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
//             if(floor != null && num - floor <= t ||
//               ceil != null && ceil - num <= t) {
//                 return true;
//             }

//             set.add(num);
//             if(i >= k) {
//                 set.remove((long)nums[i - k]);
//             }
//         }
//         return false;
//     }
}
