package binarySearch;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
class VersionControl {
    boolean isBadVersion(int version) {
        return version > 0;
    }
}
public class FirstBadVersion extends VersionControl {
    //二分，找下界
    public int firstBadVersion(int n){
        int lo = 1, hi = n;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(isBadVersion(mid)){
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return n == 1 && !isBadVersion(lo)? -1 : lo;//一定要判断n == 1,否则不会进循环
    }
}
