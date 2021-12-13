package graph;

/**
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 *
 *     There are no self-edges (graph[u] does not contain u).
 *     There are no parallel edges (graph[u] does not contain duplicate values).
 *     If v is in graph[u], then u is in graph[v] (the graph is undirected).
 *     The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 *
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 *
 *
 * Example 1:
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 */
public class IsGraphBipartite {
    /*
        use two colors to color the graph and see if there are any adjacent nodes having the same color.
        Initialize a color[] array for each node. Here are three states for colors[] array:
        0: Haven't been colored yet.
        1: Blue.
        -1: Red.
     */
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for(int i = 0; i < n; i++) {
            if(colors[i] == 0 && !validColor(graph, colors, i, 1)) {//check every unvisited node and color it and its neighbors
                return false;
            }
        }
        return true;
    }

    private boolean validColor(int[][] graph, int[] colors, int node, int color) {
        colors[node] = color;
        for(int neighbor : graph[node]) {
            if (colors[neighbor] == -color) continue;
            if (colors[neighbor] == color || !validColor(graph, colors, neighbor, -color)) {//same color or unvisited node but cannot color its neighbors recursively
                return false;
            }
        }
        return true;
    }

    //BFS
    /*
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for(int i = 0; i < n; i++) {
            if(colors[i] != 0) continue;
            colors[i] = 1;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            while(!queue.isEmpty()) {
                int cur = queue.poll();
                for(int neighbor : graph[cur]) {
                    if(colors[neighbor] == 0) {//if this node has not been colored, color it with differnt color
                        colors[neighbor] = -colors[cur];
                        queue.offer(neighbor);
                    } else if(colors[neighbor] == colors[cur]){//if cur node and neighbor have same color, return false
                        return false;
                    }
                }
            }
        }
        return true;
    }
    */
}
