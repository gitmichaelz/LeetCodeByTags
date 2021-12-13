package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 */
public class UniqueBinarySearchTreesII {
    //思路是每次一次选取一个结点为根，然后递归求解左右子树的所有结果，最后根据左右子树的返回的所有子树，依次选取然后接上
    //一个优化是用一个cache存已经计算过的从start 到end的结果
    List<TreeNode>[][] cache;
    public List<TreeNode> generateTrees(int n) {
        if(n == 0) return new ArrayList<>();
        cache = new List[n + 1][n + 1];
        return generate(1, n);
    }
    private List<TreeNode> generate(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if(start > end) {
            res.add(null);
            return res;
        }
        if(cache[start][end] != null) return cache[start][end];
        for(int i = start; i <= end; i++) {//pick every node from start to end as root
            List<TreeNode> left = generate(start, i - 1);//all possible left child
            List<TreeNode> right = generate(i + 1, end);// all possible right child
            for(TreeNode node1: left){
                for(TreeNode node2 : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = node1;
                    root.right = node2;
                    res.add(root);
                }
            }
        }
        cache[start][end] = res;
        return res;
    }
}
