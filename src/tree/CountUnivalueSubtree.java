package tree;

/**
 * Given the root of a binary tree, return the number of uni-value subtrees.
 *
 * A uni-value subtree means all nodes of the subtree have the same value.
 *
 * Example 1:
 *
 * Input: root = [5,1,5,5,5,null,5]
 * Output: 4
 */
public class CountUnivalueSubtree {
    //这两种方法都可以，第二种更简洁一些。
    //Helper isUnivalSubtree tells whether all nodes in the given tree have the given value. And while doing that, it also counts the uni-value subtrees.
    // int count = 0;
    // public int countUnivalSubtrees(TreeNode root) {
    //     isUnivalSubtree(root);
    //     return count;
    // }
    // private boolean isUnivalSubtree(TreeNode root) {
    //     if(root == null) return true;
    //     boolean left = isUnivalSubtree(root.left);
    //     boolean right = isUnivalSubtree(root.right);
    //     if(left && right && (root.left == null || root.val == root.left.val)
    //             && (root.right == null || root.val == root.right.val)) {
    //         count++;
    //         return true;
    //     }
    //     return false;
    // }

    int count = 0;
    public int countUnivalSubtrees(TreeNode root){
        isUniSubtree(root, 0);
        return count;
    }
    //val is parent node's value
    private boolean isUniSubtree(TreeNode root, int val){
        if(root == null) return true;
        if(!isUniSubtree(root.left, root.val) | !isUniSubtree(root.right, root.val)) return false;//dont use ||, use | to get all possible results
        count++;//now we know cur subtree rooted with 'root' is a uniValSubtree,(a subtree of value node.val)
        return root.val == val;//to check if this is a valid subtree for parent node
    }
}
