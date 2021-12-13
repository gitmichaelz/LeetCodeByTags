package bfs;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 *
 *     For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 *
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 *
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 *
 *
 * Example 1:
 *
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 */
public class BusRoutes {
    //bfs
    //首先建图，节点是stop,边是routes(即通过该stop的bus)
    //然后从source出发，找通过source的所有routes(bus), 然后根据bus,找出bus能达到的所有stop
    //注意，需要用skip掉已访问过的bus和stop，busesUsed来存储访问过的bus,这样这辆bus所经过的stop都会处理过了，无需访问。也不用单独标记某个stop是否访问过，只需要标记访问过的bus即可！！！
    //经同一bus能到达的所有stop处于同一层，每一次搜索本层所有为未访问过的stop, 知道目的地，返回所在层数即可
    public int numBusesToDestination1(int[][] routes, int source, int target) {
        if(source == target) return 0;//no bus needed
        Map<Integer, List<Integer>> stop_buses = new HashMap<>();
        for(int i = 0; i < routes.length; i++) {
            for(int stops : routes[i]) {
                List<Integer> buses = stop_buses.computeIfAbsent(stops, k -> new ArrayList<>());
                buses.add(i);
            }
        }
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        boolean[] busesUsed = new boolean[routes.length];
        int level = 0;
        while(!queue.isEmpty()) {
            level++;
            for(int size = queue.size(); size > 0; size--) {
                int curStop = queue.poll();
                for(int bus : stop_buses.get(curStop)) {
                    if(busesUsed[bus]) continue;
                    busesUsed[bus] = true;
                    for(int stop : routes[bus]) {
                        if(stop == target) return level;
                        queue.offer(stop);
                    }
                }
            }
        }
        return -1;
    }





    //对上述方法的小优化。queue里存bus, 而不是stop.
    //22ms beat 99.68%
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;//no bus needed
        Map<Integer, List<Integer>> stop_buses = new HashMap<>();
        for(int i = 0; i < routes.length; i++) {
            for(int stops : routes[i]) {
                List<Integer> buses = stop_buses.computeIfAbsent(stops, k -> new ArrayList<>());
                buses.add(i);
            }
        }
        if(!stop_buses.containsKey(source) || !stop_buses.containsKey(target)) return -1;
        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] busesVisited = new boolean[routes.length];
        for(int bus : stop_buses.get(source)) {
            queue.offer(bus);
            busesVisited[bus] = true;
        }
        int level = 1;
        while(!queue.isEmpty()) {
            for(int size = queue.size(); size > 0; size--) {
                int curBus = queue.poll();
                //traverse all the stops
                for(int stop : routes[curBus]) {
                    if(stop == target) return level;
                    //search in all the possible buses
                    for(int bus : stop_buses.get(stop)) {
                        if(busesVisited[bus]) continue;
                        busesVisited[bus] = true;
                        queue.offer(bus);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
