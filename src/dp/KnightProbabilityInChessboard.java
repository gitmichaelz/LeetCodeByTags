package dp;

/**
 * On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
 *
 * A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
 *
 * Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
 *
 * The knight continues moving until it has made exactly k moves or has moved off the chessboard.
 *
 * Return the probability that the knight remains on the board after it has stopped moving.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 2, row = 0, column = 0
 * Output: 0.06250
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
 * From each of those positions, there are also two moves that will keep the knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 */
public class KnightProbabilityInChessboard {
    //the 8 moves
    int[][] moves = {{2,1},{-2,1},{1,2},{-1,2},{2,-1},{-2,-1},{1,-2},{-1,-2}};

    public double knightProbability(int N, int K, int r, int c) {
        //probability cache
        double[][][] cache = new double[N/2+1][N/2+1][K+1];

        return knightProbabilityHelper(N,K,r,c,cache);
    }

    private double knightProbabilityHelper(int N, int K, int r, int c, double[][][] cache) {

        if(r<0||r>=N||c<0||c>=N) return 0.0;

        if(K==0) return 1.0;

        r = Math.min(r,N-1-r);
        c = Math.min(c,N-1-c);
        if(r<c) return knightProbabilityHelper(N,K,c,r,cache);

        if(cache[r][c][K]!=0.0)
            return cache[r][c][K];

        double probability = 0.0;
        for(int i=0; i<moves.length; i++)
            probability += knightProbabilityHelper(N, K-1, r + moves[i][0], c + moves[i][1], cache)/8.0;

        cache[r][c][K] = probability;

        return probability;
    }
}
