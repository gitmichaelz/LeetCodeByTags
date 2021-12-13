package tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 *
 * Example 2:
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
 * Output: false
 */
public class GraphValidTree {
    //这个题UnionFind最快。但三种方法都要掌握
    //union find, for adjacent nodes, we union them and make them same root, but if we already find they have same root
    //before the union operation, that means we find a cycle,  return false
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for(int[] edge : edges) {
            int root1 = findRoot(parent, edge[0]);
            int root2 = findRoot(parent, edge[1]);
            if(root1 == root2) return false;
            parent[root1] = root2;
            n--;//消灭一条边
        }
        return n == 1;//n个点，如果是树的话，应该有n - 1条边。所以如果消灭n - 1条边，最后n = 1
    }

    private int findRoot(int[] parent, int i) {
        while(i != parent[i]) {
            i = parent[parent[i]];
        }
        return i;
    }





    //build graph, then bfs/dfs traverse graph, find if any nodes visited twice.
    //also we will detect if they are all connected
    public boolean validTreeBFS(int n, int[][] edges) {
        Set<Integer>[] graph = new Set[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new HashSet();
        }
        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        //detect cycle
        Set<Integer> visited = new HashSet<>();//mark visited nodes, if we meet a visited node, return false
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        while(!queue.isEmpty()) {
            int curNode = queue.poll();
            if(!visited.add(curNode)) return false;//already visited curNode

            for(int neighbor : graph[curNode]) {
                queue.offer(neighbor);
                graph[neighbor].remove(curNode);//这一步很重要，避免重复访问节点
            }
        }

        return visited.size() == n;//check if fully connected, if not, visited.size() != n
    }



    //DFS
    public boolean validTreeDFS(int n, int[][] edges) {
        Set<Integer>[] graph = new Set[n];
        for(int i = 0; i < n; i++) {
            graph[i] = new HashSet();
        }
        for(int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        //check cycle
        if(!dfs(graph, visited, 0, -1)) return false;
        return visited.size() == n;
    }
    //we use 'parent' node to skip it, avoid repetitive visit
    private boolean dfs(Set<Integer>[] graph, Set<Integer> visited, int curNode, int parent) {
        visited.add(curNode);
        for(int neighbor : graph[curNode]) {
            if(neighbor == parent) continue;
            if(visited.contains(neighbor) || !dfs(graph, visited, neighbor, curNode)) return false;
        }
        return true;
    }
}
