package unionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 *
 * Return the size of the largest island in grid after applying this operation.
 *
 * An island is a 4-directionally connected group of 1s.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 */
public class MakingALargeIsland {
    class UnionFind{
        int[] father;
        int[] area;
        UnionFind(int n){
            father = new int[n];
            area = new int[n];
            for(int i = 0; i < n; i++){
                father[i] = i;
            }
        }

        int find(int x){
            while(x != father[x]){
                father[x] = father[father[x]];
                x = father[x];
            }

            return x;
        }

        void union(int x, int y){
            int root1 = find(x);
            int root2 = find(y);
            if(root1 != root2){
                father[root1] = root2;
                area[root2] += area[root1];
                maxArea = Math.max(maxArea, area[root2]);//dont forget, update maxArea
            }
        }
    }

    int maxArea = 1;//注意这里初始化为1， 例如case: [[1]]
    public int largestIsland(int[][] grid) {
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int rows = grid.length, cols = grid[0].length;
        UnionFind uf = new UnionFind(rows * cols);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 1){
                    uf.area[i * cols + j] = 1;//update the area of cur grid
                    for(int k = 0; k < 2; k++){//only consider the left col and upper row to avoid duplicates
                        int preRow = i + dirs[k][0];
                        int preCol = j + dirs[k][1];
                        if(preRow >= 0 && preRow < rows && preCol >= 0 && preCol < cols && grid[preRow][preCol] == 1){
                            uf.union(i * cols + j, preRow * cols + preCol);
                        }
                    }
                }
            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == 0){
                    int curPos = i * cols + j;
                    uf.area[curPos] = 1;
                    Set<Integer> visited = new HashSet<>();//Use set to record neighbors' father to avoid adding area repeatedly
                    for(int[] dir : dirs){
                        int newRow = i + dir[0];
                        int newCol = j + dir[1];
                        if(newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1){
                            int neighborFather = uf.find(newRow * cols + newCol);
                            if(!visited.contains(neighborFather)){
                                visited.add(neighborFather);
                                uf.area[curPos] += uf.area[neighborFather];
                            }
                        }
                    }
                    maxArea = Math.max(maxArea, uf.area[curPos]);//update maxArea
                }
            }
        }
        return maxArea;
    }
}
