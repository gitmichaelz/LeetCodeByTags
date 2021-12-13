package bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

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
