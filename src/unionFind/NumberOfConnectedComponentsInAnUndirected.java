package unionFind;

/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
 *
 * Return the number of connected components in the graph.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 * Output: 2
 *
 * Example 2:
 *
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 *     1 <= n <= 2000
 *     1 <= edges.length <= 5000
 *     edges[i].length == 2
 *     0 <= ai <= bi < n
 *     ai != bi
 *     There are no repeated edges.
 */
public class NumberOfConnectedComponentsInAnUndirected {
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

    public int countComponents(int n, int[][] edges){
        UnionFind uf = new UnionFind(n);
        for(int[] edge: edges){
            uf.union(edge[0], edge[1]);
        }
        return uf.size();
    }
}
