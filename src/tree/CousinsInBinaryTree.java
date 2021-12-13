package tree;

/**
 * Given the root of a binary tree with unique values and the values of two different nodes of the tree x and y, return true if the nodes corresponding to the values x and y in the tree are cousins, or false otherwise.
 *
 * Two nodes of a binary tree are cousins if they have the same depth with different parents.
 *
 * Note that in a binary tree, the root node is at the depth 0, and children of each depth k node are at the depth k + 1.
 *
 *
 *
 * Example 1:
 *
 * Input: root = [1,2,3,4], x = 4, y = 3
 * Output: false
 */
public class CousinsInBinaryTree {
    TreeNode xParent = new TreeNode(-1), yParent = new TreeNode(-2);//two dummy parent initialized
    int xDepth = -1, yDepth = -2;
    public boolean isCousins(TreeNode root, int x, int y){
        searchNode(root, x, y, 0, null);
        return xDepth == yDepth && xParent != yParent;
    }

    private void searchNode(TreeNode root, int x, int y, int depth, TreeNode parent){
        if(root == null) return;
        if(root.val == x){
            xDepth = depth;
            xParent = parent;
        } else if(root.val == y) {
            yDepth = depth;
            yParent = parent;
        } else {
            searchNode(root.left, x, y, depth + 1, root);
            searchNode(root.right, x, y, depth + 1, root);
        }
    }
}
