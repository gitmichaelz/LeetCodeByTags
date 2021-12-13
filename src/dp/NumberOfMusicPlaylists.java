package dp;

/**
 * Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:
 *
 *     Every song is played at least once.
 *     A song can only be played again only if k other songs have been played.
 *
 * Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, goal = 3, k = 1
 * Output: 6
 * Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
 */
public class NumberOfMusicPlaylists {
//dp[i][j]: num of music playlists of i songs with j different songs, the final answer is dp[L][N]
    //there are 2 cases for the answer of dp[i][j]
    //case 1: the last added one is new song: dp[i][j] = dp[i - 1][j - 1] * (N - (J - 1))
    //case 2: the last added one is old song: dp[i][j] = dp[i - 1][j] * J

    //now we have constraint K, the above 2 cases would be:
    //case 1: no changes since the newly added one is new song. dp[i][j] = dp[i - 1][j - 1] * (N - (J - 1))
    //case 2: since "A song can only be played again only if K other songs have been played" 一首歌只能在其他K首歌播放完后才能再次被播放
    //Actually, in this problem it means there must be at least K other songs between two same songs
    //例如 N = 2, L = 3, K = 1, we can only get[1, 2, 1], [2, 1, 2]. 而不能产生[1, 2, 2], [2, 1, 1]这样的结果，因为2,2之间没有K首其他歌被播放
    //所以case 2中，我们要修改转换方程： dp[i][j] = dp[i - 1][j] * (j - k) if j > k, else 0 since we cannot produce such lists without k other songs played
    public int numMusicPlaylists(int N, int L, int K) {
        int mod = (int)Math.pow(10, 9) + 7;
        long[][] dp = new long[L + 1][N + 1];
        dp[0][0] = 1;//It's possible to fill an empty list with 0 songs, so dp[0][0] = 1.
        for(int i = 1; i <= L; i++) {
            for(int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i - 1][j - 1] * (N - j + 1)) % mod;
                if(j > K) {
                    dp[i][j] = (dp[i][j] + (dp[i - 1][j] * (j - K)) % mod) % mod;//注意这里要两个mod
                }
            }
        }
        return (int)dp[L][N];
    }
}
