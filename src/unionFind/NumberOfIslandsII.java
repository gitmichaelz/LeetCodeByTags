package unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).
 *
 * We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.
 *
 * Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 *
 *
 * Example 1:
 *
 * Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
 * Output: [1,1,2,3]
 * Explanation:
 * Initially, the 2d grid is filled with water.
 * - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
 * - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
 * - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
 * - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.
 */
public class NumberOfIslandsII {
    //union by rank can make the runtime reduced to klogmn https://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
    //该题并没有像一般的UnionFind那样初始化parent数组，而是每次需要涂某一个位置时才赋值，这样可以通过判断parent[i] > 0 来推断是否某个位置已经被涂成岛屿，又因为位置0如果被涂的话，parent[0] = 0, 这样跟初始值一样，就无法得知0位置是否已涂，所以我们offset一位，即求index时让index + 1;
    int[] parent;
    int[] rank;

    public List<Integer> numIslands2(int m, int n, int[][] positions){
        List<Integer> res = new ArrayList<>();
        parent = new int[m * n + 1];//dont forget the extra offset
        rank = new int[m * n + 1];

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int count = 0;
        for(int[] p : positions){
            int index = p[0] * n + p[1] + 1;
            if(parent[index] == 0){
                count++;
                parent[index] = index;
                for(int[] d : directions){//check neighbors
                    int x = p[0] + d[0], y = p[1] + d[1], neighbor = x * n + y + 1;
                    if(x >=0 && x < m && y >= 0 && y < n && parent[neighbor] > 0){
                        if(union(index, neighbor)){
                            count--;
                        }
                    }
                }

            }
            res.add(count);//对每个position都要加入结果集，这样也包含了重复position的情况
        }
        return res;
    }

    private boolean union(int x, int y){
        int root1 = find(x);
        int root2 = find(y);
        if(root1 == root2) return false;
        if(rank[root1] > rank[root2]) {
            parent[root2] = root1;
        } else {
            parent[root1] = root2;
            if(rank[root1] == rank[root2]){
                rank[root2]++;
            }
        }
        return true;
    }

    private int find(int x){
        while(x != parent[x]){
            parent[x] = parent[parent[x]];//path compression
            x = parent[x];
        }
        return x;
    }
}
