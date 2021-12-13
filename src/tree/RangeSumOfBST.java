package tree;

/**
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
 *
 *
 *
 * Example 1:
 *
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 */
public class RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if(root == null) return 0;
        if(root.val >= L && root.val <= R) {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        } else if(root.val < L) {
            return rangeSumBST(root.right, L, R);
        } else {
            return rangeSumBST(root.left, L, R);
        }
    }
    // public int rangeSumBST(TreeNode root, int L, int R) {
    //     Deque<TreeNode> stack = new LinkedList<>();
    //     stack.push(root);
    //     int sum = 0;
    //     while(!stack.isEmpty()){
    //         root = stack.pop();
    //         if(root == null) continue;
    //         if(root.val > L) stack.push(root.left);//left subtree could be a possible candidate
    //         if(root.val < R) stack.push(root.right);//right subtree could be a possible candidate
    //         if(root.val >= L && root.val <= R) sum += root.val;
    //     }
    //     return sum;
    // }
}
