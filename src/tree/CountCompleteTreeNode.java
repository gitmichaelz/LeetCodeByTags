package tree;

/**
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 */
public class CountCompleteTreeNode {
    //分别求出从跟到leftmost child 和到rightmost child的深度，然后比较，如果相同，则return (1 << left) - 1;
    //否则，递归求解左右子树的个数，然后加一，base case是如果root == null, return 0;
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int left = leftHeight(root);
        int right = rightHeight(root);
        if(left == right) {
            return (1 << left) - 1;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    private  int leftHeight(TreeNode root){
        int h = 0;
        while(root != null) {
            root = root.left;
            h++;
        }
        return h;
    }

    private int rightHeight(TreeNode root){
        int h = 0;
        while(root != null){
            root = root.right;
            h++;
        }
        return h;
    }
}
