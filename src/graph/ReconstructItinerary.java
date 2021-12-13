package graph;

import java.util.*;

/**
 * You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.
 *
 * All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.
 *
 *     For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
 *
 * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 *
 *
 *
 * Example 1:
 *
 * Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * Output: ["JFK","MUC","LHR","SFO","SJC"]
 */
public class ReconstructItinerary {
    //这个题是欧拉路径(Eulerian path)，即在有环的情况下，如何恰好只走过每一条边然后经过每一个点(点可以重复访问，路径只能走一遍。路径是有方向的)visits every edge exactly once (allowing for revisiting vertices)。比如图的形状是"6"这样的，假设需要从环跟分叉的交点开始出发，我们一直按照贪心dfs，即从该点走字典路径最小的路径，如果走到某个dead end, 走不下去了，这说明该点一定是所有遍历的终点，我们逆序把他加到结果集。然后
    //回溯到父节点，继续这样的走法。需要注意的是，走过的路径需要删除，以免重复访问。
    //参考 https://www.youtube.com/watch?v=4udFSOWQpdg  花花视频下面的评论
    //以及 https://www.youtube.com/watch?v=kZXsB3WemYY
    public List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> res = new LinkedList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();//因为需要按字典序最小的路径，所以用PQ
        buildGraph(tickets, graph);
        dfs(graph, "JFK", res);
        return res;
    }

    private void dfs(Map<String, PriorityQueue<String>> graph, String from, LinkedList<String> res) {
        PriorityQueue<String> destinations = graph.get(from);
        while(destinations != null && !destinations.isEmpty()) {
            String to = destinations.poll();//poll(),删除走过的路径，以免回溯到父节点重复访问。
            dfs(graph, to, res);
        }
        res.addFirst(from);
    }


    private void buildGraph(List<List<String>> tickets, Map<String, PriorityQueue<String>> graph) {
        for(List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            PriorityQueue<String> destinations = graph.computeIfAbsent(from, k -> new PriorityQueue<>());
            destinations.offer(to);
        }
    }
}
