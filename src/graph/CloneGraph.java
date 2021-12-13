package graph;

import dfs_backtracking_Recursion.Node;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a reference of a node in a connected undirected graph.
 *
 * Return a deep copy (clone) of the graph.
 *
 * Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
 *
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 *
 *
 *
 * Test case format:
 *
 * For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.
 *
 * An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.
 *
 * The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.
 */
public class CloneGraph {
    //这题BFS DFS都要掌握
    //BFS
    public Node cloneGraphBFS(Node node) {
        if(node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            for(Node neighbor : cur.neighbors){
                if(!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                map.get(cur).neighbors.add(map.get(neighbor));//建立adjacent关系。重要，不要忘记这一步。注意是在if外面。这个关系的建立是在
                //cur.neighbors的前提下的。
            }
        }
        return map.get(node);
    }

    //DFS
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        return deepCopy(node, map);
    }

    private Node deepCopy(Node node, Map<Node, Node> map) {
        if(map.containsKey(node)) return map.get(node);
        map.put(node, new Node(node.val));
        for(Node neighbor : node.neighbors) {
            map.get(node).neighbors.add(deepCopy(neighbor, map));//add deep copy of node's neighbors to newNode's neighbors
        }
        return map.get(node);
    }
}
