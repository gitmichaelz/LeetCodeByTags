package dfs_backtracking_Recursion;

/**
 * Android devices have a special lock screen with a 3 x 3 grid of dots. Users can set an "unlock pattern" by connecting the dots in a specific sequence, forming a series of joined line segments where each segment's endpoints are two consecutive dots in the sequence. A sequence of k dots is a valid unlock pattern if both of the following are true:
 *
 *     All the dots in the sequence are distinct.
 *     If the line segment connecting two consecutive dots in the sequence passes through the center of any other dot, the other dot must have previously appeared in the sequence. No jumps through the center non-selected dots are allowed.
 *         For example, connecting dots 2 and 9 without dots 5 or 6 appearing beforehand is valid because the line from dot 2 to dot 9 does not pass through the center of either dot 5 or 6.
 *         However, connecting dots 1 and 3 without dot 2 appearing beforehand is invalid because the line from dot 1 to dot 3 passes through the center of dot
 */
public class AndrodUnlockPatterns {
    public int numberOfPatterns(int m, int n) {
        //skip[i][j] means if we are connecting i and j, we must make sure that skip[i][j] is visited/selected,
        int[][]skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[7][9] = skip[9][7] = 8;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[2][8] = skip[8][2]= skip[4][6] = skip[6][4] = skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        int count = 0;
        boolean[] visited = new boolean[10];
        count += dfs(skip, visited, 1, 0, 1, m, n) * 4;//1,3,7,9 are symmetric, so we only need to use 1 to do it once and then multiply the result by 4
        count += dfs(skip, visited, 2, 0, 1, m, n) * 4;//2,4,6,8 are symmetric, so we only need to use 1 to do it once and then multiply the result by 4
        count += dfs(skip, visited, 5, 0, 1, m, n);
        return count;
    }

    private int dfs(int[][] skip, boolean[] visited, int num, int count, int len, int m, int n) {
        if(len >= m && len <= n) {
            count++;//count initialized as 0, when meets m <= len <= n, increase count
        }
        len++;
        if(len > n) {
            return count;//不满足，可以返回count了
        }
        visited[num] = true;
        for(int next = 1; next <= 9; next++) {
            if(!visited[next] && (skip[num][next] == 0 || visited[skip[num][next]])) {
                count = dfs(skip, visited, next, count, len, m, n);
            }
        }
        visited[num] = false;//restore the visited array when backtracking
        return count;
    }
}
