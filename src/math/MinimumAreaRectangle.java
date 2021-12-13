package math;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] p: points){
            if(!map.containsKey(p[0])) map.put(p[0], new HashSet<>());
            map.get(p[0]).add(p[1]);
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < points.length; i++){
            int[] p1 = points[i];
            for(int j = i+1; j < points.length; j++){
                int[] p2 = points[j];
                if(p1[0] == p2[0] || p1[1] == p2[1]) continue;
                int temp = Math.abs(p1[0] - p2[0])*Math.abs(p1[1] - p2[1]);
                if(temp >= res) continue;
                if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
                    res = temp;
                }

            }
        }
        return res== Integer.MAX_VALUE?0:res;
    }
}
