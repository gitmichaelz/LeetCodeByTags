package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class MinimumNumberOfArrowsToBurstBalloons {
    //sort balloons by start, maintain a minimum end and once ballon[i][0] > minimal end, count++
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0 || points[0].length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));//(a, b) -> a[0] - b[0]
        int count = 1;
        int minEnd = points[0][1];
        for(int i = 1; i < points.length; i++){
            if(points[i][0] > minEnd){
                count++;
                minEnd = points[i][1];
            } else {
                minEnd = Math.min(minEnd, points[i][1]);
            }
        }
        return count;
    }
}
