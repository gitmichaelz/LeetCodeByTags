package binarySearch;

/**
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 */
public class KoKoEatingBananas {
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = 0;
        for(int i = 0; i < piles.length; i++) {
            right = Math.max(right, piles[i]);
        }
        while(left <= right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int i = 0; i < piles.length; i++) {
                //需要的时间，凑整
                count += (piles[i] + mid - 1) / mid;
            }
            if(count > H)   //K too small
                left = mid + 1;
            else
                right = mid - 1;
        }
        return left;
    }
}
