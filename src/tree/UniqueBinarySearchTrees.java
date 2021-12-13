package tree;

/**
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 5
 */
public class UniqueBinarySearchTrees {
    //可以这样考虑，根肯定会占用一个结点，那么剩余的n-1个结点可以有如下的分配方式，
    //T(0, n-1),T(1, n-2),...T(n-1, 0)，设T(i, j)表示根的左子树含i个结点，右子树含j个结点。
    //设问题的解为f(n)，那么f(n) = f(0)*f(n-1) + f(1)*f(n-2) + .......+ f(n-2)*f(1) + f(n-1)*f(0)。
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;//0 root, empty node can denote an empty tree
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {//j is the number of left children
                dp[i] += dp[j] * dp[i - j - 1];//total number - left children - root
            }
        }
        return dp[n];
    }
}
