package unionFind;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 */
public class RedundantConnection {
    class UnionFind {
        private int num;//the number of disjoint sets
        private int[] father;
        private int[] rank;
        public UnionFind(int n) {
            num = n;
            father = new int[num];
            rank = new int[num];
            for(int i = 0; i < num; i++) {
                father[i] = i;
            }
        }

        public int find(int i) {
            while(i != father[i]) {
                father[i] = father[father[i]];//path compression   logN
                i = father[i];
            }
            return i;
        }

        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
        public boolean union(int i, int j) {
            int root1 = find(i);
            int root2 = find(j);
            if(root1 == root2) return false;//no need to union i and j
            if(rank[root1] > rank[root2]) father[root2] = root1;
            else {
                father[root1] = root2;
                if(rank[root1] == rank[root2]) {
                    rank[root2]++;
                }
            }
            num--;//the number of disjoint set decreasing by 1
            return true;
        }
        public int size() {
            return num;
        }
    }
    public int[] findRedundantConnection(int[][] edges) {
        int[]res = new int[2];
        int N = edges.length;//there are N nodes labelled from 1 to N, and we have one more edge, so total N edges
        UnionFind uf = new UnionFind(N + 1);
        for(int[] edge: edges){
            if(!uf.union(edge[0], edge[1])) {
                res = edge;
            }
        }
        return res;
    }
}
