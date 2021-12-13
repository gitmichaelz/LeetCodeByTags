package unionFind;

/**
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
 *
 * The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.
 *
 * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 *
 *
 * Example 1:
 *
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 */
public class RedundantConnectionII {
    //this problem we will not use the general union find class,
    //https://leetcode.com/problems/redundant-connection-ii/discuss/278105/topic
    int[] ancester;//union find
    int[] father;//record each node's direct father
    public int[] findRedundantDirectedConnection(int[][] edges) {
        ancester = new int[edges.length + 1];//because the number is labled from 1 to N
        father = new int[edges.length + 1];
        int[] edge1 = null;
        int[] edge2 = null;
        int[] lastEdgeCausesCircle = null;
        for(int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if(ancester[u] == 0) ancester[u] = u;//initialize union find set
            if(ancester[v] == 0) ancester[v] = v;

            if(father[v] != 0) {//node v already has a direct father, let's skip the union of this edge and check if there will be a circle, 跳过edge2, 并记下edge1 edge2
                edge1 = new int[]{father[v], v};
                edge2 = edge;
            } else {//union
                father[v] = u;
                int rootU = find(u);
                int rootV = find(v);
                if(rootU != rootV) {
                    ancester[rootV] = ancester[rootU];
                } else {//meet a circle, 碰到了环
                    lastEdgeCausesCircle = edge;
                }
            }
        }
        if(edge1 != null && edge2 != null) return lastEdgeCausesCircle == null? edge2 : edge1;//详见帖子分析。如果是情况2，3，则根据有无环的情况返回edge2 或 edge1
        return lastEdgeCausesCircle;
    }

    public int find(int i) {
        while(i != ancester[i]) {//注意这里我们用ancester来做Union find
            ancester[i] = ancester[ancester[i]];//path compression   logN
            i = ancester[i];
        }
        return i;
    }
}
