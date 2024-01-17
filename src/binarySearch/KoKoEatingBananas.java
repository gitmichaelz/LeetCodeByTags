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
        int min = 1, max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        //二分，最慢一小时吃一根，最快一小时吃最大堆所有根。在这个区间里面去试getHours(),
        while (min < max) {
            int mid = min + (max - min) / 2;
            if (getHours(piles, mid) <= H) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    private int getHours(int[] piles, int speed) {
        int hours = 0;
        for (int bananas : piles) {
            hours += Math.ceil(1.0 * bananas / speed);
        }
        return hours;
    }
}
