package graph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 *
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 *
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 */
public class ShortestPathWithAlternatingColors {
    //就是正常的BFS，只不过在求下一个点时，用到点小技巧。仔细看注释
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Set<Integer>[][] graph = new Set[2][n];
        for(int i = 0; i < n; i++) {
            graph[0][i] = new HashSet<>();//red edged graph
            graph[1][i] = new HashSet<>();//blue edged graph
        }
        for(int[] e : red_edges) {
            graph[0][e[0]].add(e[1]);
        }
        for(int[] e : blue_edges) {
            graph[1][e[0]].add(e[1]);
        }
        int[][] res = new int[2][n];
        for(int i = 1; i < n; i++) {
            res[0][i] = Integer.MAX_VALUE;//actually, it won't beyond 2 * n
            res[1][i] = Integer.MAX_VALUE;
        }

        Deque<int[]> queue = new LinkedList<>();//e[0]: node    e[1] color
        queue.offer(new int[]{0, 0});//node 0 could pick red
        queue.offer(new int[]{0, 1});//or blue
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            int color = cur[1];
            //we dont need to kee p track of level up to now
            //only need to keep what color the cur node pick and the length will be derived from previous node
            for(int next : graph[1 - color][node]) {//注意这里的巧妙之处。1 - color可以确保颜色是交替的.alternating
                if(res[1 - color][next] == Integer.MAX_VALUE) {//this node with this color not visited
                    res[1 - color][next] = res[color][node] + 1;//直接从上层节点中推导出当前节点的长度（从start开始的长度）
                    queue.offer(new int[]{next, 1 - color});
                }
            }
        }
        int[] ans = new int[n];
        for(int i = 0; i < n; i++) {
            ans[i] = Math.min(res[0][i], res[1][i]);
            if(ans[i] == Integer.MAX_VALUE) ans[i] = -1;
        }
        return ans;
    }


    //non-weighted edge, directed, we can do a simple bfs,
    //since the data set is relatively small, so we can use a adjacent matrix graph[][]
    //graph[i][j] = 1 red, -1, (blue), n (red and blue), 0(no color)
    //dont forget to check if nodes are visited, nodes picking same color are regarded as visited, if picking different color, its unvisited
    //our queue will store two info, 1. is node, 2. the color that node is picking, so for its next neighbor, we need pick a oppocolor or n.
//     public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
//         int[][] graph = new int[n][n];
//         buildGraph(graph, n, red_edges, blue_edges);
//         int[] res = new int[n];
//         Arrays.fill(res, Integer.MAX_VALUE);
//         res[0] = 0;
//         Deque<int[]> queue = new LinkedList<>();//e[0]: node, e[1]: the chosen color
//         queue.offer(new int[]{0, 1});
//         queue.offer(new int[]{0, -1});//the start node can either pick red or blue
//         int len = 0;//the distance from cur node to start node
//         Set<String> visited = new HashSet<>();
//         while(!queue.isEmpty()) {
//             len++;
//             for(int size = queue.size(); size > 0; size--) {
//                 int[] cur = queue.poll();
//                 int from = cur[0];
//                 int oppoColor = -cur[1];
//                 for(int i = 0; i < n; i++) {
//                     if(graph[from][i] == oppoColor || graph[from][i] == n) {
//                         if(!visited.add(i + " " + oppoColor)) continue;
//                         res[i] = Math.min(res[i], len);
//                         queue.offer(new int[]{i, oppoColor});
//                     }
//                 }
//             }
//         }
//         for(int i = 0; i < n; i++) {
//             if(res[i] == Integer.MAX_VALUE) {//not visited/reached from start
//                 res[i] = -1;
//             }
//         }
//         return res;
//     }

//     private void buildGraph(int[][] graph, int n, int[][] red_edges, int[][] blue_edges) {
//         for(int[] e : red_edges) {
//             int from = e[0];
//             int to = e[1];
//             graph[from][to] = 1;
//         }
//         for(int[] e : blue_edges) {
//             int from = e[0];
//             int to = e[1];
//             if(graph[from][to] == 1) {
//                 graph[from][to] = n;
//             } else {
//                 graph[from][to] = -1;
//             }
//         }
//     }
}
