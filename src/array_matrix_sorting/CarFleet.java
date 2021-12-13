package array_matrix_sorting;

import java.util.Arrays;

/**
 * There are n cars going to the same destination along a one-lane road. The destination is target miles away.
 *
 * You are given two integer array position and speed, both of length n, where position[i] is the position of the ith car and speed[i] is the speed of the ith car (in miles per hour).
 *
 * A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same speed. The faster car will slow down to match the slower car's speed. The distance between these two cars is ignored (i.e., they are assumed to have the same position).
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 * Return the number of car fleets that will arrive at the destination.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * Output: 3
 */
public class CarFleet {
    /**
     Sort cars by the start positions pos
     Loop on each car from the end to the beginning
     Calculate its time needed to arrive the target
     cur records the current biggest time (the slowest)

     If another car needs less or equal time than cur,
     it can catch up this car fleet.

     If another car needs more time,
     it will be the new slowest car,
     and becomes the new lead of a car fleet.
     */
    public int carFleet(int target, int[] position, int[] speed) {
        if(position == null || position.length == 0 || speed == null || speed.length == 0) return 0;
        int n = position.length;
        int[][] cars = new int[n][2];
        for(int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }
        Arrays.sort(cars, (a, b) -> b[0] - a[0]);//距离target由远及近排列

        int res = 0;
        double cur = 0;//the cur slowest car
        for(int i = 0; i < n; i++) {
            double local = (double)(target - cars[i][0])/cars[i][1];
            if(local > cur) {
                cur = local;
                res++;
            }
        }
        return res;
    }
}
