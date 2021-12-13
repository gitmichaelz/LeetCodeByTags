package bfs;

import java.util.*;

public class CheapestFlightsWithinKStops {
    //优化.用一个visited map记录visited的节点和该节点属于从开始到现在第几个stop
    //如果当前节点没被访问过，可以入队
    //如果当前节点被访问过，并且之前访问过的时候到它为止所经历的stop大于当前到它所经历的stop,也可以入队。因为之前计算的时候，肯定是不满足(比如受限于stop过多不满足要求)条件而通过其他方式(更少的stop,但cost会更多)又走到
    //该节点。这时我们是可以入队的。但是，如果之前访问过的时候到它位置所经历的stop小于当前到它所经历的stop,说明之前的行不通，到现在更行不通(cost小，stop也小)，所以这时没必要入队。
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//build graph, use List<int[]> [] instead of map
        List<int[]>[] graph = new List[n];
        for(int[] flight : flights){
            int start = flight[0];
            int des = flight[1];
            int price = flight[2];
            if(graph[start] == null){
                graph[start] = new ArrayList<>();//注意这个写法，第一遍手生写错了
            }
            graph[start].add(new int[] {des, price});
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[] {0, src, 0});//prices, src, stops
        Map<Integer, Integer> visited = new HashMap<>();//visited city, stops
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int price = cur[0];
            int curDst = cur[1];
            int stops = cur[2];
            if(curDst == dst) return price;
            if((!visited.containsKey(curDst) || visited.get(curDst) > stops) && graph[curDst] != null && stops <= k) {
                visited.put(curDst, stops);
                for(int[] neighbor : graph[curDst]){
                    pq.offer(new int[] {neighbor[1] + price, neighbor[0],stops + 1});
                }
            }
        }
        return -1;
    }






    //这是标准的Dijkstra, 会TLE
    //带权重的有向边的图最短路径问题 dijkstra求最短路径
    //dijkstra与普通BFS的区别，普通BFS用一个Queue即可。dijkstra要用pq，pq里存的是从起始点到当前点的最短路径距离以及当前节点。pq按照路径距离的长短
    //排序，这样每次都poll()最短距离的点，如果碰到目的地，就可以保证从原点到该点的距离是最短的。
    public int findCheapestPriceTLE(int n, int[][] flights, int src, int dst, int k) {
        List<int[]>[] graph = new List[n];
        for(int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int price = flight[2];
            if(graph[from] == null) {
                graph[from] =  new ArrayList<>();
            }
            graph[from].add(new int[]{to, price});
        }
        if(graph[src] == null) return -1;//if src has no destination, return -1;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, src, 0});//total costs from src till curNode, curNode, stops
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int curCity = cur[1];
            int stops = cur[2];
            if(curCity == dst) return cost;
            if(stops <= k && graph[curCity] != null) {
                for(int[] next : graph[curCity]) {
                    pq.offer(new int[] {cost + next[1], next[0], stops + 1});
                }
            }
        }
        return -1;
    }
}
