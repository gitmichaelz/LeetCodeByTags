package unionFind;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 */
public class NumberOfProvinces {
    //该题可优先考虑dfs
    //dfs
    public int findCircleNum(int[][] M){
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int N = M.length;
        boolean[] visited = new boolean[N];
        int count = 0;
        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                dfs(M, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited){
        for(int j = 0; j < M.length; j++) {
            if(!visited[j] && M[i][j] == 1) {
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }

    /*
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
    public int findCircleNum(int[][] M) {
        if(M == null || M.length == 0 || M[0].length == 0) return 0;
        int N = M.length;
        UnionFind uf = new UnionFind(N);//actually there are only M.length 个人
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(M[i][j] == 1 && i != j) {
                    uf.union(i, j);
                }
            }
        }
        return uf.size();
    }*/
}
