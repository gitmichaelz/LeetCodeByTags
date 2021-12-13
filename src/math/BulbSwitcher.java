package math;

/**
 * There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
 *
 * On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb.
 *
 * Return the number of bulbs that are on after n rounds.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 1
 * Explanation: At first, the three bulbs are [off, off, off].
 * After the first round, the three bulbs are [on, on, on].
 * After the second round, the three bulbs are [on, off, on].
 * After the third round, the three bulbs are [on, off, off].
 * So you should return 1 because there is only one bulb is on.
 */
public class BulbSwitcher {
    //A light will be toggled only during the round of its factors, e.g. number 6 light will be toggled at 1,2,3,6 and light 12 will be toggled at 1,2,3,4,6,12.
    //The final state of a light is on and off only depends on if the number of its factor is odd or even. If odd, the light is on and if even the light is off.
    //The number of one number's factor is odd if and only if it is a perfect square!
    //So we will only need to loop to find all the perfect squares that are smaller than n!
    public int bulbSwitch(int n){
        //or just return Math.sqrt(n);
        int count = 0;
        for(int i = 1; i * i <= n; i++){
            count++;
        }
        return count;
    }
}
