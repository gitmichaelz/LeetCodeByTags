package bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 */
public class MinimumHeightTrees {
    //Here is one insight for this problem: the root of MHT is the middle point of the longest path in the tree; hence there are at most two MHT roots.
    //so we just need to remove nodes from leave to root level by level, the remaining one or two nodes is our result
    //bfs
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n <= 0) return new LinkedList<>();
        if(n == 1) {//这一步非常重要，必不可少，如果只有一个node, degree will be 0, this case is not included in the following discussion.
            return Arrays.asList(0);
        }
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++){
            graph[i] = new LinkedList<>();
        }
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int[] degree = new int[n];// the number of neighbors of each node
        LinkedList<Integer> leaves = new LinkedList<>();//这里我们用linkedlist, 可以作为结果直接返回
        for(int i = 0; i < n; i++){
            degree[i] = graph[i].size();
            if(degree[i] == 1){//leaf's degree is only 1
                leaves.offer(i);//linkedlist 的offer方法是添加到末尾
            }
        }
        int count = n;
        while(count > 2){
            count -= leaves.size();
            for(int i = leaves.size(); i > 0; i--){//delete level by level, 一定要这么写, 不能for(int leaf : leaves),我们一定是只处理本层的leaf
                int leaf = leaves.poll();//linkedlist 也有poll方法，remove the first element
                for(int neighbor : graph[leaf]){
                    degree[neighbor]--;
                    if(degree[neighbor] == 1){
                        leaves.offer(neighbor);
                    }
                }
            }
        }
        return leaves;
    }
}
