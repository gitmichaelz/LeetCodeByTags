package math;

/**
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.
 *
 * If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 *     Fill any of the jugs with water.
 *     Empty any of the jugs.
 *     Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
 *
 *
 *
 * Example 1:
 *
 * Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
 * Output: true
 * Explanation: The famous Die Hard example
 */
public class WaterAndJugProblem {
    //https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution
    public boolean canMeasureWater(int x, int y, int z) {
        //limit brought by the statement that water is finallly in one or both buckets
        if(x + y < z) return false;
        //case x or y is zero
        if( x == z || y == z || x + y == z ) return true;

        //get GCD, then we can use the property of Bézout's identity
        return z%GCD(x, y) == 0;
    }

    public int GCD(int a, int b){
        while(b != 0 ){
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
    }
}
